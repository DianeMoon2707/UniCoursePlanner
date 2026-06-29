package com.uni_course_planner.controller.core;

import java.time.LocalDate;
import java.util.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uni_course_planner.constants.modul.EventTypes;
import com.uni_course_planner.constants.timetable.Timeslot;
import com.uni_course_planner.constants.views.PageAddress;
import com.uni_course_planner.dto.timetable.*;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.service.relation.user.UserService;
import com.uni_course_planner.service.relation.zeitraum.ZeitraumService;

@Controller
public class StundenplanController 
{
	private static final String STANDARD_FRAGMENT = "standard";
	private static final String TIMETABLE_FRAGMENT = "timetable";
	
	private UserService userService;
	private ZeitraumService zeitraumService;
	
	public StundenplanController(UserService userService, ZeitraumService zeitraumService) 
	{
		this.userService = userService;
		this.zeitraumService = zeitraumService;
	}

	@GetMapping(PageAddress.STUNDENPLAN_PAGE_ADDRESS)
	public String showStundenplanPage(Model model)
	{
		this.renderStundenplanPage(model);
		return PageAddress.STUNDENPLAN_PAGE_ADDRESS;
	}
	
	@PostMapping("/saveDate")
	public String saveZeitraum(@RequestParam String validFrom,
			@RequestParam String validTo, Model model)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LogInData user = userService.getUserByUsername(auth.getName());
		
		zeitraumService.saveZeitraum(
			LocalDate.parse(validFrom), 
			LocalDate.parse(validTo), 
			user
		);
		
		return "redirect:" + PageAddress.STUNDENPLAN_PAGE_ADDRESS;
	}
	
	private void renderStundenplanPage(Model model)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LogInData user = userService.getUserByUsername(auth.getName());
		
		if(zeitraumService.actuellZeitraumExistsByUser(user))
		{
			model.addAttribute("fragmentName", TIMETABLE_FRAGMENT);
			model.addAttribute("timetable", createDummyTimetable());
		}
		else
		{
			model.addAttribute("fragmentName", STANDARD_FRAGMENT);
			
			LocalDate today = LocalDate.now();
			model.addAttribute("today", today);
			model.addAttribute("later", today.plusMonths(3));
		}
	}
	
	private List<TimetableRowDTO> createDummyTimetable()
	{
	    List<TimetableRowDTO> timetable = new ArrayList<>();

	    // ===== Slot 08-10 =====
	    List<LectureDTO> lectureMo1 = List.of(new LectureDTO("Mathe", EventTypes.UB, "A101"));
	    List<LectureDTO> lectureDi1 = List.of(new LectureDTO("Deutsch", EventTypes.VL, "B202"));
	    List<LectureDTO> lectureMi1 = List.of();
	    List<LectureDTO> lectureDo1 = List.of(new LectureDTO("Informatik", EventTypes.SEM, "C303"));
	    List<LectureDTO> lectureFr1 = List.of();
	    
	    List<List<LectureDTO>> lectures = new ArrayList<>();
	    lectures.add(lectureMo1);
	    lectures.add(lectureDi1);
	    lectures.add(lectureMi1);
	    lectures.add(lectureDo1);
	    lectures.add(lectureFr1);
	    
	    TimetableRowDTO row1 = new TimetableRowDTO(Timeslot.SLOT_08_10, lectures);

	    timetable.add(row1);

	    // ===== Slot 10-12 =====	    
	    List<LectureDTO> lectureMo2 = List.of(
		        new LectureDTO("Physik", EventTypes.VL, "A101"),
		        new LectureDTO("Tutorium", EventTypes.UB, "A101")
		    );
	    
	    List<LectureDTO> lectureDi2 = List.of();
	    List<LectureDTO> lectureMi2 = List.of(new LectureDTO("Mathe", EventTypes.VL, "B202"));
	    List<LectureDTO> lectureDo2 = List.of();
	    List<LectureDTO> lectureFr2 = List.of();
	    
	    List<List<LectureDTO>> lectures2 = new ArrayList<>();
	    lectures2.add(lectureMo2);
	    lectures2.add(lectureDi2);
	    lectures2.add(lectureMi2);
	    lectures2.add(lectureDo2);
	    lectures2.add(lectureFr2);
	    
	    TimetableRowDTO row2 = new TimetableRowDTO(Timeslot.SLOT_10_12, lectures2);

	    timetable.add(row2);

	    return timetable;
	}
}
