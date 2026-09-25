package com.uni_course_planner.service.relation.modulnote;

import org.springframework.stereotype.Service;
import com.uni_course_planner.constants.modulnote.Grades;
import com.uni_course_planner.constants.views.ModalType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.dto.modulnote.ModulnoteDTOWithEdit;
import com.uni_course_planner.relation.modul.modul.*;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.repository.modul.*;
import com.uni_course_planner.service.modal.strategy.EditStrategy;

@Service
public class ModulnoteEditService implements EditStrategy
{
	private ModulRepository modulRep;

	public ModulnoteEditService(ModulRepository modulRep)
	{
		this.modulRep = modulRep;
	}

	@Override
	public ModalType getType() 
	{
		return ModalType.LEISTUNGSPUNKTE;
	}

	@Override
	public FieldDTO createDTO() 
	{
		return new ModulnoteDTOWithEdit(1L, "", 0, null, null);
	}

	@Override
	public FieldDTO createDTO(String data, LogInData user) 
	{
		ModulnoteDTOWithEdit dto = new ModulnoteDTOWithEdit();
		
		ModulId mId = new ModulId(user.getId(), Long.parseLong(data));
		Modul modul = modulRep.findById(mId).get();
		
		dto.setModul_id(mId.getModulId());
		dto.setModulname(modul.getModulname());
		dto.setLp(modul.getLp());
		
		dto.setGrade(
			    modul.getGrade() == null
			        ? null
			        : modul.getGrade().getGrade()
			);
		
		dto.setGradeNeu(dto.getGrade());
		
		return dto;
	}

	@Override
	public void edit(FieldDTO dto, LogInData user) 
	{
		ModulnoteDTOWithEdit noteDTO = (ModulnoteDTOWithEdit) dto;
		
		ModulId mId = new ModulId(user.getId(), noteDTO.getModul_id());
		Modul modul = modulRep.findById(mId).get();
		
		Grades grade = noteDTO.getGradeNeu();
		modul.setGrade(new Grade(grade));
		modulRep.save(modul);
	}
}
