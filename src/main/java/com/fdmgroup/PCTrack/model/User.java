package com.fdmgroup.PCTrack.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
	
	@Id
	private int userId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private LocalDate joinDate;
	private List<Staff> roles;
	
	
	public User(int userId, String username, String password, String firstName, String lastName, LocalDate joinDate) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.joinDate = LocalDate.now();
		this.roles = new ArrayList<>();
	}
	
	public User(String username, String password, String firstName, String lastName, LocalDate joinDate) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.joinDate = LocalDate.now();
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
				+ lastName + ", joinDate=" + joinDate + ", roles=" + roles + "]";
	}

	
	
}
