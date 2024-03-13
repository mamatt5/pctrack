package com.fdmgroup.PCTrack.ServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.fdmgroup.PCTrack.controller.StaffController;
import com.fdmgroup.PCTrack.dal.MandateRepository;
import com.fdmgroup.PCTrack.dal.RoomAdminRepository;
import com.fdmgroup.PCTrack.dal.StaffRepository;
import com.fdmgroup.PCTrack.model.AdminLevel;
import com.fdmgroup.PCTrack.model.Location;
import com.fdmgroup.PCTrack.model.Mandate;
import com.fdmgroup.PCTrack.model.Room;
import com.fdmgroup.PCTrack.model.RoomAdmin;
import com.fdmgroup.PCTrack.model.Staff;
import com.fdmgroup.PCTrack.model.User;
import com.fdmgroup.PCTrack.service.StaffService;

@ExtendWith(MockitoExtension.class)
public class StaffServiceTests {

	@Mock
	StaffRepository staffRepo;
	@Mock
	MandateRepository mandateRepo;
	@Mock
	RoomAdminRepository roomAdminRepo;

	StaffService staffService;
	Staff staff;
	Page<Staff> page;

	@BeforeEach
	public void setup() {
		staffService = new StaffService(staffRepo, mandateRepo, roomAdminRepo);
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
		RoomAdmin roomAdmin = new RoomAdmin();
		List<Mandate> roomMandates = new ArrayList<>();
		roomMandates.add(new Mandate());
		when(roomAdminRepo.findById(0)).thenReturn(Optional.of(roomAdmin));
		when(staffRepo.existsById(0)).thenReturn(true);
		when(mandateRepo.findByRoomAdmin(roomAdmin)).thenReturn(roomMandates);
		staffService.deleteById(0);
		verify(staffRepo, times(1)).existsById(0);
		verify(mandateRepo).delete(any(Mandate.class));
	}
	
	@Test
	public void delete_throws_when_staff_does_not_exist() {
		assertThrows(RuntimeException.class, ()-> staffService.deleteById(2));
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
	public void find_staff_by_id_throws_when_not_found()
	{
		assertThrows(RuntimeException.class, () -> staffService.findByStaffId(0));
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
		when(staffRepo.existsById(staff.getStaffId())).thenReturn(true);
		staffService.update(staff);
		verify(staffRepo, times(1)).save(staff);
	}
	
	@Test
	public void update_throws_when_staff_does_not_exist()
	{
		staff = new Staff();
		when(staffRepo.existsById(staff.getStaffId())).thenReturn(false);
		assertThrows(RuntimeException.class, () -> staffService.update(staff));
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
	
	@Test
	public void get_location_admin_with_staff_filter() {
	    List<Staff> staffList = new ArrayList<>();
	    List<Location> locations = new ArrayList<>();
	    List<AdminLevel> adminLevels = new ArrayList<>();
	    Page<Staff> page = new PageImpl<>(staffList);
	    Pageable pageable = PageRequest.of(1, 10, Sort.by("user.username").ascending());

	    when(staffRepo.findByLocationInAndAdminLevelIn(locations, adminLevels, pageable)).thenReturn(page);
	    assertEquals(staffList, staffService.getLocationAdminFilteredStaff(1, 10, locations, adminLevels));
	}
	
	@Test
	public void get_staff_page() {
		List<Staff> staffList = new ArrayList<>();
		Page<Staff> page = new PageImpl<>(staffList);
		Pageable pageable = PageRequest.of(1,10, Sort.by("user.username").ascending());
		
		when(staffRepo.findAll(pageable)).thenReturn(page);
		assertEquals(page, staffService.getStaffPage(1, 10));
	}
	
	@Test
	public void get_staff_page_with_partial_match() {
		List<Staff> staffList = new ArrayList<>();
		List<Integer> adminLevels = new ArrayList<>();
		List<Integer> locations = new ArrayList<>();
		Page<Staff> page = new PageImpl<>(staffList);
		Pageable pageable = PageRequest.of(1,10);
		
		when(staffRepo.findPartial("test", pageable, locations, adminLevels)).thenReturn(page);
		assertEquals(staffList, staffService.findAllUsersPartialMatch("test", 1, 10, locations, adminLevels));
	}
	
	@Test
	public void count_staff_by_location_and_admin_levels() {
	    List<Location> locations = new ArrayList<>();
	    List<AdminLevel> adminLevels = new ArrayList<>();
		when(staffRepo.countByLocationInAndAdminLevelIn(locations, adminLevels)).thenReturn(10L);
		assertEquals(10L, staffService.staffCountFiltered(locations, adminLevels));
	}

}
