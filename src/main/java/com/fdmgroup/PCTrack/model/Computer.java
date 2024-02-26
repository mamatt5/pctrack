package com.fdmgroup.PCTrack.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Computer {
	@Id
	@Column(name = "ID")
	private int computerId;
//	private Room room;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Computer_Program", 
	joinColumns =
	@JoinColumn(name = "FK_COMPUTER_ID"),
	inverseJoinColumns =
	@JoinColumn(name = "FK_PROGRAM_ID"))
	private List<Program> programList;
	
	public Computer(int computerId, List<Program> programList) {
		super();
		this.computerId = computerId;
		this.programList = programList;
	}
	public Computer() {
		super();
	}
	public int getComputerId() {
		return computerId;
	}
	public void setComputerId(int computerId) {
		this.computerId = computerId;
	}
	public List<Program> getProgramList() {
		return programList;
	}
	public void setProgramList(List<Program> programList) {
		this.programList = programList;
	}
	@Override
	public String toString() {
		return "Computer [computerId=" + computerId + ", programList=" + programList + "]";
	}
}
