package com.fdmgroup.PCTrack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.PCTrack.model.Location;
import com.fdmgroup.PCTrack.service.LocationService;


@RestController
@CrossOrigin("http://localhost:5173")
public class LocationController {
	private LocationService locationService;

	@Autowired
	public LocationController(LocationService locationService) {
		super();
		this.locationService = locationService;
	}
	 
	
	@GetMapping("locations")
	public List<Location> getLocations() {
		return locationService.findAllLocations();
	}
	
	@GetMapping("locations/{locationId}")
	public Location findById(@PathVariable int locationId) {
		return locationService.findById(locationId);
	}
	
	@PostMapping("locations")
	public Location createNewLocation(@RequestBody Location newLocation) {
		locationService.save(newLocation);
		return locationService.findById(newLocation.getLocationId());
	}

	@PutMapping("location")
	public Location updateLocation(@RequestBody Location newLocation) {
		locationService.update(newLocation);
		return locationService.findById(newLocation.getLocationId());
	}
	
	@DeleteMapping("locations")
	
	public void deleteLocation(@PathVariable int locationId) {
		locationService.deleteById(locationId);
	}  
	
	
}
