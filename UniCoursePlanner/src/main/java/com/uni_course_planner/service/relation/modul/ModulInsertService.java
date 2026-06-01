package com.uni_course_planner.service.relation.modul;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.uni_course_planner.constants.fields.mask.*;
import com.uni_course_planner.constants.popup.InsertType;
import com.uni_course_planner.relation.modul.modul.*;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.repository.modul.ModulRepository;
import com.uni_course_planner.service.popup.strategy.InsertStrategy;

@Service
public class ModulInsertService implements InsertStrategy 
{
	private ModulRepository modulRep;
	
	public ModulInsertService(ModulRepository modulRep)
	{
		this.modulRep = modulRep;
	}
	
	@Override
	public InsertType getType()
	{
		return InsertType.MODUL;
	}

	@Override
	public void save(Map<String, String> insertMap, LogInData user) 
	{
		String modulname = insertMap.get(ModulField.MODULNAME.getHtmlName());
		int lp = Integer.parseInt(insertMap.get(ModulField.LP.getHtmlName()));
		
		Long mId = modulRep.getMaxModulId() + 1;
		
		modulRep.save(new Modul(
				new ModulId(user.getId(), mId),
				modulname,
				lp
			));
	}
	
}
