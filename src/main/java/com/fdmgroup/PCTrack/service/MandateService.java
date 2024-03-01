package com.fdmgroup.PCTrack.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.PCTrack.dal.MandateRepository;
import com.fdmgroup.PCTrack.dal.RoomRepository;
import com.fdmgroup.PCTrack.model.Mandate;
import com.fdmgroup.PCTrack.model.Room;

@Service
public class MandateService {
	private MandateRepository mandateRepo;
	private RoomRepository roomRepo;
	
	@Autowired
	public MandateService(MandateRepository mandateRepo, RoomRepository roomRepo) {
		super();
		this.mandateRepo = mandateRepo;
		this.roomRepo = roomRepo;
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

	public List<Mandate> findByRoom(int roomId) {
		Room room = this.roomRepo.findById(roomId)
				.orElseThrow(() -> new RuntimeException("Room not found"));
		
		List<Mandate> allMandates = this.mandateRepo.findAll();
		List<Mandate> roomMandates = new ArrayList<Mandate>();
		
		for (Mandate mandate : allMandates) {
			
			if (mandate.getRoom() == room) {
				roomMandates.add(mandate);
			}
			
		}
		return roomMandates;
	}
}

