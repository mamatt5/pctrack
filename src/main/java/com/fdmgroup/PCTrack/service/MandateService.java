package com.fdmgroup.PCTrack.service;

import java.util.List;

import com.fdmgroup.PCTrack.dal.MandateRepository;
import com.fdmgroup.PCTrack.model.Mandate;

public class MandateService {
	private MandateRepository mandateRepository;

	public MandateService(MandateRepository mandateRepository) {
		super();
		this.mandateRepository = mandateRepository;
	}
	
	public List<Mandate> findAllMandates() {
		return this.mandateRepository.findAll();
	}
	
	public Mandate findById(int mandateId) {
		return this.mandateRepository.findById(mandateId).orElseThrow(()-> new RuntimeException("Mandate not found"));
	}
	
	public void save(Mandate newMandate) {
		if (this.mandateRepository.existsById(newMandate.getMandateId())) {
			throw new RuntimeException("Mandate already exists");
		
		} else {
			this.mandateRepository.save(newMandate);
		}
	}
	
	public void deleteById(int mandateId) {
		if (this.mandateRepository.existsById(mandateId)) {
			mandateRepository.deleteById(mandateId);
		}
	}
	
	public void update(Mandate newMandate) {
		if (this.mandateRepository.existsById(newMandate.getMandateId())) {
			this.mandateRepository.save(newMandate);
		
		} else {
			throw new RuntimeException("Mandate does not exist");
		}
	}
}
