package com.fdmgroup.PCTrack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fdmgroup.PCTrack.model.AdminLevel;
import com.fdmgroup.PCTrack.model.Location;
import com.fdmgroup.PCTrack.model.Room;
import com.fdmgroup.PCTrack.model.Staff;
import com.fdmgroup.PCTrack.model.User;
import com.fdmgroup.PCTrack.service.AdminLevelService;
import com.fdmgroup.PCTrack.service.LocationService;
import com.fdmgroup.PCTrack.service.StaffService;
import com.fdmgroup.PCTrack.service.UserService;

@RestController
@CrossOrigin("http://localhost:5173")
public class StaffController {
	private StaffService staffService;
	private AdminLevelService adminLevelService;
	private LocationService locationService;

	@Autowired
	public StaffController(StaffService staffService,LocationService locationService, AdminLevelService adminLevelService) {
		super();
		this.staffService = staffService;
		this.locationService= locationService; 
		this.adminLevelService = adminLevelService; 
	}

	// non pagination filtered by location and by admin level.
	@GetMapping("filteredStaff")
	public List<Staff> getStaffByLocationsAndAdminLevels(@RequestParam List<Integer> locationIds,
			@RequestParam List<Integer> adminLevelIds, @RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "10") int pageSize) {

		List<Location> locations = locationService.getLocationsByIds(locationIds);
		List<AdminLevel> adminLevels = adminLevelService.getAdminLevelsByIds(adminLevelIds);
		return staffService.getLocationAdminFilteredStaff(pageNumber, pageSize, locations, adminLevels);
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
	
	// takes into account what is filtered from locations and adminlevel
	@GetMapping("searchStaff/{query}")
	public List<Staff> getStaffPartial(@PathVariable String query, @RequestParam List<Integer> locationIds,
			@RequestParam List<Integer> adminLevelIds, @RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "10") int pageSize) {
		
		return staffService.findAllUsersPartialMatch(query, pageNumber, pageSize, locationIds, adminLevelIds);
	}

	@GetMapping("countStaff")
	public long countStaff() {
		return staffService.staffCount();

	}

	
	@GetMapping("countStaffFiltered")
	public long countStaffFiltered(@RequestParam List<Integer> locationIds,
			@RequestParam List<Integer> adminLevelIds) {
		List<Location> locations = locationService.getLocationsByIds(locationIds);
		List<AdminLevel> adminLevels = adminLevelService.getAdminLevelsByIds(adminLevelIds);
		return staffService.staffCountFiltered(locations,adminLevels);
	}
	// counts staff that is filtered and is queried. 
	@GetMapping("countStaffPartial/{query}")
	public long countStaff(@PathVariable String query, @RequestParam List<Integer> locationIds,
			@RequestParam List<Integer> adminLevelIds) { 
		return staffService.staffCountPartial(query, locationIds, adminLevelIds);
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

	
	@GetMapping("staff/getregisteredrooms/{userId}")
	public List<Room> findRoomsWhereStaffIsRegistered(@PathVariable int userId) {
		return staffService.getRoomStaffIsRegisterdIn(userId);
	}
	

	@DeleteMapping("staff/{staffid}")
	public void deleteStaff(@PathVariable int staffid) {
		staffService.deleteById(staffid);
	}

}
