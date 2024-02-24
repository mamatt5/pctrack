package com.fdmgroup.PCTrack.model;

import java.util.List;

public class Location {
	private int locationId;
	private String name;
	private String city;
	private List<Room> rooms;
	
	public Location(int locationId, String name, String city, List<Room> rooms) {
		super();
		this.locationId = locationId;
		this.name = name;
		this.city = city;
		this.rooms = rooms;
	}
	
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public List<Room> getRooms() {
		return rooms;
	}
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
	
	
}
