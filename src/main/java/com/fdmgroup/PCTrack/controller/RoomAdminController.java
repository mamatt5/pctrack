package com.fdmgroup.PCTrack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.PCTrack.model.Room;
import com.fdmgroup.PCTrack.model.RoomAdmin;
import com.fdmgroup.PCTrack.model.Staff;
import com.fdmgroup.PCTrack.model.User;
import com.fdmgroup.PCTrack.service.RoomAdminService;
import com.fdmgroup.PCTrack.service.StaffService;

@RestController
@CrossOrigin("http://localhost:5173")
public class RoomAdminController {

	private RoomAdminService roomAdminService;
	
	@Autowired
	public RoomAdminController(RoomAdminService roomAdminService) {
		super();
		this.roomAdminService = roomAdminService;
	}
	
	@GetMapping("getRoomAdminRooms/{staffId}")
	public List<Room> getRoomsOfRoomAdmin(@PathVariable int staffId) {
		return roomAdminService.getRooms(staffId);
	}
	
	@PutMapping("editRooms/{staffId}")
	public RoomAdmin findRoomsWhereStaffIsAdmin(@RequestBody RoomAdmin admin) {
		roomAdminService.update(admin);
		return roomAdminService.getRoomAdminById(admin.getStaffId());
		
	}

	
	
	
}
