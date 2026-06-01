package com.uni_course_planner.relation.modul.modul;

import java.util.List;

import jakarta.persistence.*;

import com.uni_course_planner.relation.modul.event_type.*;

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
