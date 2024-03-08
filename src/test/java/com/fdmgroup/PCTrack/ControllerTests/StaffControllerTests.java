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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.fdmgroup.PCTrack.controller.StaffController;
import com.fdmgroup.PCTrack.model.AdminLevel;
import com.fdmgroup.PCTrack.model.Location;
import com.fdmgroup.PCTrack.model.Room;
import com.fdmgroup.PCTrack.model.Staff;
import com.fdmgroup.PCTrack.model.User;
import com.fdmgroup.PCTrack.service.AdminLevelService;
import com.fdmgroup.PCTrack.service.LocationService;
import com.fdmgroup.PCTrack.service.StaffService;

@ExtendWith(MockitoExtension.class)
public class StaffControllerTests {
	
	@Mock
	StaffService staffService;
	@Mock
	LocationService locationService;
	@Mock
	AdminLevelService adminLevelService;
	
	Staff staff;
	Room room;
	StaffController staffController;
	Page<Staff> page;
	
	@BeforeEach
	public void setup() {
		staffController = new StaffController(staffService, locationService, adminLevelService);
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
		List<Integer> locations = new ArrayList<>();
		List<Integer> adminLevels = new ArrayList<>();
		when(staffService.staffCountPartial("testing", locations, adminLevels)).thenReturn((long) 5);
		assertEquals((long) 5, staffController.countStaff("testing", locations, adminLevels));
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
	
	@Test
	void get_paginated_staff() {
		List<Staff> paginatedStaffList = new ArrayList<>();
		page = new PageImpl<Staff>(paginatedStaffList);
		when(staffService.getStaffPage(1, 10)).thenReturn(page);
		assertEquals(paginatedStaffList, staffController.getStaffPage(1, 10));
	}
	
	@Test
	void get_paginated_partial_staff() {
		List<Integer> locations = new ArrayList<>();
		List<Integer> adminLevels = new ArrayList<>();
		List<Staff> paginatedStaffList = new ArrayList<>();
		when(staffService.findAllUsersPartialMatch("test", 1, 10, locations, adminLevels)).thenReturn(paginatedStaffList);
		assertEquals(paginatedStaffList, staffController.getStaffPartial("test", locations, adminLevels, 1, 10));
	}
	
	@Test
	void get_rooms_where_staff_is_registered() {
		List<Room> registeredRoomList = new ArrayList<>();
		when(staffService.getRoomStaffIsRegisterdIn(0)).thenReturn(registeredRoomList);
		assertEquals(registeredRoomList, staffController.findRoomsWhereStaffIsRegistered(0));
	}
	
	@Test
	void get_staff_by_location_and_admin_levels() {
		List<Integer> locationIds = new ArrayList<>();
		List<Integer> adminLevelIds = new ArrayList<>();
		
		List<Staff> staffList = new ArrayList<>();
		List<Location> locations = new ArrayList<>();
		List<AdminLevel> adminLevels = new ArrayList<>();
		
		when(locationService.getLocationsByIds(locationIds)).thenReturn(locations);
		when(adminLevelService.getAdminLevelsByIds(adminLevelIds)).thenReturn(adminLevels);
		when(staffService.getLocationAdminFilteredStaff(1, 10, locations, adminLevels)).thenReturn(staffList);
		
		assertEquals(staffList, staffController.getStaffByLocationsAndAdminLevels(locationIds, adminLevelIds, 1, 10));
	}
	
	@Test
	void count_filtered_staff_by_location_and_admin_level() {
		List<Integer> locationIds = new ArrayList<>();
		List<Integer> adminLevelIds = new ArrayList<>();
		
		List<Location> locations = new ArrayList<>();
		List<AdminLevel> adminLevels = new ArrayList<>();
		
		when(locationService.getLocationsByIds(locationIds)).thenReturn(locations);
		when(adminLevelService.getAdminLevelsByIds(adminLevelIds)).thenReturn(adminLevels);
		when(staffService.staffCountFiltered(locations, adminLevels)).thenReturn(10L);
		
		assertEquals(10L, staffController.countStaffFiltered(locationIds, adminLevelIds));
	}
}
