package com.uni_course_planner.entity.module.module;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.uni_course_planner.entity.user.User;

import jakarta.persistence.*;

@Entity(name = "modul")
public class Module 
{
	@EmbeddedId
	private ModuleId mId;
	
	@Column(nullable = false)
	private String modulname;
	
	@Column(nullable = false)
	private int lp;
	
	@Embedded
	private Grade grade;
	
	@MapsId("userId")
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	protected Module() {}

	public Module(ModuleId mId, String modulname, int lp, User user)
	{
		this.mId = mId;
		this.modulname = modulname;
		this.lp = lp;
		
		this.user = user;
	}

	public ModuleId getmId() 
	{
		return mId;
	}

	public void setmId(ModuleId mId) 
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
			this.mId = new ModuleId();
		}
		
		this.mId.setUserId(user.getId());
	}
}
