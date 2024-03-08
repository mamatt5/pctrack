package com.fdmgroup.PCTrack.ServiceTests;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PCTrack.dal.MandateRepository;
import com.fdmgroup.PCTrack.dal.RoomRepository;
import com.fdmgroup.PCTrack.model.Mandate;
import com.fdmgroup.PCTrack.model.Room;
import com.fdmgroup.PCTrack.service.MandateService;

import org.mockito.Mock;

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

@ExtendWith(MockitoExtension.class)
public class MandateServiceTests {
	
	@Mock
	MandateRepository mandateRepo;
	
	@Mock
	RoomRepository roomRepo;
	
	MandateService mandateService;
	Room room;
	Mandate mandate;
	Mandate mandate2;
	
	@BeforeEach
	public void setup() {
		mandateService = new MandateService(mandateRepo, roomRepo);
	}
	
	@Test
	public void get_all_mandates() {
		List<Mandate> allMandates = new ArrayList<>();
		when(mandateRepo.findAll()).thenReturn(allMandates);
		assertEquals(allMandates, mandateService.findAllMandate());
	}

	@Test
	public void get_mandate_by_id() {
		mandate = new Mandate();
		mandate.setMandateId(0);
		when(mandateRepo.findById(0)).thenReturn(Optional.of(mandate));
		assertEquals(mandate, mandateService.findById(0));
	}
	
	@Test
	public void save_mandate() {
		mandate = new Mandate();
		mandate.setMandateId(0);
		when(mandateRepo.existsById(0)).thenReturn(false);
		mandateService.save(mandate);
		verify(mandateRepo, times(1)).save(mandate);
	}
	
	@Test
	public void save_throws_error_when_mandate_exists_already() {
		mandate = new Mandate();
		when(mandateRepo.existsById(mandate.getMandateId())).thenReturn(true);
		assertThrows(RuntimeException.class, ()-> mandateService.save(mandate));
	}
	
	@Test
	public void delete_mandate_by_id() {
		when(mandateRepo.existsById(0)).thenReturn(true);
		mandateService.deleteById(0);
		verify(mandateRepo, times(1)).deleteById(0);
	}
	
	@Test
	public void delete_throws_error_when_mandate_does_not_exist() {
		when(mandateRepo.existsById(0)).thenReturn(false);
		assertThrows(RuntimeException.class, ()-> mandateService.deleteById(0));
	}
	
	@Test
	public void update_mandate() {
		mandate = new Mandate();
		when(mandateRepo.existsById(mandate.getMandateId())).thenReturn(true);
		mandateService.update(mandate);
		verify(mandateRepo, times(1)).save(mandate);
	}
	
	@Test
	public void update_throws_error_when_mandate_does_not_already() {
		mandate = new Mandate();
		when(mandateRepo.existsById(mandate.getMandateId())).thenReturn(false);
		assertThrows(RuntimeException.class, ()-> mandateService.update(mandate));
	}
	
	@Test
	public void get_all_mandates_by_room() {
		room = new Room();
		room.setRoomId(0);
		mandate = new Mandate();
		mandate2 = new Mandate();
		mandate.setRoom(room);
		
		List<Mandate> allMandates = new ArrayList<>();
		allMandates.add(mandate);
		allMandates.add(mandate2);
		
		List<Mandate> roomMandates = new ArrayList<>();
		roomMandates.add(mandate);
		
		when(roomRepo.findById(0)).thenReturn(Optional.of(room));
		when(mandateRepo.findAll()).thenReturn(allMandates);
		
		assertEquals(roomMandates, mandateService.findByRoom(0));
	}
}
