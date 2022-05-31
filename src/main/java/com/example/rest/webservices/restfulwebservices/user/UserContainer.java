package com.example.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class UserContainer {
	@Autowired
	private UserDao userService;
	@GetMapping("users/{id}")
	public EntityModel<User> findUser(@PathVariable long id) {
	  User user = userService.findOne(id);
	  if(user == null) {
		  throw new UserNotFoundException("id-"+id);
	  }
	  EntityModel<User> model = EntityModel.of(user);
	  WebMvcLinkBuilder linkToUsers = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findAll());
	  model.add(linkToUsers.withRel("all-users"));
	  return model;
	}
	@GetMapping("users")
	public List<User> findAll() {
	  return userService.findAll();
	}
	@PostMapping("users")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
	  User savedUser = userService.save(user);
	  URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
	  return ResponseEntity.created(location).build();
	}
//	@GetMapping("users/{id}/posts")
//	public ArrayList<Post> getUserPosts(@PathVariable long id){
//		return this.findUser(id).getPosts();
//	}
	@DeleteMapping("users/{id}")
	public void deleteById(@PathVariable long id) {
		User user = userService.deleteById(id);
		if(user == null) {
			throw new UserNotFoundException("id-"+id);
		}
	}
}
