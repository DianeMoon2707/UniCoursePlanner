package com.uni_course_planner.entity.module.event_type;

import java.io.Serializable;
import java.util.Objects;

import com.uni_course_planner.constants.module.EventTypes;
import com.uni_course_planner.entity.module.module.ModuleId;

import jakarta.persistence.*;

@Embeddable
public class EventTypeId implements Serializable
{
	private ModuleId mId;
	
	@Enumerated(EnumType.STRING)
	private EventTypes type;
	
	public EventTypeId() {}

	public EventTypeId(ModuleId mId, EventTypes type) 
	{
		this.mId = mId;
		this.type = type;
	}

	public ModuleId getmId()
	{
		return mId;
	}

	public EventTypes getType()
	{
		return type;
	}

	@Override
	public int hashCode() 
	{
		return Objects.hash(mId, type);
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventTypeId other = (EventTypeId) obj;
		return Objects.equals(mId, other.mId) && type == other.type;
	}
}
