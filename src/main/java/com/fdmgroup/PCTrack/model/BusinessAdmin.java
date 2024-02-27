package com.fdmgroup.PCTrack.model;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("business_admin")
public class BusinessAdmin extends LocationAdmin {

	public BusinessAdmin(User user, Location location) {
		super(user, location);

	}
	
	public void addLocation(Location location) {
		
	}

}
