package com.fdmgroup.PCTrack.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fdmgroup.PCTrack.dal.VersionRepository;
import com.fdmgroup.PCTrack.model.Version;

@Service
public class VersionService {
	private VersionRepository versionRepository;

	public VersionService(VersionRepository versionRepository) {
		super();
		this.versionRepository = versionRepository;
	}
	
	public List<Version> findAllVersions() {
		return this.versionRepository.findAll();
	}
	
	public Version findById(int versionId) {
		return this.versionRepository.findById(versionId).orElseThrow(()-> new RuntimeException("Version not found"));
	}
	
	public void save(Version newVersion) {
		if (this.versionRepository.existsById(newVersion.getVersionId())) {
			throw new RuntimeException("Version already exists");
		
		} else {
			this.versionRepository.save(newVersion);
		}
	}
	
	public void saveAll(List<Version> newVersions) {
		for (Version p : newVersions) {
			if (this.versionRepository.existsById(p.getVersionId())) {
				throw new RuntimeException("Version already exists");
			
			} else {
				this.versionRepository.save(p);
			}
		}
	}
	
	public void deleteById(int versionId) {
		if (this.versionRepository.existsById(versionId)) {
			versionRepository.deleteById(versionId);
		} else {
			throw new RuntimeException("Version does not exist");
		}
	}
	
	public void update(Version newVersion) {
		if (this.versionRepository.existsById(newVersion.getVersionId())) {
			this.versionRepository.save(newVersion);
		
		} else {
			throw new RuntimeException("Version does not exist");
		}
	}
}
