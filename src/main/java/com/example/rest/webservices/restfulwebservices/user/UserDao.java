package com.example.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDao {
	private static List<User> users = new ArrayList<>(); 
	static {
		users.add(new User(1l, "Mubeen", new java.util.Date()));
		ArrayList<Post> posts = new ArrayList<>();
		posts.add(new Post("i am mubeen", 1l));
//		users.get(0).setPosts(posts);
		users.add(new User(2l, "Mubeen1", new java.util.Date()));
		users.add(new User(3l, "Mubeen2", new java.util.Date()));
	}
	public List<User> findAll(){
		return users;
	}
	public User save(User user) {
		if(user.getId() == null) {
			user.setId(users.size()+1l);
		}
		users.add(user);
		return user;
	}
	public User findOne(Long id) {
		return users.stream().filter(cargo -> cargo.getId() == id).findFirst().orElse(null);
	}
	
	public User deleteById(Long id) {
		Iterator<User> it = users.iterator();
		while(it.hasNext()) {
			User user = it.next();
			if(user.getId() == id) {
				it.remove();
				return user;
			}
		}
		return null;
	}
	
}
