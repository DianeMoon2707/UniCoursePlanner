package com.uni_course_planner.service.validation;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.uni_course_planner.entity.module.module.Module;
import com.uni_course_planner.repository.module.*;

@Service
public class ModuleValidation 
{
	private ModuleRepository modulRep;
	
	public ModuleValidation(ModuleRepository modulRep) 
	{
		this.modulRep = modulRep;
	}
	
	public void validateUserAlreadyGeneratedModul(String modulname, Long userId)
	{
		Optional<Module> modul = modulRep.findModulByModulnameAndUserId(modulname, userId);
		
		if(modul.isPresent())
		{
			throw new IllegalArgumentException("Du besitzt bereits ein Modul mit diesem Namen!");
		}
	}
	
	public void validateUserChangesModulnameToAExistingOne(String modulname, Long modulId, Long userId)
	{
		Optional<Module> modul = modulRep.findModulByModulnameAndUserId(modulname, userId);
		
		if(modul.isPresent())
		{
			if(!modul.get().getmId().getModulId().equals(modulId))
			{
				throw new IllegalArgumentException("Du besitzt bereits ein anderes Modul mit diesem Namen!");
			}
		}
	}
}
