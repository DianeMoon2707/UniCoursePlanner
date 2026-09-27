package com.uni_course_planner.service.entity.grade;

import java.util.*;

import org.springframework.stereotype.Service;

import com.uni_course_planner.dto.grade.GradeDTO;
import com.uni_course_planner.entity.module.module.*;
import com.uni_course_planner.entity.module.module.Module;
import com.uni_course_planner.repository.module.*;

//Provides grade data for the credits table
@Service
public class GradeTableService 
{
	private ModuleRepository moduleRep;
	
	public GradeTableService(ModuleRepository moduleRep)
	{
		this.moduleRep = moduleRep;
	}
	
	public List<GradeDTO> fillCreditsTable(Long user)
	{
		List<GradeDTO> tableData = new ArrayList<GradeDTO>();
		
		List<Module> modules = moduleRep.findAllByUserId(user);
		
		for(Module module : modules)
		{
			Long lastModuleId = -1l;
			if(!tableData.isEmpty())
			{
				lastModuleId = tableData.get(tableData.size()-1).getModuleId();
			}
			
			if(lastModuleId != module.getmId().getModuleId())
			{	
				Grade grade = module.getGrade();
				GradeDTO dto = new GradeDTO(
					module.getmId().getModuleId(),
					module.getModulename(),
					module.getCredits(),
					grade != null ? grade.getGrade() : null
				);
				
				tableData.add(dto);
			}		
		}
		
		return tableData;
	}
	
	public int getTotalCreditsByUserId(Long user)
	{
		Integer sum = moduleRep.getTotalCreditsByUserId(user);
		return sum == null ? 0 : sum;
	}
}
