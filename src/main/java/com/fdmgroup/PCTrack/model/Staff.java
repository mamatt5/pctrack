package com.fdmgroup.PCTrack.model;

import java.util.List;

import jakarta.persistence.*;


/**
 * This object represents the role of a user in the company. It is the most basic role in the web application with 
 * the most basic permissions which are basically read only permissions. It is from this entity where the admin
 * roles inherits in the following order (with increasing permission levels): Room, Location, Business. Note that
 * a user can have multiple Staff objects in it.
 */

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
	
	@JoinColumn(name = "FK_ADMIN_ID")
	@ManyToOne
    private AdminLevel adminLevel;
	
	public Staff() {
		super();
	}

	public Staff(AdminLevel type, User user, Location location) {
		super();
		this.user = user;
		this.location = location;
		this.adminLevel = type; 
	}
	
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	
	public AdminLevel getAdminLevel() {
		return adminLevel;
	}

	public void setAdminLevel(AdminLevel staffType) {
		this.adminLevel = staffType;
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





