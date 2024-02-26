package com.fdmgroup.PCTrack.model;

import java.util.List;

public class RoomAdmin extends Staff {
	private List<Room> roomAssigned;
	
	public RoomAdmin(Location location, User user) {
		super(location, user);
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
