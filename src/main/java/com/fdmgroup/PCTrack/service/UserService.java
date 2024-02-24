package com.fdmgroup.PCTrack.service;

import java.util.List;

import com.fdmgroup.PCTrack.dal.UserRepository;
import com.fdmgroup.PCTrack.model.User;

public class UserService {
	private UserRepository userRepository;
//	add spring security
//	private PasswordEncoder encoder;

	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	public List<User> findAllUsers() {
		return this.userRepository.findAll();
	}
	
	public User findUserId(String userId) {
		return this.userRepository.findById(userId).orElseThrow(()-> new RuntimeException("Username not found."));
	}
	
	public void register(User newUser) {
		if (this.userRepository.existsById(newUser.getUserId())) {
			throw new RuntimeException("Username already exists");
		} else {
			//use password encoder here
//			newUser.setPassword(encoder.encode(newUser.getPassword()));
			this.userRepository.save(newUser);
		}
	}
	
	public void deleteByUserId(String userId) {
		if (this.userRepository.existsById(userId)) {
			userRepository.deleteById(userId);
			
		} else {
			throw new RuntimeException("Username does not exist");
		}
	}
	
	public void update(User newUser) {
		if (this.userRepository.existsById(newUser.getUserId())) {
			this.userRepository.save(newUser);
		
		} else {
			throw new RuntimeException("Username does not exist");
		}
	}
	
}
