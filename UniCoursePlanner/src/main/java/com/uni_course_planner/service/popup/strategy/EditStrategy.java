package com.uni_course_planner.service.popup.strategy;

import com.uni_course_planner.constants.views.PopupType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.relation.user.LogInData;

public interface EditStrategy 
{
	public PopupType getType();
	FieldDTO createDTO();
	public void edit(FieldDTO dto, LogInData currentUser);
}
