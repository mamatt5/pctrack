package com.fdmgroup.PCTrack.ControllerTests;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PCTrack.controller.ProgramController;
import com.fdmgroup.PCTrack.controller.SoftwareController;
import com.fdmgroup.PCTrack.model.Location;
import com.fdmgroup.PCTrack.model.Software;
import com.fdmgroup.PCTrack.service.ProgramService;
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
	@Mock
	SoftwareService programService;
	
	ProgramController programController;
	
	@BeforeEach
	void setup() {
		this.programController = new ProgramController(softwareService, programService);
	}
	
	@Test
	void findProgramById_test() {
		
		Software vscode = new Software("Visual Studio Code");
		
		when(softwareService.findById(1)).thenReturn(vscode);
		assertSame(vscode, programController.findById(1));
		verify(softwareService, times(1)).findById(1);
	}
	
	@Test
	void createProgram_test() {
		Software excel = new Software("Excel");
		
		when(softwareService.findById(0)).thenReturn(excel);
		assertSame(excel, programController.createNewProgram(excel));
		verify(softwareService, times(1)).save(excel);
	}
	
	@Test
	void updateProgram_test() {
		Software git = new Software("Git");
		Software updatedGit = new Software("Git");

		when(softwareService.findById(0)).thenReturn(updatedGit);
		assertSame(programController.updateProgram(git), updatedGit);
		verify(softwareService, times(1)).update(git);
	}
	
	@Test
	void deleteProgram_test() {
		programController.deleteProgram(1);
		verify(softwareService, times(1)).deleteById(1);
	}
	
	@Test
	void findAllPrograms() {
		Software vscode = new Software("Visual Studio Code");
		Software eclipse = new Software("Eclipse");
		Software nodejs = new Software("Node.js");
		Software python = new Software("Python Laucher");
		Software npm = new Software("NPM");
		Software sql8wb = new Software("MySQL 8 Workbench");
		Software sqlShell = new Software("MySQL Shell");
		Software powerBi = new Software("PowerBi");


		
		List<Software> allPrograms = new ArrayList<>();
		allPrograms.add(vscode);
		allPrograms.add(eclipse);
		allPrograms.add(nodejs);
		allPrograms.add(python);
		allPrograms.add(npm);
		allPrograms.add(sql8wb);
		allPrograms.add(sqlShell);
		allPrograms.add(powerBi);
		
		when(softwareService.findAllPrograms()).thenReturn(allPrograms);
		assertSame(programController.getPrograms(), allPrograms);
		verify(softwareService, times(1)).findAllPrograms();
	}
}
