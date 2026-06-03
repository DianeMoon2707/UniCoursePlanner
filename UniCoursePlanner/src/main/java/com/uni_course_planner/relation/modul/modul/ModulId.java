package com.uni_course_planner.relation.modul.modul;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;

@Embeddable
public class ModulId implements Serializable
{
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "modul_id")
	private Long modulId;

	public ModulId() {}
	
	public ModulId(Long userId, Long modulId)
	{
		this.userId = userId;
		this.modulId = modulId;
	}

	public Long getModulId() 
	{
		return modulId;
	}

	public void setModulId(Long modulId) 
	{
		this.modulId = modulId;
	}

	public Long getUserId() 
	{
		return userId;
	}

	public void setUserId(Long userId) 
	{
		this.userId = userId;
	}

	@Override
	public int hashCode() 
	{
		return Objects.hash(modulId, userId);
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
		ModulId other = (ModulId) obj;
		return Objects.equals(modulId, other.modulId) && Objects.equals(userId, other.userId);
	}
}
