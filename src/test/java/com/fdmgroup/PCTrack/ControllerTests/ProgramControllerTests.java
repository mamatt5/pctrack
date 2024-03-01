package com.fdmgroup.PCTrack.ControllerTests;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PCTrack.controller.ProgramController;
import com.fdmgroup.PCTrack.model.Location;
import com.fdmgroup.PCTrack.model.Software;
import com.fdmgroup.PCTrack.model.Program;
import com.fdmgroup.PCTrack.model.Version;
import com.fdmgroup.PCTrack.service.ComputerService;
import com.fdmgroup.PCTrack.service.ProgramService;

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
public class ProgramControllerTests {
	
	@Mock
	ProgramService programVersionService;
	@Mock
	ComputerService computerService;
	
	ProgramController programVersionController;
	
	@BeforeEach
	void setup() {
		this.programVersionController = new ProgramController(programVersionService, computerService);
	}
	
	@Test
	void findProgramVersionById_test() {
		
		Program vscode = new Program(new Software("Visual Studio Code"),"1.46.1");
		
		when(programVersionService.findById(1)).thenReturn(vscode);
		assertSame(vscode, programVersionController.findById(1));
		verify(programVersionService, times(1)).findById(1);
	}
	
	@Test
	void createProgramVersion_test() {
		Program excel = new Program(new Software("Excel"), "2107");
		
		when(programVersionService.findById(0)).thenReturn(excel);
		assertSame(excel, programVersionController.createNewProgramVersion(excel));
		verify(programVersionService, times(1)).save(excel);
	}
	
	@Test
	void updateProgramVersion_test() {
		Program git = new Program(new Software("Git"), new Version("2.27.0"));
		Program updatedGit = new Program(new Software("Git"), new Version("3.27.0"));

		when(programVersionService.findById(0)).thenReturn(updatedGit);
		assertSame(programVersionController.updateProgramVersion(git), updatedGit);
		verify(programVersionService, times(1)).update(git);
	}
	
	@Test
	void deleteProgramVersion_test() {
		programVersionController.deleteProgramVersion(1);
		verify(programVersionService, times(1)).deleteById(1);
	}
	
	@Test
	void findAllProgramVersions() {
		Program vscode = new Program(new Software("Visual Studio Code"), new Version("1.46.1"));
		Program eclipse = new Program(new Software("Eclipse"), new Version("4.22"));
		Program nodejs = new Program(new Software("Node.js"), new Version("20.11.0"));
		Program python = new Program(new Software("Python Laucher"), new Version("3.9.7427.0"));
		Program npm = new Program(new Software("NPM"), new Version("10.2.4"));
		Program sql8wb = new Program(new Software("MySQL 8 Workbench"), new Version("8.0.32"));
		Program sqlShell = new Program(new Software("MySQL Shell"), new Version("8.0.32"));
		Program powerBi = new Program(new Software("PowerBi"), new Version("2.126.927.0"));


		
		List<Program> allProgramVersions = new ArrayList<>();
		allProgramVersions.add(vscode);
		allProgramVersions.add(eclipse);
		allProgramVersions.add(nodejs);
		allProgramVersions.add(python);
		allProgramVersions.add(npm);
		allProgramVersions.add(sql8wb);
		allProgramVersions.add(sqlShell);
		allProgramVersions.add(powerBi);
		
		when(programVersionService.findAllProgramVersions()).thenReturn(allProgramVersions);
		assertSame(programVersionController.getProgramVersions(), allProgramVersions);
		verify(programVersionService, times(1)).findAllProgramVersions();
	}
}
