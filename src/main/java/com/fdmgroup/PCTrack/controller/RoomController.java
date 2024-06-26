package com.fdmgroup.PCTrack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fdmgroup.PCTrack.model.Computer;
import com.fdmgroup.PCTrack.model.Room;
import com.fdmgroup.PCTrack.service.RoomService;

@RestController
@CrossOrigin("http://localhost:5173")
public class RoomController {
	
	private RoomService roomService;
	
	@Autowired
	public RoomController(RoomService roomService) {
		super();
		this.roomService = roomService;
	}
	
	@GetMapping("rooms")
	public List<Room> getRooms() {
		return roomService.findAllRooms();
	}
	
	@GetMapping("roomsByLocation/{locationId}")
	public List<Room> getRoomsAtLocation(@PathVariable int locationId) {
		return roomService.getRoomsInLocation(locationId);
	}
	
	@GetMapping("rooms/search/{name}")
	public List<Room> searchByName(@PathVariable String name) {
		return roomService.searchByName(name);
	}
	
	@GetMapping("rooms/{roomId}")
	public Room findById(@PathVariable int roomId) {
		return roomService.findById(roomId);
	} 
	
	@PostMapping("rooms")
	public Room createNewRoom(@RequestBody Room newRoom) {
		roomService.save(newRoom);
		return roomService.findById(newRoom.getRoomId());
	}
	
	@PutMapping("rooms")
	public Room updateRoom(@RequestBody Room newRoom) {
		roomService.update(newRoom);
		return roomService.findById(newRoom.getRoomId());
	}
	
	@DeleteMapping("rooms/{roomdId}")
	public void deleteRoom(@PathVariable int roomId) {
		roomService.deleteById(roomId);
	}
	
	@GetMapping("getcomputers/{roomId}")
	public List<Computer> getComputersInRoom(@PathVariable int roomId) {
		return roomService.getComptuersInRoom(roomId);
	}

}
