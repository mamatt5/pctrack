package com.fdmgroup.PCTrack.model;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.*;

/**
 * One of the main objects in the software, this object registers the data of a computer into the database.
 * It automatically designates a role based on the software that are installed on the computer.
 */

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
	@JoinColumn(name = "FK_PROGRAM_VERSION_ID"))
	private List<Program> programList;
	@Column(name = "ready_for_role")
	private String role;

	
	public Computer(int computerCode, List<Program> programList) {
		super();
		this.computerCode = computerCode;
		this.programList = programList;
		this.role = determineRole(this).name();
	}
	public Computer(int computerCode) {
		super();
		this.computerCode = computerCode;
		this.role = determineRole(this).name();
	}
	
	public Computer(int computerCode, Room room) {
		super();
		this.computerCode = computerCode;
		this.room = room;
		this.role = determineRole(this).name();
	}
	public Computer() {
		super();
		this.role = determineRole(this).name();
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
		this.role = determineRole(this).name();
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}	
	public int programIndex(int programId) {
		for (int i = 0; i < programList.size(); i++) {
			if (programList.get(i).getProgramId() == programId) {
				return i;
			}
		}
		return -1;
	}

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	/**
	 * The list of software required for a computer to be DEV/BI ready is taken from an interview with a stakeholder.
	 * In the future, it will be better to create another object that sets the list of software that needs to be
	 * installed in a computer to be designated a role.
	 * @param computer
	 * @return Role
	 */
	public Role determineRole(Computer computer) {
		boolean devReady = true;
		boolean biReady = true;
		
		List<String> devPrograms = Arrays.asList("Visual Studio.*", "Eclipse", "Node.js", "Python Launcher", 
                ".*NPM", ".*MySQL.*", "JDK", "Git");
		List<String> biPrograms = Arrays.asList("Excel", "PowerBi");
		
		List<Program> computerPrograms = computer.getProgramList();
		
		if (computerPrograms == null || computerPrograms.isEmpty()) {
			return Role.NONE;
		}
		
		for (String program : devPrograms) {
			boolean programFound = false;
			
			for (Program computerProgram : computerPrograms) {
				if (computerProgram.getSoftware().getName().matches(program)) {
					programFound = true;
					break;
				}
			}
			
			if (!programFound) {
				devReady = false;
				break;
			}
		}
		
		for (String program : biPrograms) {
			boolean programFound = false;
			
			for (Program computerProgram : computerPrograms) {
				if (computerProgram.getSoftware().getName().matches(program)) {
					programFound = true;
					break;
				}
			}
			
			if (!programFound) {
				biReady = false;
				break;
			}
		}
		
		
		if (biReady && devReady) {
			return Role.BOTH;
			
		} else if (biReady && !devReady) {
			return Role.BI;
			
		} else if (!biReady && devReady) {
			return Role.DEV;
			
		} else {
			return Role.NONE;
		}
		
	}
	
	@Override
	public String toString() {
		return "Computer [computerId=" + computerId + ", programList=" + programList + "]";
	}
}

