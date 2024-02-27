package com.fdmgroup.PCTrack.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.PCTrack.model.Location;
import com.fdmgroup.PCTrack.service.LocationService;
@RestController
public class LocationController {
	private LocationService locationService;

	public LocationController(LocationService locationService) {
		super();
		this.locationService = locationService;
	}
	
	public List<Location> getLocations() {
		return locationService.findAllLocations();
	}
	
	public Location findById(@PathVariable int locationId) {
		return locationService.findById(locationId);
	}
	
	public Location createNewLocation(@RequestBody Location newLocation) {
		locationService.save(newLocation);
		return locationService.findById(newLocation.getLocationId());
	}
	
	public Location updateLocation(@RequestBody Location newLocation) {
		locationService.update(newLocation);
		return locationService.findById(newLocation.getLocationId());
	}
	
	public void deleteLocation(@PathVariable int locationId) {
		locationService.deleteById(locationId);
	}

}