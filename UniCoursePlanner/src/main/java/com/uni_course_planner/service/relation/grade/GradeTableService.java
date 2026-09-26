package com.uni_course_planner.service.relation.grade;

import java.util.*;

import org.springframework.stereotype.Service;

import com.uni_course_planner.dto.grade.GradeDTO;
import com.uni_course_planner.entity.module.module.*;
import com.uni_course_planner.entity.module.module.Module;
import com.uni_course_planner.repository.module.*;

@Service
public class GradeTableService 
{
	private ModuleRepository modulRep;
	
	public GradeTableService(ModuleRepository modulRep)
	{
		this.modulRep = modulRep;
	}
	
	public List<GradeDTO> fillCreditsTable(Long user)
	{
		List<GradeDTO> tableData = new ArrayList<GradeDTO>();
		
		List<Module> module = modulRep.findAllByUserId(user);
		
		for(Module modul : module)
		{
			Long lastModulId = -1l;
			if(!tableData.isEmpty())
			{
				lastModulId = tableData.get(tableData.size()-1).getModuleId();
			}
			
			if(lastModulId != modul.getmId().getModuleId())
			{	
				Grade grade = modul.getGrade();
				GradeDTO dto = new GradeDTO(
					modul.getmId().getModuleId(),
					modul.getModulename(),
					modul.getCredits(),
					grade != null ? grade.getGrade() : null
				);
				
				tableData.add(dto);
			}		
		}
		
		return tableData;
	}
	
	public int sumByUserId(Long user)
	{
		Integer sum = modulRep.getTotalCreditsByUserId(user);
		return sum == null ? 0 : sum;
	}
}
