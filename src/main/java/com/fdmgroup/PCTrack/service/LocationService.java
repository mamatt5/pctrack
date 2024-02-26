package com.fdmgroup.PCTrack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fdmgroup.PCTrack.dal.LocationRepository;
import com.fdmgroup.PCTrack.model.Location;

@Service
public class LocationService {
	private LocationRepository locationRepo;
	
	@Autowired
	public LocationService(LocationRepository locationRepo) {
		super();
		this.locationRepo = locationRepo;
	}
	
	public List<Location> findAllLocations() {
		return this.locationRepo.findAll();
	}
	
	public Location findById(int locationId) {
		return this.locationRepo.findById(locationId).orElseThrow(() -> new RuntimeException("Location not found"));
	}
	
	public void save(Location newLocation) {
		if (this.locationRepo.existsById(newLocation.getLocationId())) {
			throw new RuntimeException("Location already exists");
		} else {
			this.locationRepo.save(newLocation);
		}
	}
}
