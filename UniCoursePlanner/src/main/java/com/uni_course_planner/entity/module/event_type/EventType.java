package com.uni_course_planner.entity.module.event_type;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.uni_course_planner.entity.module.module.Module;

import jakarta.persistence.*;

/**
 * Represents an event type assigned to a module.
 * Uses a composite key consisting of the user ID, module ID, and event type ID.
 */
@Entity(name = "event_type")
public class EventType 
{
	@EmbeddedId
	private EventTypeId eId;
	
	@MapsId("mId")
	@ManyToOne(optional = false)
	@JoinColumns({
		@JoinColumn(name = "user_id", referencedColumnName = "user_id"),
		@JoinColumn(name = "module_id", referencedColumnName = "module_id")
	})
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Module module;
	
	protected EventType() {}
	
	public EventType(EventTypeId eId, Module module)
	{
		this.eId = eId;
		this.module = module;
	}

	public EventTypeId geteId() 
	{
		return eId;
	}
	
	public Module getModule()
	{
		return module;
	}
}
