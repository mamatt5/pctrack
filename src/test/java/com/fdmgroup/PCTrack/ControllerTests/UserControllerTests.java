package com.fdmgroup.PCTrack.ControllerTests;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PCTrack.controller.UserController;
import com.fdmgroup.PCTrack.model.User;
import com.fdmgroup.PCTrack.service.StaffService;
import com.fdmgroup.PCTrack.service.UserService;

import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests {
	
	@Mock
	UserService userService;
	@Mock
	StaffService staffService;
	
	UserController userController;
	
	@BeforeEach
	void setup() {
		this.userController = new UserController(userService, staffService);
	}
	
	@Test
	void findUserById_test() {
		
		User user1 = new User("andy.joe", "password123", "Andy", "Joe", LocalDate.of(2023, 11, 27));
		
		when(userService.findUserId(1)).thenReturn(user1);
		assertSame(user1, userController.findById(1));
		verify(userService, times(1)).findUserId(1);
	}
	
	@Test
	void createUser_test() {
		User user1 = new User("andy.joe", "password123", "Andy", "Joe", LocalDate.of(2023, 11, 27));
		
		userController.createNewUser(user1);
		verify(userService, times(1)).register(user1);
	}
	
	@Test
	void updateUser_test() {
		User user1 = new User("andy.joe", "password123", "Andy", "Joe", LocalDate.of(2023, 11, 27));
		User updatedUser = new User("updated.updated", "updated", "Up", "Dated", LocalDate.of(2023, 4, 27));
		
	
		when(userService.findUserId(0)).thenReturn(updatedUser);
		assertSame(userController.updateUser(user1), updatedUser);
		verify(userService, times(1)).update(user1);
	}
	
	@Test
	void deleteUser_test() {
		userController.deleteUser(1);
		verify(userService, times(1)).deleteByUserId(1);
	}
	
	@Test
	void findAllUsers() {
		User user1 = new User("andy.joe", "password123", "Andy", "Joe", LocalDate.of(2023, 11, 27));
		User user2 = new User("ahri.foxian", "password123", "Ahri", "Foxian", LocalDate.of(2023, 11, 27));
		User user3 = new User("aatrox.damion", "password123", "Aatrox", "Damion", LocalDate.of(2023, 11, 27));
		User user4 = new User("amumu.munsen", "password123", "Amumu", "Munsen", LocalDate.of(2023, 11, 27));

		
		List<User> allUsers = new ArrayList<>();
		allUsers.add(user1);
		allUsers.add(user2);
		allUsers.add(user3);
		allUsers.add(user4);
		
		when(userService.findAllUsers()).thenReturn(allUsers);
		assertSame(userController.getUsers(), allUsers);
		verify(userService, times(1)).findAllUsers();
	}

}
