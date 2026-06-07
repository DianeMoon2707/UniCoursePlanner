package com.uni_course_planner.service.relation.modul;

import java.util.*;

import org.springframework.stereotype.Service;

import com.uni_course_planner.constants.modul.EventTypes;
import com.uni_course_planner.dto.modul.ModulDTOWithID;
import com.uni_course_planner.relation.modul.event_type.EventType;
import com.uni_course_planner.relation.modul.modul.Modul;
import com.uni_course_planner.repository.modul.*;

@Service
public class ModulTableService 
{
	private ModulRepository modulRep;
	private EventTypeRepository eventTypeRep;
	
	public ModulTableService(ModulRepository modulRep, EventTypeRepository eventTypeRep) 
	{
		this.modulRep = modulRep;
		this.eventTypeRep = eventTypeRep;
	}
	
	public List<ModulDTOWithID> fillModulTable(Long user)
	{
		List<ModulDTOWithID> tableData = new ArrayList<ModulDTOWithID>();
		
		List<Modul> module = modulRep.findAllByUserId(user);
		
		for(Modul modul : module)
		{
			List<EventType> eventTypesOfModul = eventTypeRep.findAllByModul(modul);
			Set<EventTypes> eventSet =  new HashSet<EventTypes>();
			
			for(EventType e : eventTypesOfModul)
			{
				eventSet.add(e.geteId().getType());
			}
			
			tableData.add(
				new ModulDTOWithID(
					modul.getModulname(),
					modul.getLp(),
					eventSet,
					modul.getmId().getModulId()
				));
		}
		
		return tableData;
	}
}
