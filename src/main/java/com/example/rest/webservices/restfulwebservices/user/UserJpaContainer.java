package com.example.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
public class UserJpaContainer {
	@Autowired
	private UserDao userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("jpa/users/{id}")
	public EntityModel<User> findUser(@PathVariable long id) {
	  Optional<User> user = userRepository.findById(id);
	  if(!user.isPresent()) {
		  throw new UserNotFoundException("id-"+id);
	  }
	  EntityModel<User> model = EntityModel.of(user.get());
	  WebMvcLinkBuilder linkToUsers = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findAll());
	  model.add(linkToUsers.withRel("all-users"));
	  return model;
	}
	@GetMapping("jpa/users")
	public List<User> findAll() {
	  return userRepository.findAll();
	}
	@PostMapping("jpa/users")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
	  User savedUser = userRepository.save(user);
	  URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
	  return ResponseEntity.created(location).build();
	}
//	@GetMapping("users/{id}/posts")
//	public ArrayList<Post> getUserPosts(@PathVariable long id){
//		return this.findUser(id).getPosts();
//	}
	@DeleteMapping("jpa/users/{id}")
	public void deleteById(@PathVariable long id) {
		userRepository.deleteById(id);
	}
	
	@GetMapping("jpa/users/{id}/posts")
	public List<Post> getPosts(@PathVariable Long id){
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			  throw new UserNotFoundException("id-"+id);
		  }
		return user.get().getPosts();
	}
	
	@PostMapping("jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable Long id, @RequestBody Post post){
		Optional<User> userOptional = userRepository.findById(id);
		  if(!userOptional.isPresent()) {
			  throw new UserNotFoundException("id-"+id);
		  }
		User user = userOptional.get();
		post.setUser(user);
		Post savedPost = postRepository.save(post);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("id").buildAndExpand(savedPost.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
}
