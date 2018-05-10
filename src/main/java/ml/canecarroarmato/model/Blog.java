package ml.canecarroarmato.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "BLOGS")
public class Blog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int blogid;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="USERID")
	private User owner;
	
	@NotEmpty(message="* Enter a title for your blog.")
	@Column(name = "TITLE")
	private String title;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CATID")
	private Category category;
	
	@OneToMany(
			fetch=FetchType.EAGER, 
			mappedBy = "ownerblog"
	)
	private List<Post> posts;
	
	@Column(name = "CREATION")
	private LocalDate creation;
	
	// Constructor methods
	public Blog() { }
	public Blog(String title, User owner, Category category) {
		this.creation = LocalDate.now();
		this.owner = owner;
		this.category = category;
		this.title = title;
	}
	
	// Getter and Setter methods
	public int getBlogid() {
		return blogid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDate getCreation() {
		return creation;
	}
	public void setCreation(LocalDate creation) {
		this.creation = creation;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}


}
