package ml.canecarroarmato.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ml.canecarroarmato.exceptions.PasswordException;


@Entity
@Table(name = "USERS", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"USERNAME"}),
		@UniqueConstraint(columnNames = {"EMAIL"})
})
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;
	
	@OneToOne(mappedBy="owner")
	private Blog blog;
	
	@NotEmpty(message="* Enter an original username.")
	@Column(name = "USERNAME")
	private String username;
	
	@Size(min = 6, message="* Enter a password with at least 6 characters.")
	@Column(name = "PASSWORD")
	private String password;
	
	@NotEmpty(message="* Enter a valid email address.")
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "AVATAR")
	private String avatar;
	
	@NotEmpty(message="* Enter your first name.")
	@Column(name = "NAME")
	private String name;
	
	@NotEmpty(message="* Enter your last name.")
	@Column(name = "SURNAME")
	private String surname;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "CREATION")
	private LocalDate creation;
	
	@Column(name = "ENABLED")
	private boolean enabled;
	
	@Column(name = "ADMIN")
	private boolean admin;
	
	@OneToMany(mappedBy="post")
	private List<Comment> comments;
	
	@Transient
	private BCryptPasswordEncoder passwordEncoder;
	
	// Constructor methods
	public User() {
		this.creation = LocalDate.now();
		this.avatar	  = "/resources/images/avatar.png";
		this.passwordEncoder = new BCryptPasswordEncoder();
		this.admin   = false;
		this.enabled = false;
	}
	
	// Utility methods
	public void autoEncodePassword() {
		this.password = passwordEncoder.encode(this.password);
	}
	public void authenticate(String password) throws PasswordException {
				
		if(!passwordEncoder.matches(password, this.password)) {
			throw new PasswordException("Password incorrect");
		}
	}
	
	// Getter and Setter methods
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public LocalDate getCreation() {
		return creation;
	}
	public void setCreation(LocalDate creation) {
		this.creation = creation;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
