package com.uni_course_planner.service.modal.strategy;

import com.uni_course_planner.constants.views.ModalType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.relation.user.LogInData;

public interface DeleteStrategy 
{
	public ModalType getType();
	public FieldDTO createDTO();
	public FieldDTO createDTO(String rowData);
	public void delete(FieldDTO dto, LogInData user);
}
