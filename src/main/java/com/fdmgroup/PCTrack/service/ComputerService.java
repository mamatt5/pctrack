package com.fdmgroup.PCTrack.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fdmgroup.PCTrack.dal.ComputerRepository;
import com.fdmgroup.PCTrack.model.Computer;

@Service
public class ComputerService {
	private ComputerRepository computerRepo;
	
	public ComputerService(ComputerRepository computerRepo) {
		super();
		this.computerRepo = computerRepo;
	}
	
	public List<Computer> findAllComputers() {
		return this.computerRepo.findAll();
	}
	
	public Computer findById(int computerId) {
		return this.computerRepo.findById(computerId).orElseThrow(() -> new RuntimeException("Computer not found"));
	}
	
	public void save(Computer newComputer) {
		if (this.computerRepo.existsById(newComputer.getComputerId())) {
			throw new RuntimeException("Computer already exists");
		} else {
			this.computerRepo.save(newComputer);
		}
	}
}
