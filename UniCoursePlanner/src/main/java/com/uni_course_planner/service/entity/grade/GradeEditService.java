package com.uni_course_planner.service.entity.grade;

import org.springframework.stereotype.Service;

import com.uni_course_planner.constants.grade.Grades;
import com.uni_course_planner.constants.views.ModalType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.dto.grade.GradeDTOWithEdit;
import com.uni_course_planner.entity.module.module.*;
import com.uni_course_planner.entity.module.module.Module;
import com.uni_course_planner.entity.user.LogInData;
import com.uni_course_planner.repository.module.*;
import com.uni_course_planner.service.modal.strategy.EditStrategy;

//Edits module grades
@Service
public class GradeEditService implements EditStrategy
{
	private ModuleRepository moduleRep;

	public GradeEditService(ModuleRepository moduleRep)
	{
		this.moduleRep = moduleRep;
	}

	@Override
	public ModalType getType() 
	{
		return ModalType.CREDITS;
	}

	@Override
	public FieldDTO createDTO() 
	{
		return new GradeDTOWithEdit(1L, "", 0, null, null);
	}

	@Override
	public FieldDTO createDTO(String data, LogInData user) 
	{
		GradeDTOWithEdit dto = new GradeDTOWithEdit();
		
		ModuleId mId = new ModuleId(user.getId(), Long.parseLong(data));
		Module module = moduleRep.findById(mId).get();
		
		dto.setModuleId(mId.getModuleId());
		dto.setModulename(module.getModulename());
		dto.setCredits(module.getCredits());
		
		dto.setGrade(
			    module.getGrade() == null
			        ? null
			        : module.getGrade().getGrade()
			);
		
		dto.setGradeNew(dto.getGrade());
		
		return dto;
	}

	@Override
	public void edit(FieldDTO dto, LogInData user) 
	{
		GradeDTOWithEdit noteDTO = (GradeDTOWithEdit) dto;
		
		ModuleId mId = new ModuleId(user.getId(), noteDTO.getModuleId());
		Module module = moduleRep.findById(mId).get();
		
		Grades grade = noteDTO.getGradeNew();
		module.setGrade(new Grade(grade));
		moduleRep.save(module);
	}
}
