package com.uni_course_planner.service.field;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uni_course_planner.constants.fields.FieldType;

@Service
public class ModulFieldService extends FieldService 
{
	@Override
	public List<FieldDTO> createInsertMask()
	{
		List<FieldDTO> insertMask = List.of(
			new FieldDTO("Modulname:", "modulname", FieldType.TEXT),
			new FieldDTO("Leistungspunkte:", "lp", FieldType.NUMBER)
		);
		
		return insertMask;
	}
}
