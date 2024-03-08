package com.fdmgroup.PCTrack.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fdmgroup.PCTrack.dal.ProgramRepository;
import com.fdmgroup.PCTrack.dal.SoftwareRepository;
import com.fdmgroup.PCTrack.model.Program;
import com.fdmgroup.PCTrack.model.Software;

@Service
public class ProgramService {
	private SoftwareRepository softwareRepository;
	private ProgramRepository programRepository;

	public ProgramService(ProgramRepository programRepository, SoftwareRepository softwareRepository) {
		super();
		this.softwareRepository = softwareRepository;
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
		} else {
			throw new RuntimeException("Program does not exist");
		}
	}
	
	public void update(Program newProgram) {
		if (this.programRepository.existsById(newProgram.getProgramId())) {
			this.programRepository.save(newProgram);
		
		} else {
			throw new RuntimeException("Program does not exist");
		}
	}

	public void deleteBySoftwareId(int softwareId)
	{
		List<Program> programs = findAllPrograms();
		if (programs.isEmpty()){
			throw new RuntimeException("Program does not exist");
		}
		for (Program p : programs) {
			int index = p.getSoftware().getSoftwareId();
			if(index == softwareId) {
				programRepository.deleteById(softwareId);
			}
		}
	}
}
