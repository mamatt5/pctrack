package com.fdmgroup.PCTrack.model;

import jakarta.persistence.*;

@Entity
public class Room {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int roomId;
	
	@Column(name = "NAME")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "FK_LOCATION_ID")
	private Location location;

	public Room(String name, Location location) {
		super();
		this.name = name;
		this.location = location;
	}
	public Room() {
		super();
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
	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", name=" + name + ", location=" + location + "]";
	}
}
