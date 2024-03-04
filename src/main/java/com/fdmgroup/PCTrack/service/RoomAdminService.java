package com.fdmgroup.PCTrack.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fdmgroup.PCTrack.dal.RoomAdminRepository;
import com.fdmgroup.PCTrack.dal.StaffRepository;
import com.fdmgroup.PCTrack.model.Room;
import com.fdmgroup.PCTrack.model.RoomAdmin;
import com.fdmgroup.PCTrack.model.Staff;

@Service
public class RoomAdminService {
	private RoomAdminRepository roomAdminRepo;
	
	public RoomAdminService(RoomAdminRepository roomAdminRepo) {
		super();
		this.roomAdminRepo = roomAdminRepo;
	}
	
	public void save(RoomAdmin newStaff) {
		if (this.roomAdminRepo.existsById(newStaff.getStaffId())) {
			throw new RuntimeException("Staff already exists");
		} else {
			this.roomAdminRepo.save(newStaff);
		}
	}
	public void deleteByStaffId(int userId) {
		if (this.roomAdminRepo.existsById(userId)) {
			roomAdminRepo.deleteById(userId);
			
		} else {
			throw new RuntimeException("Staff admin does not exist");
		}
	}
	
	public void update(RoomAdmin newStaff) {
		if (this.roomAdminRepo.existsById(newStaff.getStaffId())) {
			this.roomAdminRepo.save(newStaff);
		
		} else {
			throw new RuntimeException("Staff does not exist");
		}
	}
	

	
	public List<Room> getRooms(int staffId) {
		 RoomAdmin roomAdmin = roomAdminRepo.findByStaffId(staffId);
	        if (roomAdmin != null) {
	            return roomAdmin.getRoomAssigned();
	        }
	        return  new ArrayList<>();
	}

	public RoomAdmin getRoomAdminById(int id) {
		return roomAdminRepo.findByStaffId(id);
	}
	
}
