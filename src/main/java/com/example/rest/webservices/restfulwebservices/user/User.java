package com.example.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;
	
	@Size(min=2, message="Name should have atleast 2 characters")
	private String name;
	@OneToMany(mappedBy = "user")
	private List<Post> posts;
//	private ArrayList<Post> posts;
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public User() {
		
	}
	
	public User(Long id, String name, Date dob) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
	}
	@Past
	private Date dob;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", dob=" + dob + "]";
	}
//	public ArrayList<Post> getPosts() {
//		return posts;
//	}
//	public void setPosts(ArrayList<Post> posts) {
//		this.posts = posts;
//	}
}
