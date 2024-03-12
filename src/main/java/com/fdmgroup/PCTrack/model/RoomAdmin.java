package com.fdmgroup.PCTrack.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("room_admin")
public class RoomAdmin extends Staff {
	
	@ManyToMany
	private List<Room> roomAssigned;
	
	
	public RoomAdmin() {
		super(); 
    }
	
	
	public RoomAdmin(AdminLevel admin, User user, Location location) {
		super(admin, user, location);
		this.roomAssigned = new ArrayList<>();


	}

	public RoomAdmin(AdminLevel admin, User user, Location location, List<Room> rooms) {
		super(admin, user, location);
		this.roomAssigned = rooms;
	} 
	
	public List<Room> getRoomAssigned() {
		return roomAssigned;
	}

	public void setRoomAssigned(List<Room> roomAssigned) {
		this.roomAssigned = roomAssigned;
	}
	
	
	public void assignStaff(Staff staff, Room room) {
		
	}
	
	public void addComputer(Computer computer, Room room) {  
		
	} 
	
	public void addRoomAssigned(Room room) {
		
	}
	
	public void removeRoomAssigned(Room room) { 
		
	}

}
