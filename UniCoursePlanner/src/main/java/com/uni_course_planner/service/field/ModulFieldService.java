package com.uni_course_planner.service.field;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uni_course_planner.constants.fields.FieldType;
import com.uni_course_planner.constants.fields.mask.ModulField;

@Service
public class ModulFieldService extends FieldService 
{
	@Override
	public List<FieldDTO> createInsertMask()
	{
		List<FieldDTO> insertMask = List.of(
			new FieldDTO("Modulname:", ModulField.MODULNAME.getHtmlName(), FieldType.TEXT),
			new FieldDTO("Leistungspunkte:", ModulField.LP.getHtmlName(), FieldType.NUMBER),
			new FieldDTO("Vorlesung:", ModulField.VL.getHtmlName(), FieldType.CHECKBOX),
			new FieldDTO("Übung:", ModulField.UB.getHtmlName(), FieldType.CHECKBOX),
			new FieldDTO("Seminar:", ModulField.SEM.getHtmlName(), FieldType.CHECKBOX)
		);
		
		return insertMask;
	}
}
