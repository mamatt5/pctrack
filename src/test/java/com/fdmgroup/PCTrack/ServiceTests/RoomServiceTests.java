package com.fdmgroup.PCTrack.ServiceTests;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PCTrack.dal.LocationRepository;
import com.fdmgroup.PCTrack.dal.RoomRepository;
import com.fdmgroup.PCTrack.model.Location;
import com.fdmgroup.PCTrack.model.Room;
import com.fdmgroup.PCTrack.service.LocationService;
import com.fdmgroup.PCTrack.service.RoomService;

import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTests {
	@Mock
	RoomRepository roomRepo;
	
	RoomService roomService;
	
	Location location1;
	Room room1;
	
	@BeforeEach
	void setup() {
		
		this.roomService = new RoomService(roomRepo);
		
	}
	
	@Test
	void save_user_test() {
		
		Room room1 = new Room("Bondi", location1);
        Room room2 = new Room("Coogee", location1);
        Room room3 = new Room("Balmoral",location1);
        Room room4 = new Room("Bronte",location1);
        Room room5 = new Room("Tamarama",location1);
       
		
		roomService.save(room1);
		verify(roomRepo, times(1)).save(room1);
	}
	
	@Test
	void save_multiple_users_test() {
		

		Room room1 = new Room("Bondi", location1);
        Room room2 = new Room("Coogee", location1);
        Room room3 = new Room("Balmoral",location1);
        Room room4 = new Room("Bronte",location1);
        Room room5 = new Room("Tamarama",location1);
		
      
		
        roomService.save(room1);
		verify(roomRepo, times(1)).save(room1);
		
		roomService.save(room2);
		verify(roomRepo, times(1)).save(room2);
		
		roomService.save(room3);
		verify(roomRepo, times(1)).save(room3);
		
		roomService.save(room4);
		verify(roomRepo, times(1)).save(room4);
		
		roomService.save(room5);
		verify(roomRepo, times(1)).save(room5);
		

	}
	
	
	@Test
	void find_all_user_test() {
		
	
		Room room1 = new Room("Bondi", location1);
        Room room2 = new Room("Coogee", location1);
        Room room3 = new Room("Balmoral",location1);
        Room room4 = new Room("Bronte",location1);
        Room room5 = new Room("Tamarama",location1);
		
        
		List<Room> allRooms = new ArrayList<>();
		
		allRooms.add(room1);
		allRooms.add(room2);
		allRooms.add(room3);
		allRooms.add(room4);
		allRooms.add(room5);
		
		
		when(roomRepo.findAll()).thenReturn(allRooms);
		
		List<Room> foundLocations = roomService.findAllRooms();
	
		verify(roomRepo, times(1)).findAll();
		assertSame(foundLocations, allRooms);
	}
	
	@Test
	void find_user_by_id_test() {
		
		Optional<Room> room1 = Optional.of(new Room("Balmoral", location1));

		when(roomRepo.findById(1)).thenReturn(room1);
		Room foundRoom1 = roomService.findById(1);
		
		verify(roomRepo, times(1)).findById(1);
		assertSame(room1.get(), foundRoom1);

	}
	
	@Test
	void find_user_by_id_fail_test() {

		assertThrows(RuntimeException.class, () -> roomService.findById(1));
		verify(roomRepo, times(1)).findById(1);
	}
	
	@Test
	void update_user_test() {
		
		Room room1 = new Room("Bondi", location1);
		room1.setRoomId(1);
		
		when(roomRepo.existsById(1)).thenReturn(true);
		roomService.update(room1);
		
		verify(roomRepo, times(1)).existsById(1);
		verify(roomRepo, times(1)).save(room1);

	}
	
	@Test
	void update_user_fail_test() {
		
		Room room1 = new Room("Bondi", location1);
		room1.setRoomId(1);
		
		
		when(roomRepo.existsById(1)).thenReturn(false);
		
		assertThrows(RuntimeException.class, () -> roomService.update(room1));
		verify(roomRepo, times(1)).existsById(1);
		verify(roomRepo, times(0)).save(room1);
	}
	
	@Test
	void delete_user_test() {
		
		when(roomRepo.existsById(1)).thenReturn(true);
		
		roomService.deleteById(1);
		
		verify(roomRepo, times(1)).existsById(1);
		verify(roomRepo, times(1)).deleteById(1);

	}
	
	@Test
	void delete_user_fail_test() {
		
		when(roomRepo.existsById(1)).thenReturn(false);
		
		assertThrows(RuntimeException.class, () -> roomService.deleteById(1));
		verify(roomRepo, times(1)).existsById(1);
		verify(roomRepo, times(0)).deleteById(1);
	}
}
