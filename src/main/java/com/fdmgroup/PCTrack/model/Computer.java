package com.fdmgroup.PCTrack.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Computer {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int computerId;
	@Column(name = "COMPUTER_CODE")
	private int computerCode;
	@ManyToOne
	@JoinColumn(name = "FK_ROOM_ID")
	private Room room;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Computer_Program", 
	joinColumns =
	@JoinColumn(name = "FK_COMPUTER_ID"),
	inverseJoinColumns =
	@JoinColumn(name = "FK_PROGRAM_ID"))
	private List<Program> programList;
	
	public Computer(int computerCode, List<Program> programList) {
		super();
		this.computerCode = computerCode;
		this.programList = programList;
	}
	public Computer(int computerCode) {
		super();
		this.computerCode = computerCode;
	}
	
	public Computer(int computerCode, Room room) {
		super();
		this.computerCode = computerCode;
		this.room = room;
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
	public int getComputerCode()
	{
		return computerCode;
	}
	public void setComputerCode(int computerCode)
	{
		this.computerCode = computerCode;
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

