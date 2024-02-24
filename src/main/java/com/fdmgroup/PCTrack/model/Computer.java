package com.fdmgroup.PCTrack.model;

import java.util.List;

public class Computer {
	private int computerId;
	private Room room;
	private Role isReady;
	private List<Program> programList;
	
	public Computer(int computerId, Room room, Role isReady, List<Program> programList) {
		super();
		this.computerId = computerId;
		this.room = room;
		this.isReady = isReady;
		this.programList = programList;
	}
	
	
	public int getComputerId() {
		return computerId;
	}
	public void setComputerId(int computerId) {
		this.computerId = computerId;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public Role getIsReady() {
		return isReady;
	}
	public void setIsReady(Role isReady) {
		this.isReady = isReady;
	}
	public List<Program> getProgramList() {
		return programList;
	}
	public void setProgramList(List<Program> programList) {
		this.programList = programList;
	}
	
	
}
