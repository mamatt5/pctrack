package com.fdmgroup.PCTrack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fdmgroup.PCTrack.model.Staff;
import com.fdmgroup.PCTrack.model.User;
import com.fdmgroup.PCTrack.service.StaffService;
import com.fdmgroup.PCTrack.service.UserService;

@RestController
@CrossOrigin("http://localhost:5813")
public class StaffController {
	private StaffService staffService;

	@Autowired
	public StaffController(StaffService staffService) {
		super();
		this.staffService = staffService;
	}

	// non pagination
	@GetMapping("staff")
	public List<Staff> getUsers() {
		return staffService.findAllStaffs();
	}

	// pagination
	@GetMapping("staffPage")
	public List<Staff> getStaffPage(@RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "10") int pageSize) {
		return staffService.getStaffPage(pageNumber, pageSize).getContent();

	}

	@GetMapping("searchStaff/{query}")
	public List<Staff> getStaffPartial(@PathVariable String query, @RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "10") int pageSize) {
		return staffService.findAllUsersPartialMatch(query, pageNumber, pageSize).getContent();
	}

	@GetMapping("countStaff")
	public long countStaff() {
		return staffService.staffCount();

	}
	@GetMapping("countStaffPartial/{query}")
	public long countStaff(@PathVariable String query) {
		return staffService.staffCountPartial(query);

	}

	@PostMapping("staff")
	public void createStaff(@RequestBody Staff staff) {
		staffService.save(staff);
	}

	@GetMapping("staff/{userId}")
	public List<Staff> findById(@PathVariable int userId) {
		return staffService.findByUserId(userId);
	}

}
