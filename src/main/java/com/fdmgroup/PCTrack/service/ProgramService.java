package com.fdmgroup.PCTrack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fdmgroup.PCTrack.dal.ProgramRepository;
import com.fdmgroup.PCTrack.model.Program;

@Service
public class ProgramService {
	private ProgramRepository programRepo;
	
	@Autowired
	public ProgramService(ProgramRepository programRepo) {
		super();
		this.programRepo = programRepo;
	}
	public List<Program> findAllPrograms() {
		return this.programRepo.findAll();
	}
	
	public Program findById(int programId) {
		return this.programRepo.findById(programId).orElseThrow(() -> new RuntimeException("Program not found"));
	}
	
	public void save(Program newProgram) {
		if (this.programRepo.existsById(newProgram.getProgramId())) {
			throw new RuntimeException("Program already exists");
		} else {
			this.programRepo.save(newProgram);
		}
	}
	
	public void saveAll(List<Program> newPrograms) {
		for (Program p : newPrograms) {
			save(p);
		}
	}
	
}
