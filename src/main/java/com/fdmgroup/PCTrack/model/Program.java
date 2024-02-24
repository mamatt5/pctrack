package com.fdmgroup.PCTrack.model;

public class Program {
	private int programId;
	private String name;
	private double versionNumber;
	
	public Program(int programId, String name, double versionNumber) {
		super();
		this.programId = programId;
		this.name = name;
		this.versionNumber = versionNumber;
	}

	public int getProgramId() {
		return programId;
	}

	public void setProgramId(int programId) {
		this.programId = programId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(double versionNumber) {
		this.versionNumber = versionNumber;
	}
	
}
