package com.fdmgroup.PCTrack.model;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="staff_type")
@DiscriminatorValue("staff")
public class Staff {
	private Location location;
	private User user;
	
	public Staff(Location location, User user) {
		super();
		this.location = location;
		this.user = user;
	}
	
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	public List<Computer> findComputers(Role role) {
		return null;
	}
	
}




