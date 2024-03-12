package com.fdmgroup.PCTrack.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

/**
 * This object is a generic representation of a software that is installed in the computer, regardless
 * of its version. This is used for modularity in the data handling.
 */
@Entity
public class Software {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int softwareId;
	@Column(name = "NAME")
	private String name;
	@JsonIgnoreProperties("software")
	@OneToMany(mappedBy = "software")
	private List<Program> versions;
	
	public Software(String name) {
		super();
		this.name = name;
	}

	public Software()
	{
		super();
	}


	public int getSoftwareId()
	{
		return softwareId;
	}

	public void setSoftwareId(int softwareId)
	{
		this.softwareId = softwareId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<Program> getVersions() {
		return versions;
	}

	public void setVersions(List<Program> versions) {
		this.versions = versions;
	}

	@Override
	public String toString()
	{
		return "Software [softwareId=" + softwareId + ", name=" + name + "]";
	}

	
}

