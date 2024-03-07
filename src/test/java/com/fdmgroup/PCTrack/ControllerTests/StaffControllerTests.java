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

import com.fdmgroup.PCTrack.controller.StaffController;
import com.fdmgroup.PCTrack.model.Room;
import com.fdmgroup.PCTrack.model.Staff;
import com.fdmgroup.PCTrack.service.StaffService;

@ExtendWith(MockitoExtension.class)
public class StaffControllerTests {
	
	@Mock
	StaffService staffService;
	
	Staff staff;
	Room room;
	StaffController staffController;
	
	@BeforeEach
	public void setup() {
		staffController = new StaffController(staffService);
	}
	
	@Test
	public void get_all_staff() {
		List<Staff> staffList = new ArrayList<>();
		staffList.add(staff);
		when(staffService.findAllStaffs()).thenReturn(staffList);
		assertEquals(staffList, staffController.getUsers());
	}
	
	@Test
	public void count_staff() {
		when(staffService.staffCount()).thenReturn((long) 10);
		assertEquals((long) 10, staffController.countStaff());
	}
	
	@Test
	public void count_staff_partial_query() {
		when(staffService.staffCountPartial("testing")).thenReturn((long) 5);
		assertEquals((long) 5, staffController.countStaff("testing"));
	}
	
	@Test
	public void create_staff() {
		staffController.createStaff(staff);
		verify(staffService, times(1)).save(staff);
	}
	
	@Test
	public void find_staff_by_id() {
		List<Staff> staffList = new ArrayList<>();
		when(staffService.findByUserId(0)).thenReturn(staffList);
		assertEquals(staffList, staffController.findById(0));
	}
	
	@Test
	public void find_rooms_where_staff_is_admin() {
		List<Room> roomList = new ArrayList<>();
		when(staffService.getRoomsStaffIsAdmin(0)).thenReturn(roomList);
		assertEquals(roomList, staffController.findRoomsWhereStaffIsAdmin(0));
	}
	
	@Test
	public void delete_staff() {
		staffController.deleteStaff(0);
		verify(staffService, times(1)).deleteById(0);
	}
}
