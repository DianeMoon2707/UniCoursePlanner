package com.uni_course_planner.service.relation.timetable;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uni_course_planner.constants.modul.EventTypes;
import com.uni_course_planner.constants.views.ModalType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.dto.timetable.modal.LectureInsertDTO;
import com.uni_course_planner.relation.timetable.Timetable;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.repository.modul.EventTypeRepository;
import com.uni_course_planner.repository.timetable.TimetableRepository;
import com.uni_course_planner.service.modal.strategy.InsertStrategy;

@Service
public class TimetableInsertService implements InsertStrategy
{
	private TimetableRepository timetableRep;
	private EventTypeRepository eventTypeRep;
	
	public TimetableInsertService(TimetableRepository timetableRep, EventTypeRepository eventTypeRep)
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
		String[]modulOption = lectureDTO.getSelectedModul().split(" - ");
		
		Timetable entry = new Timetable(
			lectureDTO.getTime(),
			lectureDTO.getDay(),
			lectureDTO.getRoom(),
			eventTypeRep.getByModulnameAndType(
					modulOption[0], 
					EventTypes.fromDescriptionToEnum(modulOption[1]), 
					user.getId()
			)
		);
		
		timetableRep.save(entry);
	}
}
