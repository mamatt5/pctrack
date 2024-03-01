package com.fdmgroup.PCTrack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fdmgroup.PCTrack.model.Version;
import com.fdmgroup.PCTrack.service.ProgramVersionService;
import com.fdmgroup.PCTrack.service.VersionService;

@RestController
@CrossOrigin("http://localhost:5813")
public class VersionController {
	private VersionService versionService;
	private ProgramVersionService programVersionService;
	
	@Autowired
	public VersionController(VersionService versionService) {
		super();
		this.versionService = versionService;
	}
	
	@GetMapping("versions")
	public List<Version> getVersions() {
		return versionService.findAllVersions();
	}
	
	@GetMapping("versions/{versionId}")
	public Version findById(@PathVariable int versionId) {
		return versionService.findById(versionId);
	}
	
	@PostMapping("versions")
	public Version createNewVersion(@RequestBody Version newVersion) {
		versionService.save(newVersion);
		return versionService.findById(newVersion.getVersionId());
	}
	
	@PutMapping("versions")
	public Version updateVersion(@RequestBody Version newVersion) {
		versionService.update(newVersion);
		return versionService.findById(newVersion.getVersionId());
	}
	
	@DeleteMapping("versions/{versionId}")
	public void deleteVersion(@PathVariable int versionId) {
		versionService.deleteById(versionId);
	}

}
