package ml.canecarroarmato.controller;

import ml.canecarroarmato.model.Post;
import ml.canecarroarmato.repository.CommentRepository;
import ml.canecarroarmato.repository.PostRepository;
import ml.canecarroarmato.repository.UserRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	
	@RequestMapping(path={"/"}, method=RequestMethod.GET)
	public String homeGet(HttpServletRequest request, Model model) {
				
		List<String> images = new ArrayList<String>();
		List<Long> numComments = new ArrayList<Long>();
		List<Post> posts = postRepository.findTop10ByOrderByPubdateDesc();
		
		for(Post p : posts) {
			images.add(p.getFirstimage(request));
			numComments.add(commentRepository.countByPost(p));
		}
		
		model.addAttribute("images", images);
		model.addAttribute("numComments", numComments);
		model.addAttribute("posts_top_10", posts);
		model.addAttribute("comments_top_5", commentRepository.findTop5ByOrderByPubdateDesc());
						
		return "home";
	}
}
