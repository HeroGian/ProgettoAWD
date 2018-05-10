package ml.canecarroarmato.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ml.canecarroarmato.model.Blog;
import ml.canecarroarmato.model.Comment;
import ml.canecarroarmato.model.Post;
import ml.canecarroarmato.model.Tag;
import ml.canecarroarmato.repository.BlogRepository;
import ml.canecarroarmato.repository.CommentRepository;
import ml.canecarroarmato.repository.PostRepository;
import ml.canecarroarmato.repository.TagRepository;
import ml.canecarroarmato.repository.UserRepository;


@Controller
public class PostController {
	
	@Autowired
	TagRepository tagRepository;
	
	@Autowired
	BlogRepository blogRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	
	@RequestMapping(path={"/user/{userid}/blog/post/{postid}"}, method=RequestMethod.GET)
	public String newpostGet(HttpServletRequest request,
			Model model,
			@PathVariable int userid, 
			@PathVariable int postid) {
		
		Post post = postRepository.findByPostid(postid);
				
		// update visualizzazioni
		post.setViews(post.getViews() + 1);
		postRepository.save(post);
		
		// lista di commenti al post
		List<Comment> comments = commentRepository.findAllParentCommentsByPost(post);
		
		// lista di tag del post corrente
		List<Tag> tags = tagRepository.findByPostid(postid);
		
		// lista di post per ogni tag del commento
		List<List<Post>> posts_x_tag = new ArrayList<List<Post>>();
		List<List<String>> images 	 = new ArrayList<List<String>>();
		
		// lista di risposte ad ogni commento
		List<List<Comment>> replies = new ArrayList<List<Comment>>();
		
		List<List<Long>> comments_count = new ArrayList<List<Long>>();
		
		for(Tag t : tags) {
			
			// tutti i post di un tag
			List<Post> tmp = postRepository.findByTagid(t.getTagid(), post.getPostid());
			
			List<String> images_x_tag = new ArrayList<String>();
			
			// tutte le immagini dei posts di un dato tag
			for(Post p : tmp) {
				images_x_tag.add(p.getFirstimage(request));
			}
			posts_x_tag.add(tmp);
			images.add(images_x_tag);
		}
		for(Comment c : comments) {
			replies.add(commentRepository.findAllRepliesByPost(post, c));
		}
		
		for(List<Post> listP : posts_x_tag) {
			List<Long> tmp = new ArrayList<Long>();
			for(Post p : listP) {
				tmp.add(commentRepository.countByPost(p));
			}
			comments_count.add(tmp);
		}
		
		model.addAttribute("comments_count", comments_count);
		model.addAttribute("post", post);
		model.addAttribute("comments", comments);
		model.addAttribute("tags", tags);
		model.addAttribute("posts_x_tag", posts_x_tag);
		model.addAttribute("numComments", commentRepository.countByPost(post));
		model.addAttribute("replies", replies);
		model.addAttribute("images", images);
				
		return "post";
	}
	

	@RequestMapping(path={"/user/{userid}/blog/new-post"}, method=RequestMethod.POST)
	public String newpostPost(
			@PathVariable int userid,
			@RequestParam(value="postBody") String postBodyHtml,
			@RequestParam(value="postTitle") String postTitle,
			@RequestParam(value="postTags") String postTags) {
		
		if(postTitle == "") {
			return "redirect:/user/{userid}/blog";
		}
		
		Blog blog = blogRepository.findByUserId(userid);
		
		List<Tag> tagList = null;
		List<String> tagListString = null;
		
		if(postTags != "") {
			
			tagListString = Arrays.asList(
					postTags.split(",")
			);
					
			tagList = new ArrayList<>();
					
			// creazione lista di tag
			
			for(String s : tagListString) {
				
				s = s.trim().toLowerCase();
				
				Tag tmptag = tagRepository.findByTitle(s);
				
				if(tmptag == null) {
					
					Tag t = new Tag(s);
					
					System.out.println("Salvataggio tag");
					tagRepository.save(t);
					
					tagList.add(t);
				}
				else {
					tagList.add(tmptag);
				}
			}
		}
		
		// salvataggio del post sul database
		
		String smalltitle = "";
		String smallposts = "";
		
		if(postTitle.length() > 35) {
			smalltitle = postTitle.substring(0, 31) + "...";
		}
		else {
			smalltitle = postTitle;
		}
		
		smallposts = Jsoup.clean(postBodyHtml, Whitelist.none());
		if(smallposts.length() > 133) {
			smallposts = smallposts.substring(0, 129) + "...";
		}		
		
		Post post;
		if(postTags != "") {
			post = postRepository.save(new Post(
					postTitle,
					smalltitle,
					Jsoup.clean(postBodyHtml, Whitelist.relaxed()),
					smallposts,
					blog,
					tagList
			));
		}
		else {
			post = postRepository.save(new Post(
					postTitle,
					smalltitle,
					Jsoup.clean(postBodyHtml, Whitelist.relaxed()),
					smallposts,
					blog
			));
		}
		
				
		return "redirect:/user/{userid}/blog/post/" + post.getPostid();
	}
}
