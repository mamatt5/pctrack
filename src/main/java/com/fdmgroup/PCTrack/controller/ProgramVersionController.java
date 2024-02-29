package com.fdmgroup.PCTrack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fdmgroup.PCTrack.model.ProgramVersion;
import com.fdmgroup.PCTrack.service.ProgramVersionService;

@RestController
@CrossOrigin("http://localhost:5813")
public class ProgramVersionController {
	private ProgramVersionService programVersionService;
	
	@Autowired
	public ProgramVersionController(ProgramVersionService programVersionService) {
		super();
		this.programVersionService = programVersionService;
	}
	
	@GetMapping("programVersions")
	public List<ProgramVersion> getProgramVersions() {
		return programVersionService.findAllProgramVersions();
	}
	
	@GetMapping("programVersions/{programVersionId}")
	public ProgramVersion findById(@PathVariable int programVersionId) {
		return programVersionService.findById(programVersionId);
	}
	
	@PostMapping("programVersions")
	public ProgramVersion createNewProgramVersion(@RequestBody ProgramVersion newProgramVersion) {
		programVersionService.save(newProgramVersion);
		return programVersionService.findById(newProgramVersion.getProgramVersionId());
	}
	
	@PutMapping("programVersions")
	public ProgramVersion updateProgramVersion(@RequestBody ProgramVersion newProgramVersion) {
		programVersionService.update(newProgramVersion);
		return programVersionService.findById(newProgramVersion.getProgramVersionId());
	}
	
	@DeleteMapping("programVersions/{programVersionId}")
	public void deleteProgramVersion(@PathVariable int programVersionId) {
		programVersionService.deleteById(programVersionId);
	}

}
