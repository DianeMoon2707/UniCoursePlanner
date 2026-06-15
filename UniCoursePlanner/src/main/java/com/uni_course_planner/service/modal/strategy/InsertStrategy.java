package com.uni_course_planner.service.modal.strategy;

import com.uni_course_planner.constants.views.ModalType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.relation.user.LogInData;

public interface InsertStrategy
{
	public ModalType getType();
	public FieldDTO createDTO();
	public void save(FieldDTO dto, LogInData currentUser);
}
