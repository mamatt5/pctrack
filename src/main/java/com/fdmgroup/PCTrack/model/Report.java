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
	@JoinColumn(name = "FK_USER_ID")
	private User user;
	
	@Column(name = "DATE_CREATED")
	private LocalDate dateCreated;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "RESOLVED")
	private boolean resolved;

	public Report(Computer computer, User user, LocalDate dateCreated, String description) {
		super();
		this.computer = computer;
		this.user = user;
		this.dateCreated = dateCreated;
		this.description = description;
		this.resolved = false;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
		return "Report [reportId=" + reportId + ", computer=" + computer + ", user=" + user + ", dateCreated="
				+ dateCreated + ", description=" + description + ", resolved=" + resolved + "]";
	}
}
