package com.fdmgroup.PCTrack.service;

import java.util.List;

import com.fdmgroup.PCTrack.dal.RoomRepository;
import com.fdmgroup.PCTrack.model.Room;

public class RoomService {
	private RoomRepository roomRepository;

	public RoomService(RoomRepository roomRepository) {
		super();
		this.roomRepository = roomRepository;
	}
	
	public List<Room> findAllRooms() {
		return this.roomRepository.findAll();
	}
	
	public Room findById(int RoomId) {
		return this.roomRepository.findById(RoomId).orElseThrow(()-> new RuntimeException("Room not found"));
	}
	
	public void save(Room newRoom) {
		if (this.roomRepository.existsById(newRoom.getRoomId())) {
			throw new RuntimeException("Room already exists");
		
		} else {
			this.roomRepository.save(newRoom);
		}
	}
	
	public void deleteById(int roomId) {
		if (this.roomRepository.existsById(roomId)) {
			roomRepository.deleteById(roomId);
		}
	}
	
	public void update(Room newRoom) {
		if (this.roomRepository.existsById(newRoom.getRoomId())) {
			this.roomRepository.save(newRoom);
		
		} else {
			throw new RuntimeException("Room does not exist");
		}
	}
	

}
