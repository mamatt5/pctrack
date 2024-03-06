package com.fdmgroup.PCTrack.ServiceTests;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fdmgroup.PCTrack.dal.UserRepository;
import com.fdmgroup.PCTrack.model.User;
import com.fdmgroup.PCTrack.service.UserService;

import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
	@Mock
	UserRepository userRepo;
	
	@Mock
	PasswordEncoder passwordEncoder;
	
	UserService userService;
	
	@BeforeEach
	void setup() {
		
		this.userService = new UserService(userRepo, passwordEncoder);

	}
	
	@Test
	void save_user_test() {
		
		User user1 = new User("amumu.munsen", "password123", "Amumu", "Munsen", LocalDate.of(2023, 11, 27),
				"amumu.munsen@example.com");
		
		userService.register(user1);
		verify(userRepo, times(1)).save(user1);
	}
	
	@Test
	void save_multiple_users_test() {
		

		User user1 = new User("amumu.munsen", "password123", "Amumu", "Munsen", LocalDate.of(2023, 11, 27),
				"amumu.munsen@example.com");
		User user2 = new User("aatrox.damion", "password123", "Aatrox", "Damion", "aatrox.damion@example.com");
		User user3 = new User("ahri.foxian", "password123", "Ahri", "Foxian", "ahri.foxian@example.com");
		User user4 = new User("andy.joe", "password123", "Andy", "Joe", "andy.joe@example.com");

		
		userService.register(user1);
		verify(userRepo, times(1)).save(user1);
		
		userService.register(user2);
		verify(userRepo, times(1)).save(user2);
		
		userService.register(user3);
		verify(userRepo, times(1)).save(user3);
		
		userService.register(user4);
		verify(userRepo, times(1)).save(user4);
	}
	
	@Test
	void save_already_existing_user_test() {
		

		User user1 = new User("amumu.munsen", "password123", "Amumu", "Munsen", LocalDate.of(2023, 11, 27),
				"amumu.munsen@example.com");
		User duplicateUser1 = new User("amumu.munsen", "password123", "Amumu", "Munsen", LocalDate.of(2023, 11, 27),
				"amumu.munsen@example.com");
		
		RuntimeException exception = new RuntimeException("Username already exists");
		
		
		userService.register(user1);
		verify(userRepo, times(1)).save(user1);

		doThrow(exception).when(userRepo).save(duplicateUser1);
		assertThrows(RuntimeException.class, () -> userService.register(duplicateUser1));
	}
	
	@Test
	void find_all_user_test() {
		
	
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
		
		when(userRepo.findAll()).thenReturn(allUsers);
		
		List<User> foundUsers = userService.findAllUsers();
	
		verify(userRepo, times(1)).findAll();
		assertSame(foundUsers, allUsers);
	}
	
	@Test
	void find_user_by_id_test() {
		
		Optional<User> user1 = Optional.of(new User("andy.joe", "password123", "Andy", "Joe", "andy.joe@example.com"));

		when(userRepo.findById(1)).thenReturn(user1);
		User foundUser1 = userService.findUserId(1);
		
		verify(userRepo, times(1)).findById(1);
		assertSame(user1.get(), foundUser1);

	}
	
	@Test
	void find_user_by_id_fail_test() {

		assertThrows(RuntimeException.class, () -> userService.findUserId(1));
		verify(userRepo, times(1)).findById(1);
	}
	
	@Test
	void update_user_test() {
		
		User user1 = new User("amumu.munsen", "password123", "Amumu", "Munsen", LocalDate.of(2023, 11, 27),
				"amumu.munsen@example.com");
		user1.setUserId(1);
		
		when(userRepo.existsById(1)).thenReturn(true);
		userService.update(user1);
		
		verify(userRepo, times(1)).existsById(1);
		verify(userRepo, times(1)).save(user1);

	}
	
	@Test
	void update_user_fail_test() {
		
		User user1 = new User("amumu.munsen", "password123", "Amumu", "Munsen", LocalDate.of(2023, 11, 27),
				"amumu.munsen@example.com");
		user1.setUserId(1);
		
		when(userRepo.existsById(1)).thenReturn(false);
		
		assertThrows(RuntimeException.class, () -> userService.update(user1));
		verify(userRepo, times(1)).existsById(1);
		verify(userRepo, times(0)).save(user1);
	}
	
	@Test
	void delete_user_test() {
		
		when(userRepo.existsById(1)).thenReturn(true);
		
		userService.deleteByUserId(1);
		
		verify(userRepo, times(1)).existsById(1);
		verify(userRepo, times(1)).deleteById(1);

	}
	
	@Test
	void delete_user_fail_test() {
		
		when(userRepo.existsById(1)).thenReturn(false);
		
		assertThrows(RuntimeException.class, () -> userService.deleteByUserId(1));
		verify(userRepo, times(1)).existsById(1);
		verify(userRepo, times(0)).deleteById(1);
	}

}
