package com.fdmgroup.PCTrack.ControllerTests;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PCTrack.controller.ComputerController;
import com.fdmgroup.PCTrack.controller.LocationController;
import com.fdmgroup.PCTrack.model.Computer;
import com.fdmgroup.PCTrack.model.Location;
import com.fdmgroup.PCTrack.model.Software;
import com.fdmgroup.PCTrack.model.Program;
import com.fdmgroup.PCTrack.model.Room;
import com.fdmgroup.PCTrack.model.Version;
import com.fdmgroup.PCTrack.service.ComputerService;
import com.fdmgroup.PCTrack.service.LocationService;


@ExtendWith(MockitoExtension.class)
public class ComputerControllerTests {
	
	@Mock
	ComputerService computerService;
	
	ComputerController computerController;
	Software vscodeProgram;
	Software eclipseProgram;
	Software nodejsProgram;
	Version v1;
	Version v2;
	Version v3;
	Program vscode;
	Program eclipse;
	Program nodejs;
	Room room1;
    Room room2;
    Room room3;
    Location location1;
     
     
	@BeforeEach
	void setup() {
		this.computerController = new ComputerController(computerService);
		vscodeProgram = new Software("Visual Studio Code");
		eclipseProgram = new Software("Eclipse");
		nodejsProgram = new Software("Node.js");
		v1 = new Version("1.46.1");
		v2 = new Version("4.22");
		v3 = new Version("20.11.0");
		vscode = new Program(vscodeProgram,v1);
		eclipse = new Program(eclipseProgram,v2);
		nodejs = new Program(nodejsProgram,v3);
		location1 = new Location("FDM Sydney", "Sydney");
		room1 = new Room("Bondi", location1);
	    room2 = new Room("Coogee", location1);
	    room3 = new Room("Balmoral",location1);
	}
	
	@Test
	void findComputerById_test() {
		
		Computer computer1 = new Computer(15037, room1);
		computer1.setProgramList(Arrays.asList(vscode, eclipse,nodejs));
		computerService.save(computer1);
		
		when(computerService.findById(1)).thenReturn(computer1);
		assertSame(computer1, computerController.findById(1));
		verify(computerService, times(1)).findById(1);
	}
	
	@Test
	void createComputer_test() {

		Computer computer1 = new Computer(15037, room1);
		computer1.setProgramList(Arrays.asList(vscode, eclipse,nodejs));
		
		when(computerService.findById(0)).thenReturn(computer1);
		assertSame(computer1, computerController.createNewComputer(computer1));
		verify(computerService, times(1)).save(computer1);
	}
	
	@Test
	void updateComputer_test() {
		
		Computer computer1 = new Computer(15037, room1);
		computer1.setProgramList(Arrays.asList(vscode, eclipse,nodejs));
		
		Computer updatedComputer1 = new Computer(15037, room1);
		updatedComputer1.setProgramList(Arrays.asList(vscode));

		when(computerService.findById(0)).thenReturn(updatedComputer1);
		assertSame(computerController.updateComputer(computer1), updatedComputer1);
		verify(computerService, times(1)).update(computer1);
	}
	
	@Test
	void deleteComputer_test() {
		computerController.deleteComputer(1);
		verify(computerService, times(1)).deleteByComputerId(1);
	}
	
	@Test
	void findAllComputers() {
		Computer c1 = new Computer(15040, room1);
		Computer c2 = new Computer(70156, room1);
		Computer c3 = new Computer(15046, room1);
		Computer c4 = new Computer(15068, room1);
		Computer c5 = new Computer(15048, room1);
	
		
		List<Computer> allComputers = new ArrayList<>();
		allComputers.add(c1);
		allComputers.add(c2);
		allComputers.add(c3);
		allComputers.add(c4);
		allComputers.add(c5);
		
		when(computerService.findAllComputers()).thenReturn(allComputers);
		assertSame(computerController.getComputers(), allComputers);
		verify(computerService, times(1)).findAllComputers();
	}
}
