package ml.canecarroarmato.controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.imgscalr.Scalr;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ml.canecarroarmato.model.Comment;
import ml.canecarroarmato.model.User;
import ml.canecarroarmato.repository.CommentRepository;
import ml.canecarroarmato.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	
	@ResponseBody
	@RequestMapping(path	 = { "/user/{userid}" }, 
					method   = RequestMethod.POST, 
					produces = { MediaType.APPLICATION_JSON_VALUE }
	)
	public String userPost(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("image") MultipartFile image,
			@PathVariable int userid) {
		
		JSONObject resp = new JSONObject();
		JSONObject ret  = new JSONObject();
		
		// controllo se l'utente ha inserito un file
		if(image.isEmpty()) {
			resp.put("validated", false);
			ret .put("fileType", "The image uploaded is empty.");
			resp.put("error", ret);
			
			return resp.toString();
		}
						
		String suffix 		  = LocalDateTime.now().toString()
										.replace('.', '-')
										.replace(':', '-');
		String mimeType 	  = null;
		String fileNameString = null;
		
		fileNameString = image.getOriginalFilename();
		mimeType 	   = request.getServletContext()
								.getMimeType(fileNameString);
		
		// controllo se il file inserito dall'utente è un'immagine
		if (!mimeType.startsWith("image/")) {
			
			// Return an error
			// ...
			resp.put("validated", false);
			ret .put("fileType", "The file uploaded is not an image.");	
			resp.put("error", ret);
			
			return resp.toString();
		}
		
		BufferedImage buffImg = null;
		
		try {
			buffImg = ImageIO.read(new ByteArrayInputStream(image.getBytes()));
		} catch (IOException e1) {
				// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		if(buffImg.getWidth() > 200) {
			ret.put("avatarResized", true);
		}
		
		// resize
		BufferedImage newAvatar = Scalr.resize(buffImg, 200);
		
		HttpSession session = request.getSession();
		
		User userSession = (User) session.getAttribute("userSession");
		
		String format = image.getOriginalFilename().substring(
				image.getOriginalFilename().lastIndexOf('.') + 1,
				image.getOriginalFilename().length()
		);
		
		File file = new File("D://server-uploads/" + 
				userSession.getUserid() + '-' + suffix + "." + format
		);
			
		OutputStream outputStream = null;
			
		try {
			outputStream = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		try {
			ImageIO.write(newAvatar, format, outputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resp.put("validated", true);
					
		userSession.setAvatar("/uploads/" + userSession.getUserid() + '-' + suffix + "." + format);

		userRepository.save(userSession);
		
		ret.put("avatarChange", true);
		ret.put("avatarPath", userSession.getAvatar());
		
		resp.put("success", ret);
		
		return resp.toString();		
	}
	

	@RequestMapping(path={"/user/{userid}"}, method=RequestMethod.GET)
	public String userGet(HttpServletRequest request, HttpServletResponse response, Model model, 
			@PathVariable int userid) {
				
		User user = userRepository.findByUserid(userid);
		
		if(user == null) {
			
			try {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "Not Found");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
		
		List<String>  images = new ArrayList<String>();
		List<Comment> comments = commentRepository.findTop5ByAuthorOrderByPubdateDesc(user);
		
		for(Comment c : comments) {
			images.add(c.getPost().getFirstimage(request));
		}
				
		model.addAttribute("userModel", user);
		model.addAttribute("comments", comments);
		model.addAttribute("images", images);
		model.addAttribute("numComments", commentRepository.countByAuthor(user));
				
		return "user";
	}
}
