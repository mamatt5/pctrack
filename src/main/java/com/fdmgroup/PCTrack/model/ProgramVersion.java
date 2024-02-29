package com.fdmgroup.PCTrack.model;

import jakarta.persistence.*;

@Entity
public class ProgramVersion {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int programVersionId;

    @ManyToOne
    @JoinColumn(name = "PROGRAM_ID")
    private Program program;

    @ManyToOne
    @JoinColumn(name = "VERSION_ID")
    private Version version;

	public ProgramVersion(Program program, Version version)
	{
		super();
		this.program = program;
		this.version = version;
	}
	
	public ProgramVersion()
	{
		super();
	}

	public int getProgramVersionId()
	{
		return programVersionId;
	}

	public void setProgramVersionId(int programVersionId)
	{
		this.programVersionId = programVersionId;
	}

	public Program getProgram()
	{
		return program;
	}

	public void setProgram(Program program)
	{
		this.program = program;
	}

	public Version getVersion()
	{
		return version;
	}

	public void setVersion(Version version)
	{
		this.version = version;
	}

	@Override
	public String toString()
	{
		return "ProgramVersion [programVersionId=" + programVersionId + ", program=" + program + ", version=" + version
		        + "]";
	}
    
    
}

