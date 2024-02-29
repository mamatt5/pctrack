package com.fdmgroup.PCTrack.model;

import jakarta.persistence.*;

@Entity
public class Version {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int versionId;
	@Column(name = "VERSION NO.")
	private String versionNumber;
	
	public Version(String versionNumber) {
		super();
		this.versionNumber = versionNumber;
	}
	public Version() {
		super();
	}
	
	public int getVersionId() {
		return versionId;
	}
	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}

	public String getVersionNumber() {
		return versionNumber;
	}
	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}
	@Override
	public String toString() {
		return "Version [versionId=" + versionId + ", versionNumber=" + versionNumber + "]";
	}
}

