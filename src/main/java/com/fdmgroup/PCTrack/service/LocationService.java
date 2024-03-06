package com.fdmgroup.PCTrack.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fdmgroup.PCTrack.dal.LocationRepository;
import com.fdmgroup.PCTrack.dal.UserRepository;
import com.fdmgroup.PCTrack.model.AdminLevel;
import com.fdmgroup.PCTrack.model.Location;

@Service
public class LocationService { 
	private LocationRepository locationRepository;


	public LocationService(LocationRepository locationRepository) {
		super();
		this.locationRepository = locationRepository;
		
	}
	
	public List<Location> findAllLocations() {
		return this.locationRepository.findAll();
	}

	public Location findById(int locationId) {
		return this.locationRepository.findById(locationId).orElseThrow(()-> new RuntimeException("Location not found"));
	}
	
	public void save(Location newLocation) {
		if (this.locationRepository.existsById(newLocation.getLocationId())) {
			throw new RuntimeException("Location already exists");
		
		} else {
			this.locationRepository.save(newLocation);
		}
	}
	
	public void deleteById(int locationId) {
		if (this.locationRepository.existsById(locationId)) {
			locationRepository.deleteById(locationId);
		
		} else {
			throw new RuntimeException("Location does not exist");
		}
	}
	
	public void update(Location newLocation) {
		if (this.locationRepository.existsById(newLocation.getLocationId())) {
			this.locationRepository.save(newLocation);
		
		} else {
			throw new RuntimeException("Location does not exist");
		}
	}

	
	
}
