package com.uni_course_planner.service.relation.timetable;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uni_course_planner.constants.module.EventTypes;
import com.uni_course_planner.constants.timetable.*;
import com.uni_course_planner.constants.views.ModalType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.dto.timetable.modal.LectureDeleteDTO;
import com.uni_course_planner.entity.module.event_type.EventType;
import com.uni_course_planner.entity.timetable.Timetable;
import com.uni_course_planner.entity.user.*;
import com.uni_course_planner.repository.module.EventTypeRepository;
import com.uni_course_planner.repository.timetable.TimetableRepository;
import com.uni_course_planner.service.modal.strategy.DeleteStrategy;

@Service
public class TimetableDeleteService implements DeleteStrategy
{
	private TimetableRepository timetableRep;
	private EventTypeRepository eventTypeRep;
	
	public TimetableDeleteService(TimetableRepository timetableRep, EventTypeRepository eventTypeRep) 
	{
		this.timetableRep = timetableRep;
		this.eventTypeRep = eventTypeRep;
	}

	@Override
	public ModalType getType()
	{
		return ModalType.TIMETABLE;
	}

	@Override
	public FieldDTO createDTO() 
	{
		return new LectureDeleteDTO("", Weekday.MO, Timeslot.SLOT_08_10, "");
	}

	@Override
	public FieldDTO createDTO(String data, LogInData user)
	{
		LectureDeleteDTO dto = new LectureDeleteDTO();
		
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			List<String> dataList = mapper.readValue(data, new TypeReference<List<String>>() {});
			
			String[] textData = LectureDeleteDTO.convertEntryData(dataList.get(0));
			dto.setModulename(textData[0]);			
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
		LectureDeleteDTO lectureDTO = (LectureDeleteDTO) dto;
		
		String[]modulParts = lectureDTO.getModulename().split(" - ");
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
}
