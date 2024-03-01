package com.fdmgroup.PCTrack.model;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("business_admin")
public class BusinessAdmin extends LocationAdmin {

	public BusinessAdmin() {
		super(); 
	}
	public BusinessAdmin(AdminLevel admin, User user, Location location) {
		super(admin, user, location);

	}
	
	
	
	public void addLocation(Location location) {
		
	}

}
