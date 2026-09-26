package com.uni_course_planner.service.relation.timetable;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uni_course_planner.constants.module.EventTypes;
import com.uni_course_planner.constants.timetable.*;
import com.uni_course_planner.constants.views.ModalType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.dto.timetable.modal.LectureInsertDTO;
import com.uni_course_planner.entity.module.event_type.EventType;
import com.uni_course_planner.entity.timetable.Timetable;
import com.uni_course_planner.entity.user.LogInData;
import com.uni_course_planner.repository.module.EventTypeRepository;
import com.uni_course_planner.repository.timetable.TimetableRepository;
import com.uni_course_planner.service.modal.strategy.InsertStrategy;
import com.uni_course_planner.service.validation.TimetableValidation;

@Service
public class TimetableInsertService implements InsertStrategy
{
	private TimetableRepository timetableRep;
	private EventTypeRepository eventTypeRep;
	
	private TimetableValidation validation;

	public TimetableInsertService(TimetableRepository timetableRep, EventTypeRepository eventTypeRep,
			TimetableValidation validation) 
	{
		this.timetableRep = timetableRep;
		this.eventTypeRep = eventTypeRep;
		
		this.validation = validation;
	}

	@Override
	public ModalType getType() 
	{
		return ModalType.TIMETABLE;
	}

	@Override
	public FieldDTO createDTO(LogInData user) 
	{
		List<String> modulnamen = eventTypeRep.findAllByUserId(user.getId()).stream()
			.map(et -> et.getModul().getModulname() + " - " + et.geteId().getType().getDescription())
			.toList();
		return new LectureInsertDTO(modulnamen);
	}

	@Override
	public void save(FieldDTO dto, LogInData user)
	{
		LectureInsertDTO lectureDTO = (LectureInsertDTO) dto;
		
		String[]modulOption = lectureDTO.getSelectedModule().split(" - ");
		EventType event = eventTypeRep.getByModulnameAndType(
				modulOption[0], 
				EventTypes.fromDescriptionToEnum(modulOption[1]), 
				user.getId()
		);
		
		Weekday day = lectureDTO.getDay();
		Timeslot time = lectureDTO.getTime();
		
		validation.validateUserAlreadyGeneratedEntryForCell(event, day, time);
		
		Timetable entry = new Timetable(
			time,
			day,
			lectureDTO.getRoom(),
			event
		);
		
		timetableRep.save(entry);
	}
}
