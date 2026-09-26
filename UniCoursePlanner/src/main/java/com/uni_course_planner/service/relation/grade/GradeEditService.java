package com.uni_course_planner.service.relation.grade;

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

@Service
public class GradeEditService implements EditStrategy
{
	private ModuleRepository modulRep;

	public GradeEditService(ModuleRepository modulRep)
	{
		this.modulRep = modulRep;
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
		Module modul = modulRep.findById(mId).get();
		
		dto.setModuleId(mId.getModulId());
		dto.setModulename(modul.getModulname());
		dto.setCredits(modul.getLp());
		
		dto.setGrade(
			    modul.getGrade() == null
			        ? null
			        : modul.getGrade().getGrade()
			);
		
		dto.setGradeNew(dto.getGrade());
		
		return dto;
	}

	@Override
	public void edit(FieldDTO dto, LogInData user) 
	{
		GradeDTOWithEdit noteDTO = (GradeDTOWithEdit) dto;
		
		ModuleId mId = new ModuleId(user.getId(), noteDTO.getModuleId());
		Module modul = modulRep.findById(mId).get();
		
		Grades grade = noteDTO.getGradeNew();
		modul.setGrade(new Grade(grade));
		modulRep.save(modul);
	}
}
