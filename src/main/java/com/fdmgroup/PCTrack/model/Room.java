package com.fdmgroup.PCTrack.model;

public class Room {
	private int roomId;
	private String name;
	private Location location;
	
	
	
	public Room() {
		super();
	}


	public Room(int roomId, String name, Location location) {
		super();
		this.roomId = roomId;
		this.name = name;
		this.location = location;
	}


	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
}
