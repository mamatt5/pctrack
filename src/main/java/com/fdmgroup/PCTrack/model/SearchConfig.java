package com.fdmgroup.PCTrack.model;

/**
 * This object is used for having the filter functionality in the frontend.
 */

public class SearchConfig {

	private String computerCode;
	
	private String roomId;
	
	private String role;
	
	public SearchConfig(String computerCode, String roomId, String role) {
		this.computerCode = computerCode;
		this.roomId = roomId;
		this.role = role;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
