package com.uni_course_planner.service.popup.strategy;

import com.uni_course_planner.constants.views.PopupType;
import com.uni_course_planner.dto.FieldDTO;

public interface DeleteStrategy 
{
	public PopupType getType();
	FieldDTO createDTO(String rowData);
	public void delete(FieldDTO dto);
	public FieldDTO createDTO();
}
