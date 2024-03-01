package com.fdmgroup.PCTrack.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.fdmgroup.PCTrack.dal.AdminLevelRepository;
import com.fdmgroup.PCTrack.dal.StaffRepository;
import com.fdmgroup.PCTrack.model.AdminLevel;
import com.fdmgroup.PCTrack.model.Computer;
import com.fdmgroup.PCTrack.model.Staff;

@Service
public class AdminLevelService {

	private AdminLevelRepository AdminRepo;
	
	public AdminLevelService(AdminLevelRepository AdminRepo) {
		super();
		this.AdminRepo = AdminRepo;
	}
	
	
	public List<AdminLevel> findAllLevels() {
		return this.AdminRepo.findAll();
	}
	
	
	public void save(AdminLevel admin) {
		if (this.AdminRepo.existsById(admin.getId())) {
			throw new RuntimeException("admin already exists");
		} else {
			this.AdminRepo.save(admin);
		}
	}
}
