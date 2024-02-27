package com.fdmgroup.PCTrack.service;

import java.util.List;

import com.fdmgroup.PCTrack.dal.ProgramRepository;
import com.fdmgroup.PCTrack.model.Program;

public class ProgramService {
	private ProgramRepository programRepository;

	public ProgramService(ProgramRepository programRepository) {
		super();
		this.programRepository = programRepository;
	}
	
	public List<Program> findAllPrograms() {
		return this.programRepository.findAll();
	}
	
	public Program findById(int programId) {
		return this.programRepository.findById(programId).orElseThrow(()-> new RuntimeException("Program not found"));
	}
	
	public void save(Program newProgram) {
		if (this.programRepository.existsById(newProgram.getProgramId())) {
			throw new RuntimeException("Program already exists");
		
		} else {
			this.programRepository.save(newProgram);
		}
	}
	
	public void saveAll(List<Program> newPrograms) {
		for (Program p : newPrograms) {
			if (this.programRepository.existsById(p.getProgramId())) {
				throw new RuntimeException("Program already exists");
			
			} else {
				this.programRepository.save(p);
			}
		}
	}
	
	public void deleteById(int programId) {
		if (this.programRepository.existsById(programId)) {
			programRepository.deleteById(programId);
		}
	}
	
	public void update(Program newProgram) {
		if (this.programRepository.existsById(newProgram.getProgramId())) {
			this.programRepository.save(newProgram);
		
		} else {
			throw new RuntimeException("Program does not exist");
		}
	}
}
