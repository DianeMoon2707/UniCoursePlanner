package com.uni_course_planner.relation.modul.modul;

import jakarta.persistence.*;

@Entity(name = "modul")
public class Modul 
{
	@EmbeddedId
	private ModulId mId;
	
	@Column(nullable = false)
	private String modulname;
	
	@Column(nullable = false)
	private int lp;
	
	protected Modul() {}

	public Modul(ModulId mId, String modulname, int lp)
	{
		this.mId = mId;
		this.modulname = modulname;
		this.lp = lp;
	}

	public ModulId getmId()
	{
		return mId;
	}

	public String getModulname() 
	{
		return modulname;
	}

	public int getLp() 
	{
		return lp;
	}
}
