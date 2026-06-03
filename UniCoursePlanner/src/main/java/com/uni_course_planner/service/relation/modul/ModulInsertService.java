package com.uni_course_planner.service.relation.modul;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.uni_course_planner.constants.fields.mask.*;
import com.uni_course_planner.constants.modul.EventTypes;
import com.uni_course_planner.constants.popup.InsertType;
import com.uni_course_planner.relation.modul.event_type.*;
import com.uni_course_planner.relation.modul.modul.*;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.repository.modul.*;
import com.uni_course_planner.service.popup.strategy.InsertStrategy;

@Service
public class ModulInsertService implements InsertStrategy 
{
	private ModulRepository modulRep;
	private EventTypeRepository eventTypeRep;
	
	public ModulInsertService(ModulRepository modulRep, EventTypeRepository eventTypeRep)
	{
		this.modulRep = modulRep;
		this.eventTypeRep = eventTypeRep;
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
		
		Modul modul = new Modul(
				new ModulId(user.getId(), mId),
				modulname,
				lp
			);
		
		modulRep.save(modul);
		this.saveEvents(insertMap, modul);
	}
	
	private void saveEvents(Map<String, String> insertMap, Modul modul)
	{
		for(EventTypes type : EventTypes.values())
		{
			if(insertMap.containsKey(EventTypes.getLowerCase(type)))
			{
				EventTypeId eId = new EventTypeId(modul.getmId(), type);
				EventType et = new EventType(eId, modul);
				eventTypeRep.save(et);
			}
		}
	}
}
