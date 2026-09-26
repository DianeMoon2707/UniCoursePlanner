package com.uni_course_planner.entity.module.module;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.uni_course_planner.entity.user.User;

import jakarta.persistence.*;

/**
 * Represents a module assigned to a user.
 * Uses a composite key consisting of the user ID and module ID.
 */
@Entity(name = "module")
public class Module 
{
	@EmbeddedId
	private ModuleId mId;
	
	@Column(nullable = false)
	private String modulename;
	
	@Column(nullable = false)
	private int credits;
	
	@Embedded
	private Grade grade;
	
	@MapsId("userId")
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	protected Module() {}

	public Module(ModuleId mId, String modulename, int credits, User user)
	{
		this.mId = mId;
		this.modulename = modulename;
		this.credits = credits;
		
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

	public String getModulename()
	{
		return modulename;
	}

	public void setModulename(String modulename) 
	{
		this.modulename = modulename;
	}

	public int getCredits() 
	{
		return credits;
	}

	public void setCredits(int credits) 
	{
		this.credits = credits;
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
