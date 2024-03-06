package com.fdmgroup.PCTrack.ServiceTests;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fdmgroup.PCTrack.dal.ComputerRepository;
import com.fdmgroup.PCTrack.dal.UserRepository;
import com.fdmgroup.PCTrack.model.Computer;
import com.fdmgroup.PCTrack.model.Location;
import com.fdmgroup.PCTrack.model.Room;
import com.fdmgroup.PCTrack.model.User;
import com.fdmgroup.PCTrack.service.ComputerService;
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
public class ComputerServiceTests {
	
	
	@Mock
	ComputerRepository computerRepo;
	
	ComputerService computerService;
	Location location1;
	Room room1;
	
	@BeforeEach
	void setup() {
		
		this.computerService = new ComputerService(computerRepo);
		location1 = new Location("FDM Sydney", "Sydney");
		room1 = new Room("Bondi", location1);

	}
	
	@Test
	void save_computer_test() {
		
		Computer computer1 = new Computer(15040, room1);
		
		computerService.save(computer1);
		verify(computerRepo, times(1)).save(computer1);
	}
	
	@Test
	void save_multiple_computers_test() {
		

		Computer computer1 = new Computer(15040, room1);
		Computer computer2 = new Computer(70156, room1);
		Computer computer3 = new Computer(15046, room1);
		Computer computer4 = new Computer(15068, room1);
		
      
		
        computerService.save(computer1);
		verify(computerRepo, times(1)).save(computer1);
		
		computerService.save(computer2);
		verify(computerRepo, times(1)).save(computer2);
		
		computerService.save(computer3);
		verify(computerRepo, times(1)).save(computer3);
		
		computerService.save(computer4);
		verify(computerRepo, times(1)).save(computer4);
	}
	
	
	@Test
	void find_all_computer_test() {
		
	
		Computer computer1 = new Computer(15040, room1);
		Computer computer2 = new Computer(70156, room1);
		Computer computer3 = new Computer(15046, room1);
		Computer computer4 = new Computer(15068, room1);
        
		List<Computer> allComputers = new ArrayList<>();
		allComputers.add(computer1);
		allComputers.add(computer2);
		allComputers.add(computer3);
		allComputers.add(computer4);
		
		when(computerRepo.findAll()).thenReturn(allComputers);
		
		List<Computer> foundComputers = computerService.findAllComputers();
	
		verify(computerRepo, times(1)).findAll();
		assertSame(foundComputers, allComputers);
	}
	
	@Test
	void find_computer_by_id_test() {
		
		Optional<Computer> computer1 = Optional.of(new Computer(15040, room1));

		when(computerRepo.findById(1)).thenReturn(computer1);
		Computer foundComputer1 = computerService.findById(1);
		
		verify(computerRepo, times(1)).findById(1);
		assertSame(computer1.get(), foundComputer1);

	}
	
	@Test
	void find_computer_by_id_fail_test() {

		assertThrows(RuntimeException.class, () -> computerService.findById(1));
		verify(computerRepo, times(1)).findById(1);
	}
	
	@Test
	void update_computer_test() {
		
		Computer computer1 = new Computer(15040, room1);
		computer1.setComputerId(1);
		
		when(computerRepo.existsById(1)).thenReturn(true);
		computerService.update(computer1);
		
		verify(computerRepo, times(1)).existsById(1);
		verify(computerRepo, times(1)).save(computer1);

	}
	
	@Test
	void update_computer_fail_test() {
		
		Computer computer1 = new Computer(15040, room1);
		computer1.setComputerId(1);
		
		when(computerRepo.existsById(1)).thenReturn(false);
		
		assertThrows(RuntimeException.class, () -> computerService.update(computer1));
		verify(computerRepo, times(1)).existsById(1);
		verify(computerRepo, times(0)).save(computer1);
	}
	
	@Test
	void delete_computer_test() {
		Computer computer1 = new Computer(15040, room1);
		when(computerRepo.existsById(1)).thenReturn(true);
		when(computerRepo.findById(1)).thenReturn(Optional.of(computer1));
		computerService.deleteByComputerId(1);
		
		verify(computerRepo, times(1)).deleteById(1);

	}
	
	@Test
	void delete_computer_fail_test() {
		
		when(computerRepo.existsById(1)).thenReturn(false);
		
		assertThrows(RuntimeException.class, () -> computerService.deleteByComputerId(1));
		verify(computerRepo, times(1)).existsById(1);
		verify(computerRepo, times(0)).deleteById(1);
	}
}
