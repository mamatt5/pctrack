package com.fdmgroup.PCTrack.model;

import jakarta.persistence.*;

@Entity
public class Location {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int locationId;
	@Column(name = "NAME")
	private String name;
	@Column(name = "CITY")
	private String city;
<<<<<<< HEAD
	//private List<Room> rooms;
=======
>>>>>>> origin/SQLCheck
	
	public Location(String name, String city) {
		super();
<<<<<<< HEAD
	}

	public Location(int locationId, String name, String city) {
		super();
		this.locationId = locationId;
		this.name = name;
		this.city = city;
		//this.rooms = rooms;
=======
		this.name = name;
		this.city = city;
>>>>>>> origin/SQLCheck
	}
	public Location() {
		super();
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
<<<<<<< HEAD
//	public List<Room> getRooms() {
//		return rooms;
//	}
//	public void setRooms(List<Room> rooms) {
//		this.rooms = rooms;
//	}
	
	
=======
	@Override
	public String toString() {
		return "Location [locationId=" + locationId + ", name=" + name + ", city=" + city + "]";
	}
>>>>>>> origin/SQLCheck
}
