package com.fdmgroup.PCTrack.model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class Report {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int reportId;
	
	@ManyToOne
	@JoinColumn(name = "FK_COMPUTER_ID")
	private Computer computer;
	
	@ManyToOne
	@JoinColumn(name = "FK_STAFF_ID")
	private Staff staff;
	
	@Column(name = "DATE_CREATED")
	private LocalDate dateCreated;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "RESOLVED")
	private boolean resolved;

	public Report(Computer computer, Staff staff, LocalDate dateCreated, String description, boolean resolved) {
		super();
		this.computer = computer;
		this.staff = staff;
		this.dateCreated = dateCreated;
		this.description = description;
		this.resolved = resolved;
	}
	public Report() {
		super();
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
	@Override
	public String toString() {
		return "Report [reportId=" + reportId + ", computer=" + computer + ", staff=" + staff + ", dateCreated="
				+ dateCreated + ", description=" + description + ", resolved=" + resolved + "]";
	}
}
