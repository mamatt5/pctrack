package com.fdmgroup.PCTrack.ControllerTests;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PCTrack.controller.LocationController;
import com.fdmgroup.PCTrack.controller.MandateController;
import com.fdmgroup.PCTrack.model.AdminLevel;
import com.fdmgroup.PCTrack.model.Location;
import com.fdmgroup.PCTrack.model.Mandate;
import com.fdmgroup.PCTrack.model.Room;
import com.fdmgroup.PCTrack.model.RoomAdmin;
import com.fdmgroup.PCTrack.model.User;
import com.fdmgroup.PCTrack.service.LocationService;
import com.fdmgroup.PCTrack.service.MandateService;

import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
public class MandateControllerTests {
	
	@Mock
	MandateService mandateService;
	
	MandateController mandateController;
	Room room;
	RoomAdmin roomAdmin;
	AdminLevel Room;
	Mandate mandate1;
	Mandate mandate2;
	Mandate mandate3;
	
	@BeforeEach
	public void setup() {
		mandateController = new MandateController(mandateService);
//		Location location = new Location("FDM Sydney", "Sydney");
//		Room room = new Room("Bondi", location);
//		User user = new User("admin", "0000!!", "Root1", "Admin", "admin1@example.com");
//		RoomAdmin roomAdmin = new RoomAdmin(Room, user, location);
		
	}
	
	@Test
	public void find_all_mandates() {
		String mandate1Desc = "We need 10 DEV-ready computers.";
		Mandate mandate1 = new Mandate(room, roomAdmin, mandate1Desc, "2024-03-10");

		String mandate2Desc = "We need 10 BI-ready computers.";
		Mandate mandate2 = new Mandate(room, roomAdmin, mandate2Desc, "2024-03-10");

		String mandate3Desc = "We need 10 DEV and BI-ready computers.";
		Mandate mandate3 = new Mandate(room, roomAdmin, mandate3Desc, "2024-03-10");
		
		List<Mandate> allMandates = new ArrayList<>();
		allMandates.add(mandate1);
		allMandates.add(mandate2);
		allMandates.add(mandate3);
		
		when(mandateService.findAllMandate()).thenReturn(allMandates);
		assertEquals(allMandates, mandateController.getMandates());
		
	}
	
	@Test
	public void find_mandate_by_id() {
		when(mandateService.findById(0)).thenReturn(mandate1);
		assertEquals(mandate1, mandateController.findById(0));
	}
	
	@Test
	public void create_new_mandate() {
		Mandate mandate1 = new Mandate(room, roomAdmin, "", "2024-03-10");
		mandate1.setMandateId(0);
		mandateController.createNewMandate(mandate1);
		verify(mandateService, times(1)).save(mandate1);
		verify(mandateService, times(1)).findById(0);
	}
	
	@Test
	public void update_mandate() {
		Mandate mandate1 = new Mandate(room, roomAdmin, "", "2024-03-10");
		mandate1.setMandateId(0);
		mandateController.updateMandate(mandate1);
		verify(mandateService, times(1)).update(mandate1);
		verify(mandateService, times(1)).findById(0);
	}
	
	@Test
	public void delete_mandate() {
		mandateController.deleteMandate(0);
		verify(mandateService, times(1)).deleteById(0);
	}
	
	@Test
	public void find_mandates_by_room() {
		List<Mandate> allMandates = new ArrayList<>();
		allMandates.add(mandate1);
		allMandates.add(mandate2);
		allMandates.add(mandate3);
		
		when(mandateService.findByRoom(0)).thenReturn(allMandates);
		assertEquals(allMandates, mandateController.findMandateByRoom(0));
	}

}
 