package com.fdmgroup.PCTrack.model;

import java.time.LocalDate;
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
	private LocalDate dateCreated;
	
	@Column(name = "DESCRIPTION")
	private String description;

	public Mandate(Room room, RoomAdmin roomAdmin, String description) {
		super();
		this.room = room;
		this.roomAdmin = roomAdmin;
		this.dateCreated = LocalDate.now();
		this.description = description;
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
	@Override
	public String toString() {
		return "Mandate [mandateId=" + mandateId + ", room=" + room + ", dateCreated=" + dateCreated + ", description="
				+ description + "]";
	}
}

