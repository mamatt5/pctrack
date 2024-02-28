package com.fdmgroup.PCTrack.ControllerTests;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PCTrack.controller.RoomController;
import com.fdmgroup.PCTrack.model.Computer;
import com.fdmgroup.PCTrack.model.Location;
import com.fdmgroup.PCTrack.model.Program;
import com.fdmgroup.PCTrack.model.Room;
import com.fdmgroup.PCTrack.service.RoomService;

import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@ExtendWith(MockitoExtension.class)
public class RoomControllerTests {
	
	@Mock
	RoomService roomService;
	
	RoomController roomController;
	Location location1;


     
     
	@BeforeEach
	void setup() {
		this.roomController = new RoomController(roomService);
		location1 = new Location("FDM Sydney", "Sydney");

	}
	
	@Test
	void findRoomById_test() {
		
		Room room1 = new Room("Bondi", location1);

		
		when(roomService.findById(1)).thenReturn(room1);
		assertSame(room1, roomController.findById(1));
		verify(roomService, times(1)).findById(1);
	}
	
	@Test
	void createRoom_test() {

		Room room1 = new Room("Bondi", location1);
		
		when(roomService.findById(0)).thenReturn(room1);
		assertSame(room1, roomController.createNewRoom(room1));
		verify(roomService, times(1)).save(room1);
	}
	
	@Test
	void updateRoom_test() {
		
		Room room1 = new Room("Bondi", location1);
		Room updatedRoom1 = new Room("Bondi updated", location1);

		when(roomService.findById(0)).thenReturn(updatedRoom1);
		assertSame(roomController.updateRoom(room1), updatedRoom1);
		verify(roomService, times(1)).update(room1);
	}
	
	@Test
	void deleteRoom_test() {
		roomController.deleteRoom(1);
		verify(roomService, times(1)).deleteById(1);
	}
	
	@Test
	void findAllRooms() {

		Room room1 = new Room("Bondi", location1);
	    Room room2 = new Room("Coogee", location1);
	    Room room3 = new Room("Balmoral",location1);
	    Room room4 = new Room("Bronte",location1);
        Room room5 = new Room("Tamarama",location1);
        Room room6 = new Room("Aberdeen", location1);
        Room room7 = new Room("Lantau", location1);
        Room room8 = new Room("Stanley", location1);
        Room room9 = new Room("Causeway Bay", location1);
		
		List<Room> allRooms = new ArrayList<>();
		allRooms.add(room1);
		allRooms.add(room2);
		allRooms.add(room3);
		allRooms.add(room4);
		allRooms.add(room5);
		allRooms.add(room6);
		allRooms.add(room7);
		allRooms.add(room8);
		allRooms.add(room9);

		
		when(roomService.findAllRooms()).thenReturn(allRooms);
		assertSame(roomController.getRooms(), allRooms);
		verify(roomService, times(1)).findAllRooms();
	}
}
