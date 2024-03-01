package com.fdmgroup.PCTrack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.PCTrack.model.Mandate;
import com.fdmgroup.PCTrack.service.MandateService;


@RestController
public class MandateController {
	private MandateService mandateService;

	@Autowired
	public MandateController(MandateService mandateService) {
		super();
		this.mandateService = mandateService;
	}
	
	@GetMapping("mandates")
	public List<Mandate> getMandates() {
		return mandateService.findAllMandate();
	}
	
	@GetMapping("mandates/{mandateId}")
	public Mandate findById(@PathVariable int mandateId) {
		return mandateService.findById(mandateId);
	}
	
	@PostMapping("mandates")
	public Mandate createNewMandate(@RequestBody Mandate newMandate) {
		mandateService.save(newMandate);
		return mandateService.findById(newMandate.getMandateId());
	}
	
	@PutMapping("mandates")
	public Mandate updateMandate(@RequestBody Mandate newMandate) {
		mandateService.update(newMandate);
		return mandateService.findById(newMandate.getMandateId());
	}
	
	@DeleteMapping("mandates/{mandateId}")
	public void deleteMandate(@PathVariable int mandateId) {
		mandateService.deleteById(mandateId);
	}

	@GetMapping("rooms/{roomId}/mandates")
	public List<Mandate> findMandateByRoom(@PathVariable int roomId) {
		return mandateService.findByRoom(roomId);
	}

}
