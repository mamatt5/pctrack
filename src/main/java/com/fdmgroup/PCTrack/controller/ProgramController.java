package com.fdmgroup.PCTrack.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.PCTrack.model.Program;
import com.fdmgroup.PCTrack.service.ProgramService;
@RestController
public class ProgramController {
	private ProgramService programService;

	public ProgramController(ProgramService programService) {
		super();
		this.programService = programService;
	}
	
	public List<Program> getPrograms() {
		return programService.findAllPrograms();
	}
	
	public Program findById(@PathVariable int programId) {
		return programService.findById(programId);
	}
	
	public Program createNewProgram(@RequestBody Program newProgram) {
		programService.save(newProgram);
		return programService.findById(newProgram.getProgramId());
	}
	
	public Program updateProgram(@RequestBody Program newProgram) {
		programService.update(newProgram);
		return programService.findById(newProgram.getProgramId());
	}
	
	public void deleteProgram(@PathVariable int programId) {
		programService.deleteById(programId);
	}

}
