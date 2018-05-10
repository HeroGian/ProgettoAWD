package ml.canecarroarmato.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

@Entity
@Table(name = "POSTS")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postid;
	
	@NotEmpty(message="* Enter a title for this post.")
	@Column(name = "TITLE")
	private String title;
	
	@Column(name = "SMALLTITLE")
	private String smalltitle;
	
	@NotEmpty(message="* Enter your post.")
	@Column(name = "POST")
	private String post;
	
	@Column(name = "SMALLPOST")
	private String smallpost;
	
	@Column(name = "PUBDATE")
	private LocalDateTime pubdate;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="BLOGID")
	private Blog ownerblog;
	
	@OneToMany(mappedBy="post")
	private List<Comment> comments;
	
	@Column(name = "VIEWS")
	private int views;
	
	@ManyToMany
	@JoinTable(name = "POSTS_TAGS",
		joinColumns = @JoinColumn(name = "POSTID"),
		inverseJoinColumns = @JoinColumn(name = "TAGID")
	)
	private List<Tag> tagslist;
	
	
	public Post() { this.views = 0; }
	public Post(String title, String smalltitle, 
				String post, String smallpost, 
				Blog ownerblog, List<Tag> tagslist) {
		
		this.pubdate = LocalDateTime.now();
		this.post = post;
		this.smallpost = smallpost;
		this.title = title;
		this.smalltitle = smalltitle;
		this.ownerblog = ownerblog;
		this.tagslist = tagslist;
		this.views = 0;
	}
	public Post(String title, String smalltitle, 
			String post, String smallpost, 
			Blog ownerblog) {
		
		this.pubdate = LocalDateTime.now();
		this.post = post;
		this.smallpost = smallpost;
		this.title = title;
		this.smalltitle = smalltitle;
		this.ownerblog = ownerblog;
		this.views = 0;
	}
	
	public String getFirstimage(HttpServletRequest req) {
		
		Document doc  = Jsoup.parse(post);		
		Elements imgs = doc.select("img");
				
		if(!imgs.isEmpty()) {
			return imgs.first().attr("src");
		}
		
		return String.format(
				"%s://%s:%d/resources/images/no-image.png", 
				req.getScheme(), req.getServerName(), req.getServerPort()
		);

	}
	public String getSmallDescription() {
		return Jsoup.clean(post, Whitelist.none()).substring(0, 135);
	}
 	public int getPostid() {
		return postid;
	}
	public void setPostid(int postid) {
		this.postid = postid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPubdate() {
		return "On " + pubdate.getMonth().toString().toLowerCase().substring(0, 3) + " " + pubdate.getDayOfMonth();
	}
	public void setPubdate(LocalDateTime pubdate) {
		this.pubdate = pubdate;
	}
	public Blog getOwnerblog() {
		return this.ownerblog;
	}
	public void setOwnerblog(Blog ownerblog) {
		this.ownerblog = ownerblog;
	}
	public String getPost() {
		return this.post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public List<Tag> getTagslist() {
		return this.tagslist;
	}
	public void setTagslist(List<Tag> tagslist) {
		this.tagslist = tagslist;
	}
	public String getSmalltitle() {
		return smalltitle;
	}
	public void setSmalltitle(String smalltitle) {
		this.smalltitle = smalltitle;
	}
	public String getSmallpost() {
		return smallpost;
	}
	public void setSmallpost(String smallpost) {
		this.smallpost = smallpost;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
}
