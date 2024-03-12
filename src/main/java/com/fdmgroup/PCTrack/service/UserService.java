package com.fdmgroup.PCTrack.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fdmgroup.PCTrack.dal.UserRepository;
import com.fdmgroup.PCTrack.model.Staff;
import com.fdmgroup.PCTrack.model.User;

@Service
public class UserService {
	private UserRepository userRepository;
//	add spring security
	private PasswordEncoder encoder;

	public UserService(UserRepository userRepository, PasswordEncoder encoder) {
		super();
		this.userRepository = userRepository;
		this.encoder = encoder;
	}
	
	public List<User> findAllUsers() {
		return this.userRepository.findAll();
	}
	
	public long userCount() { 
		return this.userRepository.count();
	}


	// paginates user
	public Page<User> getUserPage(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return this.userRepository.findAll(pageable);
    }
	
	// paginates user with query match 
	public Page<User> findAllUsersPartialMatch(String query, int pageNumber, int pageSize) {
		   Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return this.userRepository.findPartial(query, pageable);
	}

	public long userCountPartial(String query) {
		return this.userRepository.countByUsernameLike(query);
	}
	
	public User findUserEmail(String email) {
		return this.userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("email not found."));
	}
	
	public User findUserId(int userId) {
		return this.userRepository.findById(userId).orElseThrow(()-> new RuntimeException("Username not found."));
	}
	
	public User findByUsername(String username) {
		return this.userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Username not found"));
	}

	public boolean existsByUsername(String username) {
		return this.userRepository.existsByUsername(username);
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
