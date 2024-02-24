package com.fdmgroup.PCTrack.service;

import java.util.List;

import com.fdmgroup.PCTrack.dal.ComputerRepository;
import com.fdmgroup.PCTrack.model.Computer;

public class ComputerService {
	private ComputerRepository computerRepository;

	public ComputerService(ComputerRepository computerRepository) {
		super();
		this.computerRepository = computerRepository;
	}
	
	public List<Computer> findAllComputers() {
		return this.computerRepository.findAll();
	}
	
	public Computer findById(int computerId) {
		return this.computerRepository.findById(computerId).orElseThrow(()-> new RuntimeException("Computer not found"));
	}
	
	public void save(Computer newComputer) {
		if (this.computerRepository.existsById(newComputer.getComputerId())) {
			throw new RuntimeException("Computer already exists");
		
		} else {
			this.computerRepository.save(newComputer);
		}
	}
	
	public void deleteById(int computerId) {
		if (this.computerRepository.existsById(computerId)) {
			computerRepository.deleteById(computerId);
		}
	}
	
	public void update(Computer newComputer) {
		if (this.computerRepository.existsById(newComputer.getComputerId())) {
			this.computerRepository.save(newComputer);
		
		} else {
			throw new RuntimeException("Computer does not exist");
		}
	}

}
