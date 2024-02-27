package com.fdmgroup.PCTrack.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fdmgroup.PCTrack.dal.RoomRepository;
import com.fdmgroup.PCTrack.model.Room;

@Service
public class RoomService {
	private RoomRepository roomRepo;
	
	@Autowired
	public RoomService(RoomRepository roomRepo) {
		super();
		this.roomRepo = roomRepo;
	}
	
	public List<Room> findAllRooms() {
		return this.roomRepo.findAll();
	}
	
	public Room findById(int RoomId) {
		return this.roomRepo.findById(RoomId).orElseThrow(() -> new RuntimeException("Room not found"));
	}
	
	public void save(Room newRoom) {
		if (this.roomRepo.existsById(newRoom.getRoomId())) {
			throw new RuntimeException("Room already exists");
		} else {
			this.roomRepo.save(newRoom);
		}
	}
	
	public void deleteById(int roomId) {
		if (this.roomRepo.existsById(roomId)) {
			roomRepo.deleteById(roomId);
		}
	}

	public void update(Room newRoom) {
		// TODO Auto-generated method stub
		
	}
}


