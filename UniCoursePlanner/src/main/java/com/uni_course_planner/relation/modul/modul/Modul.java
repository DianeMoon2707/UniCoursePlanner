package com.uni_course_planner.relation.modul.modul;

import java.util.List;

import jakarta.persistence.*;

import com.uni_course_planner.relation.modul.event_type.*;
import com.uni_course_planner.relation.user.User;

@Entity(name = "modul")
public class Modul 
{
	@EmbeddedId
	private ModulId mId;
	
	@Column(nullable = false)
	private String modulname;
	
	@Column(nullable = false)
	private int lp;
	
	@Embedded
	private Grade grade;
	
	@MapsId("userId")
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "modul", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EventType> eventTypes;
	
	protected Modul() {}

	public Modul(ModulId mId, String modulname, int lp, User user)
	{
		this.mId = mId;
		this.modulname = modulname;
		this.lp = lp;
		
		this.user = user;
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

	public Grade getGrade() 
	{
		return grade;
	}

	public void setGrade(Grade grade) 
	{
		this.grade = grade;
	}

	public User getUser() 
	{
		return user;
	}

	public void setUser(User user) 
	{
		this.user = user;
		
		if(this.mId == null)
		{
			this.mId = new ModulId();
		}
		
		this.mId.setUserId(user.getId());
	}
}
