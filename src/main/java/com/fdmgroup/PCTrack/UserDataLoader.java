package com.fdmgroup.PCTrack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import com.fdmgroup.PCTrack.model.User;
import com.fdmgroup.PCTrack.service.*;

@Service
public class UserDataLoader implements ApplicationRunner {
	
	private UserService userService;
	
	@Autowired
	public UserDataLoader(UserService userService) {
		super();
		this.userService = userService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		User user1 = new User("username", "password", "John", "Smith");
		
		userService.register(user1);
	}
}