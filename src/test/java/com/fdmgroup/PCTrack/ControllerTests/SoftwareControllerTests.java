package com.fdmgroup.PCTrack.ControllerTests;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PCTrack.controller.SoftwareController;
import com.fdmgroup.PCTrack.controller.SoftwareController;
import com.fdmgroup.PCTrack.model.Location;
import com.fdmgroup.PCTrack.model.Software;
import com.fdmgroup.PCTrack.service.SoftwareService;
import com.fdmgroup.PCTrack.service.SoftwareService;

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
public class SoftwareControllerTests {
	
	@Mock
	SoftwareService softwareService;
	
	SoftwareController softwareController;
	
	@BeforeEach
	void setup() {
		this.softwareController = new SoftwareController(softwareService);
	}
	
	@Test
	void findSoftwareById_test() {
		
		Software vscode = new Software("Visual Studio Code");
		
		when(softwareService.findById(1)).thenReturn(vscode);
		assertSame(vscode, softwareController.findById(1));
		verify(softwareService, times(1)).findById(1);
	}
	
	@Test
	void createSoftware_test() {
		Software excel = new Software("Excel");
		
		when(softwareService.findById(0)).thenReturn(excel);
		assertSame(excel, softwareController.createNewSoftware(excel));
		verify(softwareService, times(1)).save(excel);
	}
	
	@Test
	void updateSoftware_test() {
		Software git = new Software("Git");
		Software updatedGit = new Software("Git");

		when(softwareService.findById(0)).thenReturn(updatedGit);
		assertSame(softwareController.updateSoftware(git), updatedGit);
		verify(softwareService, times(1)).update(git);
	}
	
	@Test
	void deleteSoftware_test() {
		softwareController.deleteSoftware(1);
		verify(softwareService, times(1)).deleteById(1);
	}
	
	@Test
	void findAllSoftwares() {
		Software vscode = new Software("Visual Studio Code");
		Software eclipse = new Software("Eclipse");
		Software nodejs = new Software("Node.js");
		Software python = new Software("Python Laucher");
		Software npm = new Software("NPM");
		Software sql8wb = new Software("MySQL 8 Workbench");
		Software sqlShell = new Software("MySQL Shell");
		Software powerBi = new Software("PowerBi");


		
		List<Software> allSoftwares = new ArrayList<>();
		allSoftwares.add(vscode);
		allSoftwares.add(eclipse);
		allSoftwares.add(nodejs);
		allSoftwares.add(python);
		allSoftwares.add(npm);
		allSoftwares.add(sql8wb);
		allSoftwares.add(sqlShell);
		allSoftwares.add(powerBi);
		
		when(softwareService.findAllSoftwares()).thenReturn(allSoftwares);
		assertSame(softwareController.getSoftwares(), allSoftwares);
		verify(softwareService, times(1)).findAllSoftwares();
	}
}
