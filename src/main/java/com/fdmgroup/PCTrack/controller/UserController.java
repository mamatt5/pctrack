package com.fdmgroup.PCTrack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fdmgroup.PCTrack.model.User;
import com.fdmgroup.PCTrack.service.UserService;

@RestController
@CrossOrigin("http://localhost:5813")
public class UserController {
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("users")
	public List<User> getUsers() {
		return userService.findAllUsers();
	}
	
	@GetMapping("users/{userId}")
	public User findById(@PathVariable int userId) {
		return userService.findUserId(userId);
	}
	
	@PostMapping("users")
	public User createNewUser(@RequestBody User newUser) {
		userService.register(newUser);
		return userService.findUserId(newUser.getUserId());
	}
	
	@PutMapping("users")
	public User updateUser(@RequestBody User newUser) {
		userService.update(newUser);
		return userService.findUserId(newUser.getUserId());
	}
	
	@DeleteMapping("users")
	public void deleteUser(@PathVariable int userId) {
		userService.deleteByUserId(userId);
	}

}
