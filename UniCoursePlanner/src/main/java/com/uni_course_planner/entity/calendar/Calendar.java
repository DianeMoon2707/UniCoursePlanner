package com.uni_course_planner.entity.calendar;

import java.time.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.uni_course_planner.entity.user.User;

import jakarta.persistence.*;

@Entity(name="calendar")
public class Calendar 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private LocalDate date;
	
	@Column(nullable = false)
	private LocalTime time;
	
	@Column(nullable = false)
	private String topic;
	
	@Column(nullable = true)
	private String extension;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	protected Calendar() {}

	public Calendar(LocalDate date, LocalTime time, String topic, String extension, User user) 
	{
		this.date = date;
		this.time = time;
		this.topic = topic;
		this.extension = extension;
		this.user = user;
	}

	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public LocalDate getDate() 
	{
		return date;
	}

	public void setDate(LocalDate date)
	{
		this.date = date;
	}

	public LocalTime getTime() 
	{
		return time;
	}

	public void setTime(LocalTime time)
	{
		this.time = time;
	}

	public User getUser() 
	{
		return user;
	}

	public void setUser(User user) 
	{
		this.user = user;
	}

	public String getTopic() 
	{
		return topic;
	}

	public void setTopic(String topic) 
	{
		this.topic = topic;
	}

	public String getExtension() 
	{
		return extension;
	}

	public void setExtension(String extension) 
	{
		this.extension = extension;
	}
}
