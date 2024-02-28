package com.fdmgroup.PCTrack.ControllerTests;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PCTrack.controller.ProgramController;
import com.fdmgroup.PCTrack.model.Location;
import com.fdmgroup.PCTrack.model.Program;
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
	ProgramService programService;
	@Mock
	ComputerService computerService;
	
	ProgramController programController;
	
	@BeforeEach
	void setup() {
		this.programController = new ProgramController(programService, computerService);
	}
	
	@Test
	void findProgramById_test() {
		
		Program vscode = new Program("Visual Studio Code", "1.46.1");
		
		when(programService.findById(1)).thenReturn(vscode);
		assertSame(vscode, programController.findById(1));
		verify(programService, times(1)).findById(1);
	}
	
	@Test
	void createProgram_test() {
		Program excel = new Program("Excel", "2107");
		
		when(programService.findById(0)).thenReturn(excel);
		assertSame(excel, programController.createNewProgram(excel));
		verify(programService, times(1)).save(excel);
	}
	
	@Test
	void updateProgram_test() {
		Program git = new Program("Git", "2.27.0");
		Program updatedGit = new Program("Git", "3.27.0");

		when(programService.findById(0)).thenReturn(updatedGit);
		assertSame(programController.updateProgram(git), updatedGit);
		verify(programService, times(1)).update(git);
	}
	
	@Test
	void deleteProgram_test() {
		programController.deleteProgram(1);
		verify(programService, times(1)).deleteById(1);
	}
	
	@Test
	void findAllPrograms() {
		Program vscode = new Program("Visual Studio Code", "1.46.1");
		Program eclipse = new Program("Eclipse", "4.22");
		Program nodejs = new Program("Node.js", "20.11.0");
		Program python = new Program("Python Laucher", "3.9.7427.0");
		Program npm = new Program("NPM", "10.2.4");
		Program sql8wb = new Program("MySQL 8 Workbench", "8.0.32");
		Program sqlShell = new Program("MySQL Shell", "8.0.32");
		Program powerBi = new Program("PowerBi", "2.126.927.0");


		
		List<Program> allPrograms = new ArrayList<>();
		allPrograms.add(vscode);
		allPrograms.add(eclipse);
		allPrograms.add(nodejs);
		allPrograms.add(python);
		allPrograms.add(npm);
		allPrograms.add(sql8wb);
		allPrograms.add(sqlShell);
		allPrograms.add(powerBi);
		
		when(programService.findAllPrograms()).thenReturn(allPrograms);
		assertSame(programController.getPrograms(), allPrograms);
		verify(programService, times(1)).findAllPrograms();
	}
}
