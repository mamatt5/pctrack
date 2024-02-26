package com.fdmgroup.PCTrack.ControllerTests;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PCTrack.controller.LocationController;
import com.fdmgroup.PCTrack.model.Location;
import com.fdmgroup.PCTrack.model.User;
import com.fdmgroup.PCTrack.service.LocationService;

import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@ExtendWith(MockitoExtension.class)
public class LocationControllerTests {

		@Mock
		LocationService locationService;
		
		LocationController locationController;
		
		@BeforeEach
		void setup() {
			this.locationController = new LocationController(locationService);
		}
		
		@Test
		void findLocationById_test() {
			
			Location location1 = new Location("Sydney");
			
			when(locationService.findById(1)).thenReturn(location1);
			assertSame(location1, locationController.findById(1));
			verify(locationService, times(1)).findById(1);
		}
		
		@Test
		void createLocation_test() {
			Location location1 = new Location("Melbourne");
			
			when(locationService.findById(0)).thenReturn(location1);
			assertSame(location1, locationController.createNewLocation(location1));
			verify(locationService, times(1)).save(location1);
		}
		
		@Test
		void updateLocation_test() {
			Location location1 = new Location("Singapore");
			Location updatedLocation = new Location("Singaporealore");

			when(locationService.findById(0)).thenReturn(updatedLocation);
			assertSame(locationController.updateLocation(location1), updatedLocation);
			verify(locationService, times(1)).update(location1);
		}
		
		@Test
		void deleteLocation_test() {
			locationController.deleteLocation(1);
			verify(locationService, times(1)).deleteById(1);
		}
		
		@Test
		void findAllUsers() {
			Location location1 = new Location("Sydney");
			Location location2 = new Location("Melbourne");
			Location location3 = new Location("Singapore");
			Location location4 = new Location("Hong Kong");

			
			List<Location> allLocations = new ArrayList<>();
			allLocations.add(location1);
			allLocations.add(location2);
			allLocations.add(location3);
			allLocations.add(location4);
			
			when(locationService.findAllLocations()).thenReturn(allLocations);
			assertSame(locationController.getLocations(), allLocations);
			verify(locationService, times(1)).findAllLocations();
		}
		
		
}
