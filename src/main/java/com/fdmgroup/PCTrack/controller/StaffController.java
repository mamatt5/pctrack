package com.fdmgroup.PCTrack.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fdmgroup.PCTrack.model.Location;
import com.fdmgroup.PCTrack.model.Room;
import com.fdmgroup.PCTrack.model.Staff;
import com.fdmgroup.PCTrack.model.User;
import com.fdmgroup.PCTrack.service.StaffService;
import com.fdmgroup.PCTrack.service.UserService;

@RestController
@CrossOrigin("http://localhost:5813")
public class StaffController {
	private StaffService staffService;
	

	@Autowired
	public StaffController( StaffService staffService) {
		super();
		this.staffService = staffService;
	}
	
	@GetMapping("staff")
	public List<Staff> getUsers() {
		return staffService.findAllStaffs();
	}
	
	@PostMapping("staff")
	public void createStaff(@RequestBody Staff staff) {
		 staffService.save(staff);
	}
	
	@GetMapping("staff/{userId}")
	public List<Staff> findById(@PathVariable int userId) {
		return staffService.findByUserId(userId);
	}
	
	@GetMapping("staff/getrooms/{userId}")
	public List<Room> findRoomsWhereStaffIsAdmin(@PathVariable int userId) {
		return staffService.getRoomsStaffIsAdmin(userId);
	}
	
	
	
}
