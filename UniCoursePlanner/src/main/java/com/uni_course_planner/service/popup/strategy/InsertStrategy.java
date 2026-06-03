package com.uni_course_planner.service.popup.strategy;

import com.uni_course_planner.constants.popup.InsertType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.relation.user.LogInData;

public interface InsertStrategy
{
	public InsertType getType();
	FieldDTO createDTO();
	public void save(FieldDTO dto, LogInData currentUser);
}
