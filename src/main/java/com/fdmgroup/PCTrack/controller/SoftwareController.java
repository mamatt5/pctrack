package com.fdmgroup.PCTrack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fdmgroup.PCTrack.model.Software;
import com.fdmgroup.PCTrack.service.SoftwareService;

@RestController
@CrossOrigin("http://localhost:5173")
public class SoftwareController {
	private SoftwareService softwareService;
	
	@Autowired
	public SoftwareController(SoftwareService softwareService) {
		super();
		this.softwareService = softwareService;
	}
	
	@GetMapping("softwares")
	public List<Software> getSoftwares() {
		return softwareService.findAllSoftwares();
	}
	
	@GetMapping("softwares/{softwareId}")
	public Software findById(@PathVariable int softwareId) {
		return softwareService.findById(softwareId);
	}
	
	@PostMapping("softwares")
	public Software createNewSoftware(@RequestBody Software newSoftware) {
		softwareService.save(newSoftware);
		return softwareService.findById(newSoftware.getSoftwareId());
	}
	
	@PutMapping("softwares")
	public Software updateSoftware(@RequestBody Software newSoftware) {
		softwareService.update(newSoftware);
		return softwareService.findById(newSoftware.getSoftwareId());
	}
	
	@DeleteMapping("softwares/{softwareId}")
	public void deleteSoftware(@PathVariable int softwareId) {
		softwareService.deleteById(softwareId);
	}

}
