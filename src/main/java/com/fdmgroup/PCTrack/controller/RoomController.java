package com.fdmgroup.PCTrack.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.fdmgroup.PCTrack.model.Room;
import com.fdmgroup.PCTrack.service.RoomService;

public class RoomController {
	private RoomService roomService;

	public RoomController(RoomService roomService) {
		super();
		this.roomService = roomService;
	}
	
	public List<Room> getRooms() {
		return roomService.findAllRooms();
	}
	
	public Room findById(@PathVariable int roomId) {
		return roomService.findById(roomId);
	}
	
	public Room createNewRoom(@RequestBody Room newRoom) {
		roomService.save(newRoom);
		return roomService.findById(newRoom.getRoomId());
	}
	
	public Room updateRoom(@RequestBody Room newRoom) {
		roomService.update(newRoom);
		return roomService.findById(newRoom.getRoomId());
	}
	
	public void deleteRoom(@PathVariable int roomId) {
		roomService.deleteById(roomId);
	}

}
