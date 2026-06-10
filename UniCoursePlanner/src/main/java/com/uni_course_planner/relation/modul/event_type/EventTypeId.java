package com.uni_course_planner.relation.modul.event_type;

import java.io.Serializable;
import java.util.Objects;

import com.uni_course_planner.constants.modul.EventTypes;
import com.uni_course_planner.relation.modul.modul.ModulId;

import jakarta.persistence.*;

@Embeddable
public class EventTypeId implements Serializable
{
	private ModulId mId;
	
	@Enumerated(EnumType.STRING)
	private EventTypes type;
	
	public EventTypeId() {}

	public EventTypeId(ModulId mId, EventTypes type) 
	{
		this.mId = mId;
		this.type = type;
	}

	public ModulId getmId()
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
