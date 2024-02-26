package com.fdmgroup.PCTrack.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.fdmgroup.PCTrack.model.Mandate;
import com.fdmgroup.PCTrack.service.MandateService;

public class MandateController {
	private MandateService mandateService;

	public MandateController(MandateService mandateService) {
		super();
		this.mandateService = mandateService;
	}
	
	public List<Mandate> getMandates() {
		return mandateService.findAllMandates();
	}
	
	public Mandate findById(@PathVariable int mandateId) {
		return mandateService.findById(mandateId);
	}
	
	public Mandate createNewMandate(@RequestBody Mandate newMandate) {
		mandateService.save(newMandate);
		return mandateService.findById(newMandate.getMandateId());
	}
	
	public Mandate updateMandate(@RequestBody Mandate newMandate) {
		mandateService.update(newMandate);
		return mandateService.findById(newMandate.getMandateId());
	}
	
	public void deleteMandate(@PathVariable int mandateId) {
		mandateService.deleteById(mandateId);
	}


}
