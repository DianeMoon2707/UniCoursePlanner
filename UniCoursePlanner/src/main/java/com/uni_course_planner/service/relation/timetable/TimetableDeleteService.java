package com.uni_course_planner.service.relation.timetable;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uni_course_planner.constants.timetable.*;
import com.uni_course_planner.constants.views.ModalType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.dto.timetable.modal.LectureDeleteDTO;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.service.modal.strategy.DeleteStrategy;

@Service
public class TimetableDeleteService implements DeleteStrategy
{
	@Override
	public ModalType getType()
	{
		return ModalType.STUNDENPLAN;
	}

	@Override
	public FieldDTO createDTO() 
	{
		return new LectureDeleteDTO("", Weekday.MO, Timeslot.SLOT_08_10, "");
	}

	@Override
	public FieldDTO createDTO(String data)
	{
		LectureDeleteDTO dto = new LectureDeleteDTO();
		
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			List<String> dataList = mapper.readValue(data, new TypeReference<List<String>>() {});
			
			String[] textData = this.convertEntryData(dataList.get(0));
			dto.setModulname(textData[0]);			
			dto.setRoom(textData[1]);
			
			dto.setWeekday(Weekday.valueOf(dataList.get(1))); 
			dto.setTime(Timeslot.valueOf(dataList.get(2)));
			
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
		
		return dto;
	}

	@Override
	public void delete(FieldDTO dto, LogInData user)
	{
		// TODO Auto-generated method stub
		
	}
	
	private String[] convertEntryData(String data)
	{
		String[]array = new String[4];
		
		String[] textParts = data.split("\n");
		
		String modulname = textParts[0] + " - " + textParts[1].substring(0, textParts[1].length()-1);
		String room = textParts[2].substring(textParts[2].indexOf(":") + 2);
		
		array[0] = modulname;
		array[1] = room;
		
		return array;
	}
}
