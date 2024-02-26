package com.fdmgroup.PCTrack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import com.fdmgroup.PCTrack.model.*;
import com.fdmgroup.PCTrack.service.*;

@Service
public class UserDataLoader implements ApplicationRunner {
	
	private UserService userService;
	private LocationService locationService;
	private StaffService staffService;
	
	@Autowired
	public UserDataLoader(UserService userService, LocationService locationService, StaffService staffService) {
		super();
		this.userService = userService;
		this.locationService = locationService;
		this.staffService = staffService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		User user1 = new User("username", "password", "John", "Smith");
		Location location1 = new Location("Sydney Office", "Sydney");
		userService.register(user1);
		locationService.save(location1);
		Staff staff1 = new Staff(user1, location1);
		staffService.save(staff1);
	}
}