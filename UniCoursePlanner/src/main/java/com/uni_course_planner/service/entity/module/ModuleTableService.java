package com.uni_course_planner.service.entity.module;

import java.util.*;

import org.springframework.stereotype.Service;

import com.uni_course_planner.constants.module.EventTypes;
import com.uni_course_planner.dto.module.ModuleDTOWithID;
import com.uni_course_planner.entity.module.event_type.EventType;
import com.uni_course_planner.entity.module.module.Module;
import com.uni_course_planner.repository.module.*;

//Provides module data for the module table
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
		
		List<Module> modules = moduleRep.findAllByUserId(user);
		
		for(Module module : modules)
		{
			List<EventType> eventTypesOfModule = eventTypeRep.findAllByModule(module);
			Set<EventTypes> eventSet =  new HashSet<EventTypes>();
			
			for(EventType e : eventTypesOfModule)
			{
				eventSet.add(e.geteId().getType());
			}
			
			tableData.add(
				new ModuleDTOWithID(
					module.getModulename(),
					module.getCredits(),
					eventSet,
					module.getmId().getModuleId()
				));
		}
		
		return tableData;
	}
}
