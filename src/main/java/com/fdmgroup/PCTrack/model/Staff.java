package com.fdmgroup.PCTrack.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="staff_type")
@DiscriminatorValue("staff")
public class Staff {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int staffId;
	@JoinColumn(name = "FK_USER_ID")
	@ManyToOne
	private User user;
	@JoinColumn(name = "FK_LOCATION_ID")
	@ManyToOne
	private Location location;
	
	public Staff(User user, Location location) {
		super();
		this.user = user;
		this.location = location;
	}
	public Staff() {
		super();
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "Staff [staffId=" + staffId + ", user=" + user + ", location=" + location + "]";
	}
}





