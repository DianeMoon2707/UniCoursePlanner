package com.uni_course_planner.relation.modul.event_type;

import com.uni_course_planner.relation.modul.modul.Modul;

import jakarta.persistence.*;

@Entity(name = "event_type")
public class EventType 
{
	@EmbeddedId
	private EventTypeId eId;
	
	@MapsId("mId")
	@ManyToOne(optional = false)
	@JoinColumns({
		@JoinColumn(name = "user_id", referencedColumnName = "user_id"),
		@JoinColumn(name = "modul_id", referencedColumnName = "modul_id")
	})
	private Modul modul;
	
	protected EventType() {}
	
	public EventType(EventTypeId eId, Modul modul)
	{
		this.eId = eId;
		this.modul = modul;
	}

	public EventTypeId geteId() 
	{
		return eId;
	}
	
	public Modul getModul()
	{
		return modul;
	}
}
