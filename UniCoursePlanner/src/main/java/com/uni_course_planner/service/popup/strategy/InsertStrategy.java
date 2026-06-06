package com.uni_course_planner.service.popup.strategy;

import com.uni_course_planner.constants.views.PopupType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.relation.user.LogInData;

public interface InsertStrategy
{
	public PopupType getType();
	public FieldDTO createDTO();
	public void save(FieldDTO dto, LogInData currentUser);
}
