package com.fdmgroup.PCTrack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fdmgroup.PCTrack.model.Program;
import com.fdmgroup.PCTrack.service.ProgramService;
import com.fdmgroup.PCTrack.service.SoftwareService;

@RestController
@CrossOrigin("http://localhost:5813")
public class ProgramController {
	private ProgramService programService;
	
	@Autowired
	public ProgramController(ProgramService programService, SoftwareService softwareService) {
		super();
		this.programService = programService;
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
		programService.deleteById(programId);
	}
	
//	@DeleteMapping("programs/{programId}")
//	public void deleteProgram(@PathVariable int programId) {
//		computerService.deleteByProgramId(programId);
//		programService.deleteById(programId);
//	}

}
