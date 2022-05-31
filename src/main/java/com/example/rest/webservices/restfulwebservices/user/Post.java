package com.example.rest.webservices.restfulwebservices.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post {
	private String description;
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private User user;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Post [post=" + description + ", id=" + id + "]";
	}
	
	public Post() {
		
	}
	
	public Post(String description, Long id) {
		super();
		this.description = description;
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
