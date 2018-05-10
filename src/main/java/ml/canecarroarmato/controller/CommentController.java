package ml.canecarroarmato.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ml.canecarroarmato.model.Comment;
import ml.canecarroarmato.model.Post;
import ml.canecarroarmato.model.User;
import ml.canecarroarmato.repository.CommentRepository;
import ml.canecarroarmato.repository.PostRepository;
import ml.canecarroarmato.repository.UserRepository;

@Controller
public class CommentController {
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;
	
	
	/*
	 * Gestisce l'inserimento di una risposta relativa ad un commento nella pagina di un post
	 */
	@ResponseBody
	@RequestMapping(path	 = { "/new-reply" }, 
					method   = RequestMethod.POST, 
					produces = { MediaType.APPLICATION_JSON_VALUE }
	)
	public String replyPost(HttpServletRequest request,
			@RequestParam("postid") int  postid,
			@RequestParam("commentid") int commentid,
			@RequestParam("body") String body) {
		
		JSONObject resp = new JSONObject();
		
		if(body.length() < 1) {
						
			resp.put("valid", false);
			resp.put("error", "* Enter a body for your reply.");
		}
		else {
			
			resp.put("valid", true);
			
			HttpSession session = request.getSession();
			
			User userSession = (User) session.getAttribute("userSession");
			
			Post post = postRepository.findByPostid(postid);
			
			Comment commParent = commentRepository.findByCommentid(commentid);
			Comment reply = new Comment(body);
			
			reply.setAuthor(userSession);
			reply.setPost(post);
			reply.setReplyto(commParent);
			
			// salvataggio sul db del nuovo commento
			commentRepository.save(reply);
			
			JSONObject success = new JSONObject();
			
			success.put("body", body);
			success.put("pubdate", reply.getPubdate());
			success.put("userid", userSession.getUserid());
			success.put("username", userSession.getUsername());
			success.put("avatar", userSession.getAvatar());
			success.put("replyid", reply.getCommentid());
			success.put("parentId", commParent.getCommentid());
			
			resp.put("success", success);
		}
		
		
		return resp.toString();
	}
	
	
	/*
	 * Gestisce l'inserimento di un commento nella pagina di un post 
	 */
	@ResponseBody
	@RequestMapping(path	 = { "/new-comment" }, 
					method   = RequestMethod.POST, 
					produces = { MediaType.APPLICATION_JSON_VALUE }
	)
	public String commentPost(HttpServletRequest request,
			@RequestParam("postid") int  postid,
			@RequestParam("body") String body) {
		
		JSONObject resp = new JSONObject();
		
		System.out.println("body: " + body);
		System.out.println("postid: " + postid);
		
		if(body.length() < 1) {
			resp.put("valid", false);
			resp.put("error", "* Enter a body for your comment.");
		}
		else {
			
			HttpSession session = request.getSession();
			
			User userSession = (User) session.getAttribute("userSession");
			Post post = postRepository.findByPostid(postid);
			
			Comment comment = new Comment(body);
			
			comment.setAuthor(userSession);
			comment.setPost(post);
			
			Comment commentSaved = commentRepository.save(comment);
			
			resp.put("valid", true);
			
			JSONObject success = new JSONObject();
			
			success.put("body", comment.getBody());
			success.put("pubdate", comment.getPubdate());
			success.put("username", userSession.getUsername());
			success.put("avatar", userSession.getAvatar());
			success.put("userid", userSession.getUserid());
			success.put("commid", commentSaved.getCommentid());
			
			resp.put("success", success);
		}		
		
		return resp.toString();
	} 
}
