package com.uni_course_planner.entity.timetable;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.uni_course_planner.constants.timetable.*;
import com.uni_course_planner.entity.module.event_type.EventType;

import jakarta.persistence.*;

//Represents a timetable entry linking a time slot and weekday to an event type.
@Entity(name="timetable")
public class Timetable 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Timeslot time;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Weekday day;
	
	@Column
	private String room;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(optional = false)
	@JoinColumns({
		@JoinColumn(name = "user_id", referencedColumnName = "user_id"),
		@JoinColumn(name = "module_id", referencedColumnName = "module_id"),
		@JoinColumn(name = "type", referencedColumnName = "type")
	})
	private EventType event;
	
	protected Timetable() {}

	public Timetable(Timeslot time, Weekday day, String room, EventType event)
	{
		this.time = time;
		this.day = day;
		this.room = room;
		this.event = event;
	}

	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Timeslot getTime() 
	{
		return time;
	}

	public void setTime(Timeslot time)
	{
		this.time = time;
	}

	public Weekday getDay() 
	{
		return day;
	}

	public void setDay(Weekday day) 
	{
		this.day = day;
	}

	public String getRoom()
	{
		return room;
	}

	public void setRoom(String room) 
	{
		this.room = room;
	}

	public EventType getEvent() 
	{
		return event;
	}

	public void setEvent(EventType event) 
	{
		this.event = event;
	}
}
