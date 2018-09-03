package com.referminds.userservice.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.referminds.userservice.Configuration;
import com.referminds.userservice.exception.UserNotFoundException;
import com.referminds.userservice.model.JobPost;
import com.referminds.userservice.model.User;
import com.referminds.userservice.proxy.UserPostsProxy;
import com.referminds.userservice.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	Configuration config;
	@Autowired
	private UserPostsProxy postProxy;
	
	@PostMapping(path = "/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		System.out.println(config.getMinExp());
		User savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@GetMapping(path = "/users")
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping(path = "/users/{id}")
	public User getUser(@PathVariable String id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("No User found for Id - " + id));
	}

	@DeleteMapping(path = "/users/{id}")
	public String deleteUser(@PathVariable String id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("No User found for Id - " + id));
		userRepository.delete(user);
		return "";
	}

	@PutMapping(path = "/users/{id}")
	public User updateUser(@PathVariable String id, @RequestBody User user) {
		User exUser = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("No User found for Id - " + id));
		user.setId(exUser.getId());
		userRepository.save(user);
		return user;
	}
	@GetMapping(path = "/users/{userId}/posts")
	public Iterable<JobPost> getUserPosts(@PathVariable String userId) {
		return postProxy.getUserPosts(userId);
	}
}
