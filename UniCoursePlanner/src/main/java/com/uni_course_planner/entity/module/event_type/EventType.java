package com.uni_course_planner.entity.module.event_type;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.uni_course_planner.entity.module.module.Module;

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
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Module modul;
	
	protected EventType() {}
	
	public EventType(EventTypeId eId, Module modul)
	{
		this.eId = eId;
		this.modul = modul;
	}

	public EventTypeId geteId() 
	{
		return eId;
	}
	
	public Module getModul()
	{
		return modul;
	}
}
