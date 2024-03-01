package com.fdmgroup.PCTrack.model;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("location_admin")
public class LocationAdmin extends RoomAdmin {

	
	public LocationAdmin() {
        // Default constructor required by JPA
		super(); 
    }
	public LocationAdmin( AdminLevel admin, User user, Location location) {
		super(admin, user, location);

	}
	
	
	public void setStaffLocation(Staff staff, Location location) {
		
	}
	
	public void addRoom(Room room, Location location) {
		
	}
	
	
}
