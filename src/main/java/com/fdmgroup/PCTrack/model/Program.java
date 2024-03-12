package com.fdmgroup.PCTrack.model;

import jakarta.persistence.*;

/**
 * This object represents the actual software along with its version installed in a computer.
 */

@Entity
public class Program {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int programId;

    @ManyToOne
    @JoinColumn(name = "SOFTWARE_ID")
    private Software software;

    @Column(name = "VERSION")
    private String version;

	public Program(Software software, String version)
	{
		super();
		this.software = software;
		this.version = version;
	}
	
	public Program()
	{
		super();
	}

	public int getProgramId()
	{
		return programId;
	}

	public void setProgramId(int programId)
	{
		this.programId = programId;
	}

	public Software getSoftware()
	{
		return software;
	}

	public void setSoftware(Software software)
	{
		this.software = software;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString()
	{
		return "Program [programId=" + programId + ", software=" + software + ", version=" + version + "]";
	}

    
    
}

