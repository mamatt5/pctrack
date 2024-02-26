package com.fdmgroup.PCTrack.model;

import jakarta.persistence.*;

@Entity
public class Location {
	@Id
	@GeneratedValue
	private int locationId;
	private String name;
	private String city;
	
	public Location(String name, String city) {
		super();
		this.name = name;
		this.city = city;
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
	@Override
	public String toString() {
		return "Location [locationId=" + locationId + ", name=" + name + ", city=" + city + "]";
	}
}
