package com.fdmgroup.PCTrack.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.fdmgroup.PCTrack.dal.StaffRepository;
import com.fdmgroup.PCTrack.model.Staff;
import com.fdmgroup.PCTrack.model.User;

@Service
public class StaffService {
	private StaffRepository staffRepo;
	
	public StaffService(StaffRepository staffRepo) {
		super();
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
	
	
	public List<Staff> findByUserId(int userId) {
		return this.staffRepo.findByUserId(userId);
	}
	
	public void deleteByStaffId(int userId) {
		if (this.staffRepo.existsById(userId)) {
			staffRepo.deleteById(userId);
			
		} else {
			throw new RuntimeException("Staff does not exist");
		}
	}
	
	public void update(Staff newStaff) {
		if (this.staffRepo.existsById(newStaff.getStaffId())) {
			this.staffRepo.save(newStaff);
		
		} else {
			throw new RuntimeException("Staff does not exist");
		}
	}
}
