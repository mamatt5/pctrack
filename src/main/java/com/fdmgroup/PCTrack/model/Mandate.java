package com.fdmgroup.PCTrack.model;

import java.time.LocalDate;

public class Mandate {
	private int mandateId;
	private Room room;
	private RoomAdmin roomAdmin;
	private LocalDate dateCreated;
	private String description;
	
	public Mandate(int mandateId, Room room, RoomAdmin roomAdmin, LocalDate dateCreated, String description) {
		super();
		this.mandateId = mandateId;
		this.room = room;
		this.roomAdmin = roomAdmin;
		this.dateCreated = dateCreated;
		this.description = description;
	}
	
	
	public int getMandateId() {
		return mandateId;
	}
	public void setMandateId(int mandateId) {
		this.mandateId = mandateId;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public RoomAdmin getRoomAdmin() {
		return roomAdmin;
	}
	public void setRoomAdmin(RoomAdmin roomAdmin) {
		this.roomAdmin = roomAdmin;
	}
	public LocalDate getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
