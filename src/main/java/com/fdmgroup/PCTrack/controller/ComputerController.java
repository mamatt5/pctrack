package com.fdmgroup.PCTrack.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.fdmgroup.PCTrack.model.Computer;
import com.fdmgroup.PCTrack.service.ComputerService;

public class ComputerController {
	private ComputerService computerService;

	public ComputerController(ComputerService computerService) {
		super();
		this.computerService = computerService;
	}
	
	public List<Computer> getComputers() {
		return computerService.findAllComputers();
	}
	
	public Computer findById(@PathVariable int computerId) {
		return computerService.findById(computerId);
	}
	
	public Computer createNewComputer(@RequestBody Computer newComputer) {
		computerService.save(newComputer);
		return computerService.findById(newComputer.getComputerId());
	}
	
	public Computer updateComputer(@RequestBody Computer newComputer) {
		computerService.update(newComputer);
		return computerService.findById(newComputer.getComputerId());
	}
	
	public void deleteComputer(@PathVariable int computerId) {
		computerService.deleteById(computerId);
	}

}
