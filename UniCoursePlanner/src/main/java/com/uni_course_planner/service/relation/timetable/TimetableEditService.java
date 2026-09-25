package com.uni_course_planner.service.relation.timetable;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uni_course_planner.constants.modul.EventTypes;
import com.uni_course_planner.constants.timetable.Timeslot;
import com.uni_course_planner.constants.timetable.Weekday;
import com.uni_course_planner.constants.views.ModalType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.dto.timetable.modal.LectureDeleteDTO;
import com.uni_course_planner.dto.timetable.modal.LectureEditDTO;
import com.uni_course_planner.relation.modul.event_type.EventType;
import com.uni_course_planner.relation.timetable.Timetable;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.repository.modul.EventTypeRepository;
import com.uni_course_planner.repository.timetable.TimetableRepository;
import com.uni_course_planner.service.modal.strategy.EditStrategy;


@Service
public class TimetableEditService implements EditStrategy
{
	private TimetableRepository timetableRep;
	private EventTypeRepository eventTypeRep;
	
	public TimetableEditService(TimetableRepository timetableRep, EventTypeRepository eventTypeRep) 
	{
		this.timetableRep = timetableRep;
		this.eventTypeRep = eventTypeRep;
	}
	
	@Override
	public ModalType getType()
	{
		return ModalType.STUNDENPLAN;
	}

	@Override
	public FieldDTO createDTO() 
	{
		return new LectureEditDTO("", Weekday.MO, Timeslot.SLOT_08_10, "", "");
	}

	@Override
	public FieldDTO createDTO(String data, LogInData user)
	{
		LectureEditDTO dto = new LectureEditDTO();
		
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			List<String> dataList = mapper.readValue(data, new TypeReference<List<String>>() {});
			
			String[] textData = LectureDeleteDTO.convertEntryData(dataList.get(0));
			dto.setModulname(textData[0]);			
			dto.setRoom(textData[1]);
			dto.setRoomNeu(textData[1]);
			
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
	public void edit(FieldDTO dto, LogInData user)
	{
		LectureEditDTO lectureDTO = (LectureEditDTO) dto;
		
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
		
		entry.setRoom(lectureDTO.getRoomNeu());
		
		timetableRep.save(entry);
	}
}
