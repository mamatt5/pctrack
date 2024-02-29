package com.fdmgroup.PCTrack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fdmgroup.PCTrack.model.Program;
import com.fdmgroup.PCTrack.service.ComputerService;
import com.fdmgroup.PCTrack.service.ProgramService;

@RestController
@CrossOrigin("http://localhost:5173")
public class ProgramController {
	private ProgramService programService;
	private ComputerService computerService;
	
	@Autowired
	public ProgramController(ProgramService programService, ComputerService computerService) {
		super();
		this.programService = programService;
		this.computerService = computerService;
	}
	
	@GetMapping("programs")
	public List<Program> getPrograms() {
		return programService.findAllPrograms();
	}
	
	@GetMapping("programs/{programId}")
	public Program findById(@PathVariable int programId) {
		return programService.findById(programId);
	}
	
	@PostMapping("programs")
	public Program createNewProgram(@RequestBody Program newProgram) {
		programService.save(newProgram);
		return programService.findById(newProgram.getProgramId());
	}
	
	@PutMapping("programs")
	public Program updateProgram(@RequestBody Program newProgram) {
		programService.update(newProgram);
		return programService.findById(newProgram.getProgramId());
	}
	
	@DeleteMapping("programs/{programId}")
	public void deleteProgram(@PathVariable int programId) {
		computerService.deleteByProgramId(programId);
		programService.deleteById(programId);
	}

}
