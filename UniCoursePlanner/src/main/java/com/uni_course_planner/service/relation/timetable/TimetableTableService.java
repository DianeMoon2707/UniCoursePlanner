package com.uni_course_planner.service.relation.timetable;

import java.util.*;

import org.springframework.stereotype.Service;

import com.uni_course_planner.constants.timetable.Timeslot;
import com.uni_course_planner.constants.timetable.Weekday;
import com.uni_course_planner.dto.timetable.*;
import com.uni_course_planner.entity.timetable.Timetable;
import com.uni_course_planner.entity.user.LogInData;
import com.uni_course_planner.repository.timetable.TimetableRepository;

@Service
public class TimetableTableService 
{
	private TimetableRepository timetableRep;
	
	public TimetableTableService(TimetableRepository timetableRep)
	{
		this.timetableRep = timetableRep;
	}
	
	public List<TimetableRowDTO> fillTimetable(LogInData user)
	{
		List<TimetableRowDTO> timetable = this.createBlankTimetable();
		List<Timetable> dataSets = timetableRep.findAllByUserId(user.getId());
		
		int rowNumber, colNumber = 0;		
		TimetableRowDTO cell;
		LectureDTO lecture;
		
		for(Timetable dataSet : dataSets)
		{
			rowNumber = dataSet.getTime().getOrder();
			colNumber = dataSet.getDay().getOrder();
			
			lecture = new LectureDTO(
				dataSet.getEvent().getModule().getModulename(),
				dataSet.getEvent().geteId().getType(),
				dataSet.getRoom()
			);	
			
			cell = timetable.get(rowNumber);
			cell.getLectures().get(colNumber).add(lecture);
		}

	    return timetable;
	}
	
	private List<TimetableRowDTO> createBlankTimetable()
	{
		List<TimetableRowDTO> timetable = new ArrayList<>();
		
		for(int t = 0; t < Timeslot.values().length; t++)
		{
			List<List<LectureDTO>> lectures = new ArrayList<>();
			for(int w = 0; w < Weekday.values().length; w++)
			{
				lectures.add(new ArrayList<>());
			}
			
			timetable.add(new TimetableRowDTO(Timeslot.getIndex(t), lectures));
		}
		
		return timetable;
	}
}
