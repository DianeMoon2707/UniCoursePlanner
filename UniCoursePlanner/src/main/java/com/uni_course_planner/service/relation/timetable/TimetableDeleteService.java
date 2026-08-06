package com.uni_course_planner.service.relation.timetable;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uni_course_planner.constants.modul.EventTypes;
import com.uni_course_planner.constants.timetable.*;
import com.uni_course_planner.constants.views.ModalType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.dto.timetable.modal.LectureDeleteDTO;
import com.uni_course_planner.relation.modul.event_type.EventType;
import com.uni_course_planner.relation.timetable.Timetable;
import com.uni_course_planner.relation.user.*;
import com.uni_course_planner.repository.modul.EventTypeRepository;
import com.uni_course_planner.repository.timetable.TimetableRepository;
import com.uni_course_planner.repository.user.UserRepository;
import com.uni_course_planner.service.modal.strategy.DeleteStrategy;

@Service
public class TimetableDeleteService implements DeleteStrategy
{
	private TimetableRepository timetableRep;
	private EventTypeRepository eventTypeRep;
	private UserRepository userRep;	
	
	public TimetableDeleteService(TimetableRepository timetableRep, EventTypeRepository eventTypeRep, UserRepository userRep) 
	{
		this.timetableRep = timetableRep;
		this.eventTypeRep = eventTypeRep;
		this.userRep = userRep;
	}

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
	public void delete(FieldDTO dto, LogInData currentUser)
	{
		LectureDeleteDTO lectureDTO = (LectureDeleteDTO) dto;
		
		User user = userRep.findById(currentUser.getId()).get();
		
		String[]modulParts = lectureDTO.getModulname().split(" - ");
		EventType modul = eventTypeRep.getByModulnameAndType(
			modulParts[0],
			EventTypes.fromDescriptionToEnum(modulParts[1]),
			user.getId()
		);
		
		Timetable entry = timetableRep.findByAttributs(
			lectureDTO.getTime(),
			lectureDTO.getWeekday(),
			lectureDTO.getRoom(), 
			modul);
		
		timetableRep.delete(entry);
	}
	
	private String[] convertEntryData(String data)
	{
		String[]array = new String[4];
		
		String[] textParts = data.split("\n");
		
		String modulname = textParts[0] + " - " + textParts[1].substring(0, textParts[1].length()-1);
		String room = textParts[2].substring(textParts[2].indexOf(":") + 2).trim();
		
		array[0] = modulname;
		array[1] = room;
		
		return array;
	}
}
