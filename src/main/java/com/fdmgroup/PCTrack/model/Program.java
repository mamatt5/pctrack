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
	
	public Program(String name) {
		super();
		this.name = name;
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

	@Override
	public String toString() {
		return "Program [programId=" + programId + ", name=" + name + "]";
	}
}

