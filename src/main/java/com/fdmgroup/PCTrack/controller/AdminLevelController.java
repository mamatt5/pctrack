package com.fdmgroup.PCTrack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.PCTrack.model.AdminLevel;
import com.fdmgroup.PCTrack.model.Computer;
import com.fdmgroup.PCTrack.service.AdminLevelService;
import com.fdmgroup.PCTrack.service.ComputerService;

@RestController
@CrossOrigin("http://localhost:5173")
public class AdminLevelController {
	private AdminLevelService adminService; 
	
	@Autowired
	public AdminLevelController(AdminLevelService adminService) {
		super();
		this.adminService = adminService;
	}
	
	@GetMapping("adminLevels")
	public List<AdminLevel> getAdminLevels() {
		return adminService.findAllLevels(); 
	} 
}
 