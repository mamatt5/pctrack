package com.fdmgroup.PCTrack.ControllerTests;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.fdmgroup.PCTrack.controller.UserController;
import com.fdmgroup.PCTrack.model.Staff;
import com.fdmgroup.PCTrack.model.User;
import com.fdmgroup.PCTrack.service.StaffService;
import com.fdmgroup.PCTrack.service.UserService;

import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
	User user;
	Page<User> page;
	
	@BeforeEach
	void setup() {
		this.userController = new UserController(userService, staffService);
	}
	
	@Test
	void findUserById_test() {
		
		User user1 = new User("amumu.munsen", "password123", "Amumu", "Munsen", LocalDate.of(2023, 11, 27),
				"amumu.munsen@example.com");
		
		when(userService.findUserId(1)).thenReturn(user1);
		assertSame(user1, userController.findById(1));
		verify(userService, times(1)).findUserId(1);
	}
	
	@Test
	void createUser_test() {
		User user1 = new User("amumu.munsen", "password123", "Amumu", "Munsen", LocalDate.of(2023, 11, 27),
				"amumu.munsen@example.com");
		
		userController.createNewUser(user1);
		verify(userService, times(1)).register(user1);
	}
	
	@Test
	void updateUser_test() {
		User user1 = new User("amumu.munsen", "password123", "Amumu", "Munsen", LocalDate.of(2023, 11, 27),
				"amumu.munsen@example.com");
		User updatedUser = new User("updated.updated", "updated123", "updated", "updated", LocalDate.of(2023, 11, 27),
				"amumu.munsen@example.com");
		
	
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
		User user1 = new User("amumu.munsen", "password123", "Amumu", "Munsen", LocalDate.of(2023, 11, 27),
				"amumu.munsen@example.com");
		User user2 = new User("aatrox.damion", "password123", "Aatrox", "Damion", "aatrox.damion@example.com");
		User user3 = new User("ahri.foxian", "password123", "Ahri", "Foxian", "ahri.foxian@example.com");
		User user4 = new User("andy.joe", "password123", "Andy", "Joe", "andy.joe@example.com");

		
		List<User> allUsers = new ArrayList<>();
		allUsers.add(user1);
		allUsers.add(user2);
		allUsers.add(user3);
		allUsers.add(user4);
		
		when(userService.findAllUsers()).thenReturn(allUsers);
		assertSame(userController.getUsers(), allUsers);
		verify(userService, times(1)).findAllUsers();
	}
	
	@Test
	void find_user_by_email() {
		when(userService.findUserEmail("testing@testing.com")).thenReturn(user);
		assertEquals(user, userController.findByEmail("testing@testing.com"));
	}
	
	@Test
	void user_count() {
		when(userService.userCountPartial("test")).thenReturn((long) 10);
		assertEquals((long) 10, userController.countUser("test"));
	}
	
	@Test
	void staff_count() {
		when(userService.userCount()).thenReturn((long) 10);
		assertEquals((long) 10, userController.countStaff());
	}
	
	@Test
	void find_by_username() {
		when(userService.findByUsername("test")).thenReturn(user);
		assertEquals(user, userController.findByUsername("test"));
	}
	
	@Test
	void get_paginated_users() {
		List<User> paginatedUserList = new ArrayList<>();
		page = new PageImpl<User>(paginatedUserList);
		when(userService.getUserPage(1, 10)).thenReturn(page);
		assertEquals(paginatedUserList, userController.getStaffPage(1, 10));
	}
	
	@Test
	void get_paginated_partial_users() {
		List<User> paginatedUserList = new ArrayList<>();
		page = new PageImpl<User>(paginatedUserList);
		when(userService.findAllUsersPartialMatch("test", 1, 10)).thenReturn(page);
		assertEquals(paginatedUserList, userController.getStaffPartial("test",1, 10));
	}
	
	@Test
	void count_partial_users() {
		when(userService.userCountPartial("test")).thenReturn((long) 10);
		assertEquals((long) 10, userController.countUser("test"));
	}
	
	@Test
	void delete_user() {
		List<Staff> staffList = new ArrayList<>();
		staffList.add(new Staff());
		when(staffService.findByUserId(0)).thenReturn(staffList);
		userController.deleteUser(0);
		verify(staffService, times(1)).findByUserId(0);
		verify(userService, times(1)).deleteByUserId(0);
	}
	
	@Test
	void throws_when_username_exists() {
		user = new User();
		user.setUsername("test");
		when(userService.existsByUsername("test")).thenReturn(true);
		assertThrows(RuntimeException.class, ()-> userController.createNewUser(user));
	}

}
