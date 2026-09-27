package com.uni_course_planner.service.entity.timetable;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uni_course_planner.constants.module.EventTypes;
import com.uni_course_planner.constants.timetable.Timeslot;
import com.uni_course_planner.constants.timetable.Weekday;
import com.uni_course_planner.constants.views.ModalType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.dto.timetable.modal.LectureDeleteDTO;
import com.uni_course_planner.dto.timetable.modal.LectureEditDTO;
import com.uni_course_planner.entity.module.event_type.EventType;
import com.uni_course_planner.entity.timetable.Timetable;
import com.uni_course_planner.entity.user.LogInData;
import com.uni_course_planner.repository.module.EventTypeRepository;
import com.uni_course_planner.repository.timetable.TimetableRepository;
import com.uni_course_planner.service.modal.strategy.EditStrategy;

//Edits the room of a lecture
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
		return ModalType.TIMETABLE;
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
			dto.setModulename(textData[0]);			
			dto.setRoom(textData[1]);
			dto.setRoomNew(textData[1]);
			
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
		
		String[]moduleParts = lectureDTO.getModulename().split(" - ");
		EventType event = eventTypeRep.getByModulenameAndType(
			moduleParts[0],
			EventTypes.fromDescriptionToEnum(moduleParts[1]),
			user.getId()
		);
		
		Timetable entry = timetableRep.findByTimeDayRoomAndEvent(
			lectureDTO.getTime(),
			lectureDTO.getWeekday(),
			lectureDTO.getRoom(), 
			event);
		
		entry.setRoom(lectureDTO.getRoomNew());
		
		timetableRep.save(entry);
	}
}
