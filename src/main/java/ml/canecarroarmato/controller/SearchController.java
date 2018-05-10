package ml.canecarroarmato.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ml.canecarroarmato.model.Post;
import ml.canecarroarmato.repository.CommentRepository;
import ml.canecarroarmato.repository.PostRepository;

@Controller
public class SearchController {
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	
	@RequestMapping(
			path   = { "/search" }, 
			method = RequestMethod.GET
	)
	public String searchGet(HttpServletRequest request,
			@RequestParam("q") String query, Model model) {
				
		// immagini dei post
		List<String> imgs = new ArrayList<String>();
		
		// lista di post
		List<Post> posts  = postRepository.findBySubstring(query.toUpperCase());
		
		// lista di commenti
		List<Long> numComments = new ArrayList<Long>();
		
		for(Post p : posts) {
			imgs.add(p.getFirstimage(request));
			numComments.add(commentRepository.countByPost(p));
		}
		
		model.addAttribute("posts", posts);
		model.addAttribute("imgs", imgs);
		model.addAttribute("numComments", numComments);
		
		return "search";
	}
	
	
	@ResponseBody
	@RequestMapping(
			path	 = { "/search-asynch" }, 
			method	 = RequestMethod.GET, 
			produces = { MediaType.APPLICATION_JSON_VALUE }
	)
	public String searchGetAsynch(HttpServletRequest request,
			@RequestParam("q") String query) {
				
		JSONObject resp = new JSONObject();		
		JSONArray array = new JSONArray();
				
		List<Post> posts = postRepository.findBySubstring(query.toUpperCase());
		
		for(Post p : posts) {
						
			JSONObject JsonPost = new JSONObject();
			
			JsonPost.put("postid", p.getPostid());
			JsonPost.put("userid", p.getOwnerblog().getOwner().getUserid());
			JsonPost.put("title",  p.getTitle());
			JsonPost.put("pubdate",p.getPubdate());
			JsonPost.put("img",    p.getFirstimage(request));
			
			array.put(JsonPost);
		}
		resp.put("post", array);
		
		return resp.toString();
	}
}
