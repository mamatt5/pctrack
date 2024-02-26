package com.fdmgroup.PCTrack;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PCTrack.controller.LocationController;
import com.fdmgroup.PCTrack.service.LocationService;

import org.mockito.Mock;
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
		
}
