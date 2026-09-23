package com.uni_course_planner.service.validation;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.uni_course_planner.relation.modul.modul.*;
import com.uni_course_planner.repository.modul.*;

@Service
public class ModulValidation 
{
	private ModulRepository modulRep;
	
	public ModulValidation(ModulRepository modulRep) 
	{
		this.modulRep = modulRep;
	}
	
	public void validateUserAlreadyGeneratedModul(String modulname, Long userId)
	{
		Optional<Modul> modul = modulRep.findModulByModulnameAndUserId(modulname, userId);
		
		if(modul.isPresent())
		{
			throw new IllegalArgumentException("Du besitzt bereits ein Modul mit diesem Namen!");
		}
	}
	
	public void validateUserChangesModulnameToAExistingOne(String modulname, Long modulId, Long userId)
	{
		Optional<Modul> modul = modulRep.findModulByModulnameAndUserId(modulname, userId);
		
		if(modul.isPresent())
		{
			if(!modul.get().getmId().getModulId().equals(modulId))
			{
				throw new IllegalArgumentException("Du besitzt bereits ein anderes Modul mit diesem Namen!");
			}
		}
	}
}
