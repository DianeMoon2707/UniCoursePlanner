package com.uni_course_planner.service.relation.module;

import java.util.*;

import org.springframework.stereotype.Service;

import com.uni_course_planner.constants.module.EventTypes;
import com.uni_course_planner.dto.module.ModuleDTOWithID;
import com.uni_course_planner.entity.module.event_type.EventType;
import com.uni_course_planner.entity.module.module.Module;
import com.uni_course_planner.repository.module.*;

@Service
public class ModuleTableService 
{
	private ModuleRepository modulRep;
	private EventTypeRepository eventTypeRep;
	
	public ModuleTableService(ModuleRepository modulRep, EventTypeRepository eventTypeRep) 
	{
		this.modulRep = modulRep;
		this.eventTypeRep = eventTypeRep;
	}
	
	public List<ModuleDTOWithID> fillModulTable(Long user)
	{
		List<ModuleDTOWithID> tableData = new ArrayList<ModuleDTOWithID>();
		
		List<Module> module = modulRep.findAllByUserId(user);
		
		for(Module modul : module)
		{
			List<EventType> eventTypesOfModul = eventTypeRep.findAllByModul(modul);
			Set<EventTypes> eventSet =  new HashSet<EventTypes>();
			
			for(EventType e : eventTypesOfModul)
			{
				eventSet.add(e.geteId().getType());
			}
			
			tableData.add(
				new ModuleDTOWithID(
					modul.getModulname(),
					modul.getLp(),
					eventSet,
					modul.getmId().getModulId()
				));
		}
		
		return tableData;
	}
}
