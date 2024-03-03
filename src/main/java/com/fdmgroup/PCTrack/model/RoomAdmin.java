package com.fdmgroup.PCTrack.model;

import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("room_admin")
public class RoomAdmin extends Staff {
	
	// Ask floor if she manages all rooms or multiple admins can manage one room
	@OneToMany
	private List<Room> roomAssigned;
	
	
	public RoomAdmin() {
        // Default constructor required by JPA
		super(); 
    }
	
	
	public RoomAdmin(AdminLevel admin, User user, Location location) {
		super(admin, user, location);

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
