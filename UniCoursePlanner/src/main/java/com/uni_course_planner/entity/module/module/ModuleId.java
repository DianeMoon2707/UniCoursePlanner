package com.uni_course_planner.entity.module.module;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;

//Composite key for a module consisting of the user ID and module ID.
@Embeddable
public class ModuleId implements Serializable
{
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "module_id")
	private Long moduleId;

	public ModuleId() {}
	
	public ModuleId(Long userId, Long moduleId)
	{
		this.userId = userId;
		this.moduleId = moduleId;
	}

	public Long getModuleId() 
	{
		return moduleId;
	}

	public void setModulId(Long moduleId) 
	{
		this.moduleId = moduleId;
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
		return Objects.hash(moduleId, userId);
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
		ModuleId other = (ModuleId) obj;
		return Objects.equals(moduleId, other.moduleId) && Objects.equals(userId, other.userId);
	}
}
