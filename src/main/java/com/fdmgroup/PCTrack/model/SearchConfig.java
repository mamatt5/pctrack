package com.fdmgroup.PCTrack.model;

public class SearchConfig {

	private String computerCode;
	
	private String roomId;
	
	public SearchConfig(String computerCode, String roomId) {
		this.computerCode = computerCode;
		this.roomId = roomId;
	}

	public SearchConfig() {
		super();
	}

	public String getComputerCode() {
		return computerCode;
	}

	public void setComputerCode(String computerCode) {
		this.computerCode = computerCode;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
}
