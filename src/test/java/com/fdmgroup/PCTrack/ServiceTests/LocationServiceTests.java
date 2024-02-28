package com.fdmgroup.PCTrack.ServiceTests;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PCTrack.dal.ComputerRepository;
import com.fdmgroup.PCTrack.dal.LocationRepository;
import com.fdmgroup.PCTrack.model.Computer;
import com.fdmgroup.PCTrack.model.Location;
import com.fdmgroup.PCTrack.model.Room;
import com.fdmgroup.PCTrack.service.ComputerService;
import com.fdmgroup.PCTrack.service.LocationService;

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
public class LocationServiceTests {

	@Mock
	LocationRepository locationRepo;
	
	LocationService locationService;
	
	Location location1;
	Room room1;
	
	@BeforeEach
	void setup() {
		
		this.locationService = new LocationService(locationRepo);
		
	}
	
	@Test
	void save_user_test() {
		
		Location location1 = new Location("FDM Sydney", "Sydney");
		
		locationService.save(location1);
		verify(locationRepo, times(1)).save(location1);
	}
	
	@Test
	void save_multiple_users_test() {
		

		Location location1 = new Location("FDM Sydney", "Sydney");
        Location location2 = new Location("FDM Hongkong", "Hong Kong");
        Location location3 = new Location("FDM Singapore", "Singapore");
		
      
		
        locationService.save(location1);
		verify(locationRepo, times(1)).save(location1);
		
		locationService.save(location2);
		verify(locationRepo, times(1)).save(location2);
		
		locationService.save(location3);
		verify(locationRepo, times(1)).save(location3);
		

	}
	
	
	@Test
	void find_all_user_test() {
		
	
		Location location1 = new Location("FDM Sydney", "Sydney");
        Location location2 = new Location("FDM Hongkong", "Hong Kong");
        Location location3 = new Location("FDM Singapore", "Singapore");
        
		List<Location> allLocations = new ArrayList<>();
		
		allLocations.add(location1);
		allLocations.add(location2);
		allLocations.add(location3);
		
		
		when(locationRepo.findAll()).thenReturn(allLocations);
		
		List<Location> foundLocations = locationService.findAllLocations();
	
		verify(locationRepo, times(1)).findAll();
		assertSame(foundLocations, allLocations);
	}
	
	@Test
	void find_user_by_id_test() {
		
		Optional<Location> location1 = Optional.of(new Location("FDM Sydney", "Sydney"));

		when(locationRepo.findById(1)).thenReturn(location1);
		Location foundLocation1 = locationService.findById(1);
		
		verify(locationRepo, times(1)).findById(1);
		assertSame(location1.get(), foundLocation1);

	}
	
	@Test
	void find_user_by_id_fail_test() {

		assertThrows(RuntimeException.class, () -> locationService.findById(1));
		verify(locationRepo, times(1)).findById(1);
	}
	
	@Test
	void update_user_test() {
		
		Location location1 = new Location("FDM Sydney", "Sydney");
		location1.setLocationId(1);
		
		when(locationRepo.existsById(1)).thenReturn(true);
		locationService.update(location1);
		
		verify(locationRepo, times(1)).existsById(1);
		verify(locationRepo, times(1)).save(location1);

	}
	
	@Test
	void update_user_fail_test() {
		
		Location location1 = new Location("FDM Sydney", "Sydney");
		location1.setLocationId(1);
		
		when(locationRepo.existsById(1)).thenReturn(false);
		
		assertThrows(RuntimeException.class, () -> locationService.update(location1));
		verify(locationRepo, times(1)).existsById(1);
		verify(locationRepo, times(0)).save(location1);
	}
	
	@Test
	void delete_user_test() {
		
		when(locationRepo.existsById(1)).thenReturn(true);
		
		locationService.deleteById(1);
		
		verify(locationRepo, times(1)).existsById(1);
		verify(locationRepo, times(1)).deleteById(1);

	}
	
	@Test
	void delete_user_fail_test() {
		
		when(locationRepo.existsById(1)).thenReturn(false);
		
		assertThrows(RuntimeException.class, () -> locationService.deleteById(1));
		verify(locationRepo, times(1)).existsById(1);
		verify(locationRepo, times(0)).deleteById(1);
	}
}
