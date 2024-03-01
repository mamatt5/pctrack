package com.fdmgroup.PCTrack.model;

import jakarta.persistence.*;

@Entity
public class Software {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int softwareId;
	@Column(name = "NAME")
	private String name;
	
	public Software(String name) {
		super();
		this.name = name;
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

	@Override
	public String toString()
	{
		return "Software [softwareId=" + softwareId + ", name=" + name + "]";
	}

	
}

