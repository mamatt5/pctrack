package com.fdmgroup.PCTrack.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fdmgroup.PCTrack.dal.ProgramVersionRepository;
import com.fdmgroup.PCTrack.model.ProgramVersion;

@Service
public class ProgramVersionService {
	private ProgramVersionRepository programVersionRepository;

	public ProgramVersionService(ProgramVersionRepository programVersionRepository) {
		super();
		this.programVersionRepository = programVersionRepository;
	}
	
	public List<ProgramVersion> findAllProgramVersions() {
		return this.programVersionRepository.findAll();
	}
	
	public ProgramVersion findById(int programVersionId) {
		return this.programVersionRepository.findById(programVersionId).orElseThrow(()-> new RuntimeException("Program Version not found"));
	}
	
	public void save(ProgramVersion newProgramVersion) {
		if (this.programVersionRepository.existsById(newProgramVersion.getProgramVersionId())) {
			throw new RuntimeException("Program Version already exists");
		
		} else {
			this.programVersionRepository.save(newProgramVersion);
		}
	}
	
	public void saveAll(List<ProgramVersion> newProgramVersions) {
		for (ProgramVersion p : newProgramVersions) {
			if (this.programVersionRepository.existsById(p.getProgramVersionId())) {
				throw new RuntimeException("Program Version already exists");
			
			} else {
				this.programVersionRepository.save(p);
			}
		}
	}
	
	public void deleteById(int programVersionId) {
		if (this.programVersionRepository.existsById(programVersionId)) {
			programVersionRepository.deleteById(programVersionId);
		} else {
			throw new RuntimeException("Program Version does not exist");
		}
	}
	
	public void update(ProgramVersion newProgramVersion) {
		if (this.programVersionRepository.existsById(newProgramVersion.getProgramVersionId())) {
			this.programVersionRepository.save(newProgramVersion);
		
		} else {
			throw new RuntimeException("Program Version does not exist");
		}
	}
}
