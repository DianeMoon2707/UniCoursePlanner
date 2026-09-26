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
	private ModuleRepository moduleRep;
	private EventTypeRepository eventTypeRep;
	
	public ModuleTableService(ModuleRepository moduleRep, EventTypeRepository eventTypeRep) 
	{
		this.moduleRep = moduleRep;
		this.eventTypeRep = eventTypeRep;
	}
	
	public List<ModuleDTOWithID> fillModuleTable(Long user)
	{
		List<ModuleDTOWithID> tableData = new ArrayList<ModuleDTOWithID>();
		
		List<Module> module = moduleRep.findAllByUserId(user);
		
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
					modul.getModulename(),
					modul.getCredits(),
					eventSet,
					modul.getmId().getModuleId()
				));
		}
		
		return tableData;
	}
}
