package com.fdmgroup.PCTrack.ControllerTests;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PCTrack.controller.ProgramVersionController;
import com.fdmgroup.PCTrack.model.Location;
import com.fdmgroup.PCTrack.model.Program;
import com.fdmgroup.PCTrack.model.ProgramVersion;
import com.fdmgroup.PCTrack.model.Version;
import com.fdmgroup.PCTrack.service.ComputerService;
import com.fdmgroup.PCTrack.service.ProgramVersionService;

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
public class ProgramVersionControllerTests {
	
	@Mock
	ProgramVersionService programVersionService;
	@Mock
	ComputerService computerService;
	
	ProgramVersionController programVersionController;
	
	@BeforeEach
	void setup() {
		this.programVersionController = new ProgramVersionController(programVersionService, computerService);
	}
	
	@Test
	void findProgramVersionById_test() {
		
		ProgramVersion vscode = new ProgramVersion(new Program("Visual Studio Code"), new Version("1.46.1"));
		
		when(programVersionService.findById(1)).thenReturn(vscode);
		assertSame(vscode, programVersionController.findById(1));
		verify(programVersionService, times(1)).findById(1);
	}
	
	@Test
	void createProgramVersion_test() {
		ProgramVersion excel = new ProgramVersion(new Program("Excel"), new Version("2107"));
		
		when(programVersionService.findById(0)).thenReturn(excel);
		assertSame(excel, programVersionController.createNewProgramVersion(excel));
		verify(programVersionService, times(1)).save(excel);
	}
	
	@Test
	void updateProgramVersion_test() {
		ProgramVersion git = new ProgramVersion(new Program("Git"), new Version("2.27.0"));
		ProgramVersion updatedGit = new ProgramVersion(new Program("Git"), new Version("3.27.0"));

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
		ProgramVersion vscode = new ProgramVersion(new Program("Visual Studio Code"), new Version("1.46.1"));
		ProgramVersion eclipse = new ProgramVersion(new Program("Eclipse"), new Version("4.22"));
		ProgramVersion nodejs = new ProgramVersion(new Program("Node.js"), new Version("20.11.0"));
		ProgramVersion python = new ProgramVersion(new Program("Python Laucher"), new Version("3.9.7427.0"));
		ProgramVersion npm = new ProgramVersion(new Program("NPM"), new Version("10.2.4"));
		ProgramVersion sql8wb = new ProgramVersion(new Program("MySQL 8 Workbench"), new Version("8.0.32"));
		ProgramVersion sqlShell = new ProgramVersion(new Program("MySQL Shell"), new Version("8.0.32"));
		ProgramVersion powerBi = new ProgramVersion(new Program("PowerBi"), new Version("2.126.927.0"));


		
		List<ProgramVersion> allProgramVersions = new ArrayList<>();
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
