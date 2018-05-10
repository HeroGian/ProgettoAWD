package ml.canecarroarmato.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ml.canecarroarmato.model.Blog;
import ml.canecarroarmato.model.Category;
import ml.canecarroarmato.model.Post;
import ml.canecarroarmato.model.User;
import ml.canecarroarmato.repository.BlogRepository;
import ml.canecarroarmato.repository.CategoryRepository;
import ml.canecarroarmato.repository.CommentRepository;
import ml.canecarroarmato.repository.PostRepository;
import ml.canecarroarmato.repository.UserRepository;

@Controller
public class BlogController {
	
	@Autowired
	BlogRepository blogRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	
	public int validateBlogCreation(Blog targetBlog, User userSession, int userid) {
		
		// il blog non è stato creato
		if(targetBlog == null) {
			// il blog da creare appartiene all'utente della sessione
			if(userSession != null && 
				userid == userSession.getUserid()) {
				return 1;
			}
			return 2; // utente non loggato oppure utente loggato ma 
					  // il suo id non corrisponde al blog che vorrebbe creare
		}
		return 3;	  // blog già esistente
	}
	
	
	@RequestMapping(path={"/user/{userid}/new-blog"}, method=RequestMethod.GET)
	public String newBlogGet(HttpServletRequest request, HttpServletResponse response, Model model,
			@PathVariable int userid) {
		
		Blog blog = blogRepository.findByUserId(userid);
		
		HttpSession session = request.getSession();		
		
		// controlla se è possibile creare un blog
		int action = validateBlogCreation(
				blog, 
				(User) session.getAttribute("userSession"), 
				userid
		);
		
		if(action == 2 || // il blog che voglio creare non è mio
		   action == 3){  // esiste gia
						
			try {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
		
		List<Category> categories = (List<Category>) categoryRepository.findAll();
		
		model.addAttribute("categories", categories);
		
		return "new-blog";
	}
	
	/*
	 * Visualizza la pagina del blog
	 */
	@RequestMapping(path={"/user/{userid}/blog"}, method=RequestMethod.GET)
	public String blogGet(HttpServletRequest request, HttpServletResponse response, Model model,
			@PathVariable int userid) {
				
		Blog blog = blogRepository.findByUserId(userid);
				
		HttpSession session = request.getSession();
				
		// controlla se è possibile creare un blog
		int action = validateBlogCreation(
				blog, 
				(User) session.getAttribute("userSession"), 
				userid
		);
		
		if(action == 1){
			return "redirect:/user/{userid}/new-blog";
		}
		else if(action == 2) {
			
			try {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "Not Found");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
		
		List<Long> comments = new ArrayList<Long>();
		List<Post> posts = postRepository.findByOwnerblog(blog);
		
		for(Post p : posts) {
			comments.add(commentRepository.countByPost(p));
		}
		
		model.addAttribute("posts", posts);
		model.addAttribute("comments", comments);
		model.addAttribute("blog", blog);
		
		return "blog";
	}
	
	/*
	 * Gestisce la creazione di un nuovo blog
	 */
	@RequestMapping(path={"/user/{userid}/new-blog"}, method=RequestMethod.POST)
	public String blogPost(HttpServletRequest request, HttpServletResponse response, Model model,
			@PathVariable int userid,
			@RequestParam("blogCategory") int catid,
			@RequestParam("blogTitle") String title) {
		
		HttpSession session = request.getSession();
		
		// controlla se è possibile creare un blog
		int action = validateBlogCreation(
			blogRepository.findByUserId(userid), 
			(User) session.getAttribute("userSession"), 
			userid
		);
				
		if(action == 2 || // il blog che voglio creare non è mio
		   action == 3){  // esiste gia
										
			try {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			return null;
		}
		
		Map<String, String> JsonResp = new HashMap<String, String>();
		
		boolean errors = false;
		
		// input validation
		if(title.length() < 1) {
			JsonResp.put("titleErr", "* insert a title.");			
			errors = true;
		}		
		
		// input not validated
		if(errors) {
			System.out.println(JsonResp);
			model.addAttribute("errors", JsonResp);
			return "blog-creation";
		}
		
		blogRepository.save(new Blog(
			title, 
			(User) session.getAttribute("userSession"), 
			categoryRepository.findByCatid(catid)
		));
		
		return "redirect:/";
	}
}
