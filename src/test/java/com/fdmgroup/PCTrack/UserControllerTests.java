package com.fdmgroup.PCTrack;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PCTrack.controller.UserController;
import com.fdmgroup.PCTrack.model.User;
import com.fdmgroup.PCTrack.service.UserService;

import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests {
	
	@Mock
	UserService userService;
	
	UserController userController;
	
	@BeforeEach
	void setup() {
		this.userController = new UserController(userService);
	}
	
	@Test
	void findUserById_test() {
		
		User user1 = new User("John", "Smith", "johnsmith@live.com", "johnsmith1", "password123");
		
		when(userService.findUserId(1)).thenReturn(user1);
		User foundUser = userController.findUserId(1);
		assertSame(user1, foundUser);
		verify(userService, times(1)).findUserId(1);
	}
	
	@Test
	void createUser_test() {
		User user1 = new User("John", "Smith", "johnsmith@live.com", "johnsmith1", "password123");
		
		userController.createNewUser(user1);
		verify(userService, times(1)).register(user1);
	}
	
	@Test
	void updateUser_test() {
		User user1 = new User("John", "Smith", "johnsmith@live.com", "johnsmith1", "password123");
		
		userController.updateUser(user1);
		verify(userService, times(1)).update(user1);
	}
	
	@Test
	void deleteUser_test() {
		userController.deleteUser(1);
		verify(userService, times(1)).deleteByUserId(1);
	}
	
	@Test
	void findAllUsers() {
		User user1 = new User("John", "Smith", "johnsmith@live.com", "johnsmith1", "password123");
		User user2 = new User("Jane", "Doe", "janedoe@live.com", "janedoe3", "pass123 ");
		User user3 = new User("Mike", "Yao", "mikeyao@live.com", "mikeyao", "java23");
		User user4 = new User("Rachel", "Perry", "rachelperry@live.com", "rperry1", "moo89");
		
		List<User> allUsers = new ArrayList<>();
		allUsers.add(user1);
		allUsers.add(user2);
		allUsers.add(user3);
		allUsers.add(user4);
		
		when(userService.findAllUsers()).thenReturn(allUsers);
		assertSame(userController.findAllUsers(), allUsers);
		verify(userService, times(1)).findAllUsers();
	}

}
