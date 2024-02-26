package com.fdmgroup.PCTrack.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class User {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int userId;
	
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "FIRST NAME")
	private String firstName;
	
	@Column(name = "LAST NAME")
	private String lastName;
	
	@Column(name = "JOIN DATE")
	private LocalDate joinDate;
	
	@OneToMany(mappedBy = "user")
	private List<Staff> roles;
	
	
	public User(String username, String password, String firstName, String lastName, LocalDate joinDate) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.joinDate = joinDate;
		this.roles = new ArrayList<>();
	}
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
	}
	public List<Staff> getRoles() {
		return roles;
	}
	public void setRoles(List<Staff> roles) {
		this.roles = roles;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", joinDate=" + joinDate + "]";
	}

	
	
}
