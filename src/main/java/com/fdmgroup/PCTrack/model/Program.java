package com.fdmgroup.PCTrack.model;

import jakarta.persistence.*;

@Entity
public class Program {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int programId;
	@Column(name = "NAME")
	private String name;
	@Column(name = "VERSION NO.")
	private String versionNumber;
	
	public Program(String name, String versionNumber) {
		super();
		this.name = name;
		this.versionNumber = versionNumber;
	}
	public Program() {
		super();
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
	public String getVersionNumber() {
		return versionNumber;
	}
	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}
	@Override
	public String toString() {
		return "Program [programId=" + programId + ", name=" + name + ", versionNumber=" + versionNumber + "]";
	}
}
