package com.fdmgroup.PCTrack.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fdmgroup.PCTrack.dal.SoftwareRepository;
import com.fdmgroup.PCTrack.model.Software;

@Service
public class SoftwareService {
	private SoftwareRepository softwareRepository;

	public SoftwareService(SoftwareRepository softwareRepository) {
		super();
		this.softwareRepository = softwareRepository;
	}
	
	public List<Software> findAllSoftwares() {
		return this.softwareRepository.findAll();
	}
	
	public Software findById(int softwareId) {
		return this.softwareRepository.findById(softwareId).orElseThrow(()-> new RuntimeException("Software not found"));
	}
	
	public void save(Software newSoftware) {
		if (this.softwareRepository.existsById(newSoftware.getSoftwareId())) {
			throw new RuntimeException("Software already exists");
		
		} else {
			this.softwareRepository.save(newSoftware);
		}
	}
	
	public void saveAll(List<Software> newSoftwares) {
		for (Software p : newSoftwares) {
			if (this.softwareRepository.existsById(p.getSoftwareId())) {
				throw new RuntimeException("Software already exists");
			
			} else {
				this.softwareRepository.save(p);
			}
		}
	}
	
	public void deleteById(int softwareId) {
		if (this.softwareRepository.existsById(softwareId)) {
			softwareRepository.deleteById(softwareId);
		} else {
			throw new RuntimeException("Software does not exist");
		}
	}
	
	public void update(Software newSoftware) {
		if (this.softwareRepository.existsById(newSoftware.getSoftwareId())) {
			this.softwareRepository.save(newSoftware);
		
		} else {
			throw new RuntimeException("Software does not exist");
		}
	}
}
