package com.fdmgroup.PCTrack.ServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.fdmgroup.PCTrack.dal.StaffRepository;
import com.fdmgroup.PCTrack.model.AdminLevel;
import com.fdmgroup.PCTrack.model.Location;
import com.fdmgroup.PCTrack.model.Room;
import com.fdmgroup.PCTrack.model.Staff;
import com.fdmgroup.PCTrack.model.User;
import com.fdmgroup.PCTrack.service.StaffService;

@ExtendWith(MockitoExtension.class)
public class StaffServiceTests {

	@Mock
	StaffRepository staffRepo;

	StaffService staffService;
	Staff staff;
	Page<Staff> page;

	@BeforeEach
	public void setup() {
		staffService = new StaffService(staffRepo);
	}

	@Test
	public void find_all_staff() {
		List<Staff> staffList = new ArrayList<>();
		assertEquals(staffList, staffService.findAllStaffs());
	}

	@Test
	public void get_staff_count() {
		when(staffRepo.count()).thenReturn((long) 10);
		assertEquals((long) 10, staffService.staffCount());
	}
	
	@Test
	public void delete_staff_by_id() {
		when(staffRepo.existsById(0)).thenReturn(true);
		staffService.deleteById(0);
		verify(staffRepo, times(1)).existsById(0);
	}
	
	@Test
	public void delete_throws_when_staff_does_not_exist() {
		assertThrows(RuntimeException.class, ()-> staffService.deleteById(0));
	}
	
	@Test
	public void staff_count_partial_search() {
		List<Integer> locationList = new ArrayList<>();
		List<Integer> adminLevels = new ArrayList<>();
		when(staffRepo.countByUsernameLike("test", locationList, adminLevels)).thenReturn((long) 10);
		assertEquals((long) 10, staffService.staffCountPartial("test", locationList, adminLevels));
	}
	
	@Test
	public void find_staff_by_id() {
		staff = new Staff();
		staff.setStaffId(0);
		when(staffRepo.findById(0)).thenReturn(Optional.of(staff));
		assertEquals(staff, staffService.findByStaffId(0));
	}
	
	@Test
	public void create_staff() {
		staff = new Staff();
		staff.setStaffId(0);
		when(staffRepo.existsById(0)).thenReturn(false);
		staffService.save(staff);
		verify(staffRepo, times(1)).save(staff);
	}
	
	@Test
	public void create_staff_throws_when_already_exists() {
		staff = new Staff();
		staff.setStaffId(0);
		when(staffRepo.existsById(0)).thenReturn(true);
		assertThrows(RuntimeException.class, ()-> staffService.save(staff));
	}
	
	@Test
	public void get_staff_list_by_user_id() {
		List<Staff> staffList = new ArrayList<>();
		when(staffRepo.findByUserId(0)).thenReturn(staffList);
		assertEquals(staffList, staffService.findByUserId(0));
	}
	
	@Test
	public void delete_staff_that_exists() {
		when(staffRepo.existsById(0)).thenReturn(true);
		staffService.deleteByStaffId(0);
		verify(staffRepo, times(1)).deleteById(0);
	}
	
	@Test
	public void delete_throws_when_staff_does_not_exist2() {
		assertThrows(RuntimeException.class, ()-> staffService.deleteByStaffId(0));
	}
	
	@Test
	public void update_staff() {
		staff = new Staff();
		when(staffRepo.existsById(0)).thenReturn(true);
		staffService.update(staff);
		verify(staffRepo, times(1)).save(staff);
	}
	
	@Test
	public void update_throws_when_staff_does_not_exist() {
		assertThrows(RuntimeException.class, ()-> staffService.update(staff));
	}
	
	@Test
	public void get_rooms_where_staff_is_room_admin() {
		List<Room> roomList = new ArrayList<>();
		when(staffRepo.findRoomsStaffIsAdmin(0)).thenReturn(roomList);
		assertEquals(roomList, staffService.getRoomsStaffIsAdmin(0));
	}
	
	@Test
	public void get_rooms_where_staff_is_registered() {
		List<Room> roomList = new ArrayList<>();
		when(staffRepo.findRoomsStaffIsRegisteredIn(0)).thenReturn(roomList);
		assertEquals(roomList, staffService.getRoomStaffIsRegisterdIn(0));
	}
}
