package com.uni_course_planner.service.relation.modulnote;

import java.util.*;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uni_course_planner.constants.modulnote.Grades;
import com.uni_course_planner.constants.views.PopupType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.dto.modulnote.ModulnoteDTOWithEdit;
import com.uni_course_planner.relation.modul.modul.*;
import com.uni_course_planner.relation.modul.modulnote.Modulnote;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.repository.modul.*;
import com.uni_course_planner.service.popup.strategy.EditStrategy;

@Service
public class ModulnoteEditService implements EditStrategy
{
	private ModulnoteRepository modulnoteRep;
	private ModulRepository modulRep;

	public ModulnoteEditService(ModulnoteRepository modulnoteRep, ModulRepository modulRep)
	{
		this.modulnoteRep = modulnoteRep;
		this.modulRep = modulRep;
	}

	@Override
	public PopupType getType() 
	{
		return PopupType.LEISTUNGSPUNKTE;
	}

	@Override
	public FieldDTO createDTO() 
	{
		return new ModulnoteDTOWithEdit(1L, "", 0, null, null);
	}

	@Override
	public FieldDTO createDTO(String rowData) 
	{
		ModulnoteDTOWithEdit dto = new ModulnoteDTOWithEdit();
		
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			List<String> data = mapper.readValue(rowData, new TypeReference<List<String>>() {});
			
			dto.setModul_id(Long.parseLong(data.get(0)));
			dto.setModulname(data.get(1));
			dto.setLp(Integer.parseInt(data.get(2)));

			if(!data.get(3).isEmpty() && !data.get(3).equals("-"))
			{
				dto.setGrade(
					Grades.fromNumericToEnum(
						Double.parseDouble(data.get(3))
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
		Optional<Modulnote> modulnote = modulnoteRep.findById(mId);
		
		Grades grade = noteDTO.getGradeNeu(); 
		
		if(modulnote.isPresent())
		{
			if(grade == null)
			{
				modulnoteRep.deleteById(mId);
			}
			else
			{
				modulnote.get().setNote(grade);
				modulnoteRep.save(modulnote.get());
			}
		}
		else
		{
			Modul modul = modulRep.findById(mId).get();
			Modulnote note = new Modulnote(mId, grade, modul);
			
			modulnoteRep.save(note);
		}
	}
}
