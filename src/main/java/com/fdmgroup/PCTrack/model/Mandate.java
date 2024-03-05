package com.fdmgroup.PCTrack.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.*;

@Entity
public class Mandate {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int mandateId;
	
	@ManyToOne
	@JoinColumn(name = "FK_ROOM_ID")
	private Room room;
	
	@ManyToOne
	@JoinColumn(name = "FK_ROOM_ADMIN")
	private RoomAdmin roomAdmin;
		
	@Column(name = "DATE_CREATED")
	private String dateCreated;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "DEADLINE")
	private String deadline;

	public Mandate(Room room, RoomAdmin roomAdmin, String description, String deadline) {
		super();
		this.room = room;
		this.roomAdmin = roomAdmin;
		this.dateCreated = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
		this.description = description;
		this.deadline = LocalDate.parse(deadline).format(DateTimeFormatter.ISO_DATE);
	}
	
	public Mandate() {
		super();
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
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Mandate [mandateId=" + mandateId + ", room=" + room + ", dateCreated=" + dateCreated + ", description="
				+ description + "]";
	}
}

