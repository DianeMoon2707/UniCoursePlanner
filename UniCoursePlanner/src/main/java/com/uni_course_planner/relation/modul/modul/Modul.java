package com.uni_course_planner.relation.modul.modul;

import java.util.List;

import jakarta.persistence.*;

import com.uni_course_planner.relation.modul.event_type.*;
import com.uni_course_planner.relation.modul.modulnote.Modulnote;

@Entity(name = "modul")
public class Modul 
{
	@EmbeddedId
	private ModulId mId;
	
	@Column(nullable = false)
	private String modulname;
	
	@Column(nullable = false)
	private int lp;
	
	@OneToMany(mappedBy = "modul", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<EventType> eventTypes;
	
	@OneToOne(mappedBy = "modul", cascade = CascadeType.ALL, orphanRemoval = true)
	private Modulnote modulnote;
	
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

	public void setmId(ModulId mId) 
	{
		this.mId = mId;
	}

	public String getModulname()
	{
		return modulname;
	}

	public void setModulname(String modulname) 
	{
		this.modulname = modulname;
	}

	public int getLp() 
	{
		return lp;
	}

	public void setLp(int lp) 
	{
		this.lp = lp;
	}
}
