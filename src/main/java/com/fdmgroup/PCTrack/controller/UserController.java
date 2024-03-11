package com.fdmgroup.PCTrack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fdmgroup.PCTrack.model.Staff;
import com.fdmgroup.PCTrack.model.User;
import com.fdmgroup.PCTrack.service.StaffService;
import com.fdmgroup.PCTrack.service.UserService;

@RestController
@CrossOrigin("http://localhost:5173")
public class UserController {
	private UserService userService;
	private StaffService staffService;
	
	@Autowired
	public UserController(UserService userService, StaffService staffService) {
		super();
		this.userService = userService;
		this.staffService = staffService;
	}
	
	@GetMapping("users")
	public List<User> getUsers() {
		return userService.findAllUsers();
	}
	 
	@GetMapping("usersEmail/{email}")
	public User findByEmail(@PathVariable String email) {
		return userService.findUserEmail(email);
	}
	
	// pagination 
	@GetMapping("userPage")
	public List<User> getStaffPage(@RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "10") int pageSize) {
		return userService.getUserPage(pageNumber, pageSize).getContent();

	}

	@GetMapping("countUserPartial/{query}")
	public long countUser(@PathVariable String query) {
		return userService.userCountPartial(query);
	} 
	
	
	@GetMapping("countUser")
	public long countStaff() {  
		return userService.userCount();
	}
	
	@GetMapping("searchUser/{query}")
	public List<User> getStaffPartial(@PathVariable String query, @RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "10") int pageSize) {
		return userService.findAllUsersPartialMatch(query, pageNumber, pageSize).getContent();
	}
	
	@GetMapping("users/{userId}")
	public User findById(@PathVariable int userId) {
		return userService.findUserId(userId);
	}
	  
	@GetMapping("username/{username}")
	public User findByUsername(@PathVariable String username) {
		return userService.findByUsername(username);
	}
	
	@PostMapping("users")
	public User createNewUser(@RequestBody User newUser) {
		if (!userService.existsByUsername(newUser.getUsername())) {
			System.out.println(newUser.toString());
			System.out.println("dude");
			userService.register(newUser);
			return userService.findUserId(newUser.getUserId());
		} else {
			throw new RuntimeException("Username already exist");
		}
	}
	
	@PutMapping("users")
	public User updateUser(@RequestBody User newUser) {
		newUser.setPassword(userService.encodePw(newUser.getPassword()));
		userService.update(newUser);
		return userService.findUserId(newUser.getUserId());
	}
	
	@DeleteMapping("users/{userId}")
	public void deleteUser(@PathVariable int userId) {
		for (Staff staff : staffService.findByUserId(userId)) {
			staffService.deleteByStaffId(staff.getStaffId());
		}
		userService.deleteByUserId(userId);
	}

}
