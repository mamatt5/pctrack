package com.fdmgroup.PCTrack.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.fdmgroup.PCTrack.dal.StaffRepository;
import com.fdmgroup.PCTrack.model.Staff;

@Service
public class StaffService {
	private StaffRepository staffRepo;
	
	public StaffService(StaffRepository staffRepo) {
		this.staffRepo = staffRepo;
	}
	
	public List<Staff> findAllStaffs() {
		return this.staffRepo.findAll();
	}
	
	public Staff findByStaffId(int staffId) {
		return this.staffRepo.findById(staffId).orElseThrow(() -> new RuntimeException("Staff not found."));
	}
	
	public void save(Staff newStaff) {
		if (this.staffRepo.existsById(newStaff.getStaffId())) {
			throw new RuntimeException("Staff already exists");
		} else {
			this.staffRepo.save(newStaff);
		}
	}
}
