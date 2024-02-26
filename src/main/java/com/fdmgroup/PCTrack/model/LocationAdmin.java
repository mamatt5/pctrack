package com.fdmgroup.PCTrack.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("location_admin")
public class LocationAdmin extends RoomAdmin {

	public LocationAdmin(User user, Location location) {
		super(user, location);
	}

	
	public void setStaffLocation(Staff staff, Location location) {
		
	}
	
	public void addRoom(Room room, Location location) {
		
	}
	
	
}
