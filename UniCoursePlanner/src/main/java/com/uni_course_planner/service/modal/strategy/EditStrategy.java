package com.uni_course_planner.service.modal.strategy;

import com.uni_course_planner.constants.views.PopupType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.relation.user.LogInData;

public interface EditStrategy 
{
	public PopupType getType();
	public FieldDTO createDTO();
	public FieldDTO createDTO(String rowData);
	public void edit(FieldDTO dto, LogInData currentUser);
}
