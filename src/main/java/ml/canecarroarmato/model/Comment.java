package ml.canecarroarmato.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "COMMENTS")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commentid;
	
	@ManyToOne
	@JoinColumn(name="USERID")
	private User author;
	
	@ManyToOne
	@JoinColumn(name="POSTID")
	private Post post;
		
	@OneToMany(mappedBy="replyto")
	private List<Comment> replies;
	
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="REPLYTOID")
	private Comment replyto;
	
	@NotEmpty(message="* Enter message for this comment.")
	@Column(name = "BODY")
	private String body;
	
	@Column(name = "PUBDATE")
	private LocalDateTime pubdate;
	
	
	public Comment() {
		this.pubdate = LocalDateTime.now();
		this.replies = new ArrayList<Comment>();
	}
	public Comment(String body) {
		this.pubdate = LocalDateTime.now();
		this.body = body;
		this.replies = new ArrayList<Comment>();
	}

	
	public int getCommentid() {
		return commentid;
	}
	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getPubdate() {
		return pubdate
				.getMonth()
				.toString()
				.substring(0, 3)
				.toLowerCase() + " " + pubdate
				.getDayOfMonth() + ", " + pubdate
				.getYear();
	}
	public void setPubdate(LocalDateTime pubdate) {
		this.pubdate = pubdate;
	}
	public List<Comment> getReplies() {
		return replies;
	}
	public void setReply(Comment reply) {
		this.replies.add(reply);
	}
	public Comment getReplyto() {
		return replyto;
	}
	public void setReplyto(Comment replyto) {
		this.replyto = replyto;
	}
	
}
