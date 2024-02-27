package com.fdmgroup.PCTrack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.PCTrack.dal.MandateRepository;
import com.fdmgroup.PCTrack.model.Mandate;

@Service
public class MandateService {
	private MandateRepository mandateRepo;
	
	@Autowired
	public MandateService(MandateRepository mandateRepo) {
		super();
		this.mandateRepo = mandateRepo;
	}
	
	public List<Mandate> findAllMandate() {
		return this.mandateRepo.findAll();
	}
	
	public Mandate findById(int mandateId) {
		return this.mandateRepo.findById(mandateId).orElseThrow(() -> new RuntimeException("Mandate not found"));
	}
	
	public void save(Mandate newMandate) {
		if (this.mandateRepo.existsById(newMandate.getMandateId())) {
			throw new RuntimeException("Mandate already exists");
		} else {
			this.mandateRepo.save(newMandate);
		}
	}
	
	public void deleteById(int mandateId) {
		if (this.mandateRepo.existsById(mandateId)) {
			this.mandateRepo.deleteById(mandateId);
		}
	}
	
	public void update(Mandate newMandate) {
		if (this.mandateRepo.existsById(newMandate.getMandateId())) {
			this.mandateRepo.save(newMandate);
		} else {
			throw new RuntimeException("Mandate does not exist");
		}
	}
}

