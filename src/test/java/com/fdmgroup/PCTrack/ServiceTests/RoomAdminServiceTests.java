package com.fdmgroup.PCTrack.ServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import com.fdmgroup.PCTrack.dal.RoomAdminRepository;
import com.fdmgroup.PCTrack.model.Room;
import com.fdmgroup.PCTrack.model.RoomAdmin;
import com.fdmgroup.PCTrack.model.Staff;
import com.fdmgroup.PCTrack.service.RoomAdminService;

@ExtendWith(MockitoExtension.class)
public class RoomAdminServiceTests {
	@Mock
	RoomAdminRepository roomAdminRepo;
	
	RoomAdminService roomAdminService;
	Staff staff;
	
	@BeforeEach
	public void setup() {
		roomAdminService = new RoomAdminService(roomAdminRepo);
	}
	
	@Test
	public void save_staff_as_room_admin() {
		staff = new RoomAdmin();
		when(roomAdminRepo.existsById(staff.getStaffId())).thenReturn(false);
		roomAdminService.save((RoomAdmin) staff);
		verify(roomAdminRepo, times(1)).save((RoomAdmin) staff);
	}
	
	@Test
	public void save_fails_when_room_admin_exists() {
		staff = new RoomAdmin();
		when(roomAdminRepo.existsById(staff.getStaffId())).thenReturn(true);
		assertThrows(RuntimeException.class, ()-> roomAdminService.save((RoomAdmin) staff));
	}
	
	@Test
	public void delete_room_admin_by_staff_id() {
		staff = new RoomAdmin();
		when(roomAdminRepo.existsById(staff.getStaffId())).thenReturn(true);
		roomAdminService.deleteByStaffId(staff.getStaffId());
		verify(roomAdminRepo, times(1)).deleteById(staff.getStaffId());
	}
	
	@Test
	public void delete_throws_when_room_admin_does_not_exist() {
		staff = new RoomAdmin();
		when(roomAdminRepo.existsById(staff.getStaffId())).thenReturn(false);
		assertThrows(RuntimeException.class, ()-> roomAdminService.deleteByStaffId(staff.getStaffId()));
	}
	
	@Test
	public void update_room_admin() {
		staff = new RoomAdmin();
		when(roomAdminRepo.existsById(staff.getStaffId())).thenReturn(true);
		roomAdminService.update((RoomAdmin) staff);
		verify(roomAdminRepo, times(1)).save((RoomAdmin) staff);
	}
	
	@Test
	public void update_fails_when_room_admin_does_not_exist() {
		staff = new RoomAdmin();
		when(roomAdminRepo.existsById(staff.getStaffId())).thenReturn(false);
		assertThrows(RuntimeException.class, ()->roomAdminService.update((RoomAdmin) staff));
	}
	
	@Test
	public void get_rooms_by_room_admin() {
		List<Room> roomList = new ArrayList<>();
		staff = new RoomAdmin();
		RoomAdmin roomAdmin = (RoomAdmin) staff;
		roomAdmin.setRoomAssigned(roomList);
		when(roomAdminRepo.findByStaffId(staff.getStaffId())).thenReturn(roomAdmin);
		assertEquals(roomList, roomAdminService.getRooms(roomAdmin.getStaffId()));
	}
	
	@Test
	public void get_empty_room_list_when_staff_is_not_a_room_admin() {
		staff = new Staff();
		when(roomAdminRepo.findByStaffId(staff.getStaffId())).thenReturn(null);
		assertEquals(new ArrayList<>(), roomAdminService.getRooms(staff.getStaffId()));
	}
	
	@Test
	public void get_room_admin_by_id() {
		staff = new RoomAdmin();
		when(roomAdminRepo.findByStaffId(staff.getStaffId())).thenReturn((RoomAdmin) staff);
		assertEquals((RoomAdmin) staff, roomAdminService.getRoomAdminById(staff.getStaffId()));
	}

}
