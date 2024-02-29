package com.fdmgroup.PCTrack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fdmgroup.PCTrack.model.Computer;
import com.fdmgroup.PCTrack.service.ComputerService;

@RestController
@CrossOrigin("http://localhost:5173")
public class ComputerController {
	
	private ComputerService computerService;
	
	@Autowired
	public ComputerController(ComputerService computerService) {
		super();
		this.computerService = computerService;
	}
	
	@GetMapping("computers")
	public List<Computer> getComputers() {
		return computerService.findAllComputers();
	}
	
	@GetMapping("computers/{computerId}")
	public Computer findById(@PathVariable int computerId) {
		return computerService.findById(computerId);
	}
	
	@PostMapping("computers")
	public Computer createNewComputer(@RequestBody Computer newComputer) {
		computerService.save(newComputer);
		return computerService.findById(newComputer.getComputerId());
	}
	
	@PutMapping("computers")
	public Computer updateComputer(@RequestBody Computer newComputer) {
		computerService.update(newComputer);
		return computerService.findById(newComputer.getComputerId());
	}
	
	@DeleteMapping("computers/{computerId}")
	public void deleteComputer(@PathVariable int computerId) {
		computerService.deleteByComputerId(computerId);
	}

}
