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
	
	public List<Computer> searchByComputerCode(String code, String roomId, String role) {
		return this.computerRepo.searchComputer(code, roomId, role);
	}
	
	public List<Computer> findByProgramId(int programId) {
		return this.computerRepo.findByProgramList_ProgramId(programId);
	}
	
	public void save(Computer newComputer) {
		if (this.computerRepo.existsById(newComputer.getComputerId())) {
			throw new RuntimeException("Computer already exists");
		} else {
			this.computerRepo.save(newComputer);
		}
	}
	
	public void deleteByComputerId(int computerId) {
		if (this.computerRepo.existsById(computerId)) {
			findById(computerId).setProgramList(null);
			computerRepo.deleteById(computerId);
		} else {
			throw new RuntimeException("Computer does not exist");
		}
	}
	
	public void deleteByProgramId(int programId) {
		for (Computer computer : findAllComputers()) {
			int index = computer.programIndex(programId);
			if (index != -1) {
				computer.getProgramList().remove(index);
			}
		}
	}
	
	public void update(Computer newComputer) {
		if (this.computerRepo.existsById(newComputer.getComputerId())) {
			this.computerRepo.save(newComputer);
		
		} else {
			throw new RuntimeException("Computer does not exist");
		}
	}
}
