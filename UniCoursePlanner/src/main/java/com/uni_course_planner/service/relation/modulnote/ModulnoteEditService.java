package com.uni_course_planner.service.relation.modulnote;

import java.util.*;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	public FieldDTO createDTO(String data) 
	{
		ModulnoteDTOWithEdit dto = new ModulnoteDTOWithEdit();
		
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			List<String> dataList = mapper.readValue(data, new TypeReference<List<String>>() {});
			
			dto.setModul_id(Long.parseLong(dataList.get(0)));
			dto.setModulname(dataList.get(1));
			dto.setLp(Integer.parseInt(dataList.get(2)));

			if(!dataList.get(3).isEmpty() && !dataList.get(3).equals("-"))
			{
				dto.setGrade(
					Grades.fromNumericToEnum(
						Double.parseDouble(dataList.get(3))
					)
				);
			}
			
			dto.setGradeNeu(dto.getGrade());
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
		
		return dto;
	}

	@Override
	public void edit(FieldDTO dto, LogInData currentUser) 
	{
		ModulnoteDTOWithEdit noteDTO = (ModulnoteDTOWithEdit) dto;
		
		ModulId mId = new ModulId(currentUser.getId(), noteDTO.getModul_id());
		Modul modul = modulRep.findById(mId).get();
		
		Grades grade = noteDTO.getGradeNeu();
		modul.setGrade(new Grade(grade));
		modulRep.save(modul);
	}
}
