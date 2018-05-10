package ml.canecarroarmato.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "TAGS")
public class Tag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tagid;
	
	@NotEmpty(message="* Enter a title for this tag.")
	@Column(name = "TITLE")
	private String title;
	
	@ManyToMany(mappedBy = "tagslist")
	private List<Post> posts;
	
	public Tag() {}
	public Tag(String title) {
		this.title = title;
	}
	
	public String getTitleCapitalized() {
		return "# " + title.substring(0, 1).toUpperCase() + title.substring(1);
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPost(List<Post> posts) {
		this.posts = posts;
	}
	public int getTagid() {
		return tagid;
	}
	public void setTagid(int tagid) {
		this.tagid = tagid;
	}
}
