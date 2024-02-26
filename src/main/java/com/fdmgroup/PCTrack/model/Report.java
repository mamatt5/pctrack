package com.fdmgroup.PCTrack.model;

import java.time.LocalDate;

public class Report {
	private int reportId;
	private Computer computer;
	private Staff staff;
	private LocalDate dateCreated;
	private String description;
	private boolean resolved;
	
	
	public Report(int reportId, Computer computer, Staff staff, LocalDate dateCreated, String description,
			boolean resolved) {
		super();
		this.reportId = reportId;
		this.computer = computer;
		this.staff = staff;
		this.dateCreated = dateCreated;
		this.description = description;
		this.resolved = resolved;
	}


	public int getReportId() {
		return reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	public Computer getComputer() {
		return computer;
	}

	public void setComputer(Computer computer) {
		this.computer = computer;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isResolved() {
		return resolved;
	}

	public void setResolved(boolean resolved) {
		this.resolved = resolved;
	}
	
}
