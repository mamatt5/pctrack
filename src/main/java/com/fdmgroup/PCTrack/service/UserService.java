package com.fdmgroup.PCTrack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fdmgroup.PCTrack.dal.UserRepository;
import com.fdmgroup.PCTrack.model.User;

@Service
public class UserService {
	private UserRepository userRepository;
//	add spring security
	private PasswordEncoder encoder;

	@Autowired
	public UserService(UserRepository userRepository, PasswordEncoder encoder) {
		super();
		this.userRepository = userRepository;
		this.encoder = encoder;
	}
	
	public List<User> findAllUsers() {
		return this.userRepository.findAll();
	}
	
	public User findUserId(int userId) {
		return this.userRepository.findById(userId).orElseThrow(()-> new RuntimeException("Username not found."));
	}
	
	public void register(User newUser) {
		if (this.userRepository.existsById(newUser.getUserId())) {
			throw new RuntimeException("Username already exists");
		} else {
			//use password encoder here
			newUser.setPassword(encoder.encode(newUser.getPassword()));
			this.userRepository.save(newUser);
		}
	}
	
	public void deleteByUserId(int userId) {
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
	
	public String encodePw(String password) {
		return encoder.encode(password);
	}
}
