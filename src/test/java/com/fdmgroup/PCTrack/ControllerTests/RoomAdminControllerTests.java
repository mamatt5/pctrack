package com.fdmgroup.PCTrack.ControllerTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PCTrack.controller.RoomAdminController;
import com.fdmgroup.PCTrack.model.Room;
import com.fdmgroup.PCTrack.model.RoomAdmin;
import com.fdmgroup.PCTrack.service.RoomAdminService;

@ExtendWith(MockitoExtension.class)
public class RoomAdminControllerTests {
	
	@Mock
	RoomAdminService roomAdminService;
	
	RoomAdminController roomAdminController;
	RoomAdmin roomAdmin;
	Room room;
	
	@BeforeEach
	public void setup() {
		roomAdminController = new RoomAdminController(roomAdminService);
	}
	
	@Test
	public void get_rooms_of_room_admin() {
		List<Room> rooms = new ArrayList<>();
		when(roomAdminService.getRooms(0)).thenReturn(rooms);
		assertEquals(rooms, roomAdminController.getRoomsOfRoomAdmin(0));
	}
	
	@Test
	public void assign_room_admin_role_to_staff() {
		roomAdmin = new RoomAdmin();
		when(roomAdminService.getRoomAdminById(0)).thenReturn(roomAdmin);
		assertEquals(roomAdmin, roomAdminController.assignRoomAdminToStaff(0, roomAdmin));
		verify(roomAdminService, times(1)).update(roomAdmin);
	}
	
	@Test
	public void create_new_room_admin() {
		roomAdminController.createNewRoomAdmin(roomAdmin);
		verify(roomAdminService, times(1)).save(roomAdmin);
	}

}
