package com.fdmgroup.PCTrack.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.fdmgroup.PCTrack.model.User;
import com.fdmgroup.PCTrack.service.UserService;

public class UserController {
	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	public List<User> getUsers() {
		return userService.findAllUsers();
	}
	
	public User findById(@PathVariable int userId) {
		return userService.findUserId(userId);
	}
	
	public User createNewUser(@RequestBody User newUser) {
		userService.register(newUser);
		return userService.findUserId(newUser.getUserId());
	}
	
	public User updateUser(@RequestBody User newUser) {
		userService.update(newUser);
		return userService.findUserId(newUser.getUserId());
	}
	
	public void deleteUser(@PathVariable int userId) {
		userService.deleteByUserId(userId);
	}

}
