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
	
	private List<List<TimetableCellDTO>> createDummyTimetable()
	{
	    List<List<TimetableCellDTO>> timetable = new ArrayList<>();

	    // ===== Slot 08-10 =====
	    List<TimetableCellDTO> row1 = new ArrayList<>();

	    row1.add(new TimetableCellDTO(Timeslot.SLOT_08_10, List.of(
	        new LectureDTO("Mathe", EventTypes.UB, "A101")
	    )));

	    row1.add(new TimetableCellDTO(Timeslot.SLOT_08_10, List.of(
	        new LectureDTO("Deutsch", EventTypes.VL, "B202")
	    )));

	    row1.add(new TimetableCellDTO(Timeslot.SLOT_08_10, List.of())); // Mittwoch leer
	    row1.add(new TimetableCellDTO(Timeslot.SLOT_08_10, List.of(
	        new LectureDTO("Informatik", EventTypes.SEM, "C303")
	    )));
	    row1.add(new TimetableCellDTO(Timeslot.SLOT_08_10, List.of()));

	    timetable.add(row1);

	    // ===== Slot 10-12 =====
	    List<TimetableCellDTO> row2 = new ArrayList<>();

	    row2.add(new TimetableCellDTO(Timeslot.SLOT_10_12, List.of(
	        new LectureDTO("Physik", EventTypes.VL, "A101"),
	        new LectureDTO("Tutorium", EventTypes.UB, "A101")
	    )));

	    row2.add(new TimetableCellDTO(Timeslot.SLOT_10_12, List.of()));
	    row2.add(new TimetableCellDTO(Timeslot.SLOT_10_12, List.of(
	        new LectureDTO("Mathe", EventTypes.VL, "B202")
	    )));
	    row2.add(new TimetableCellDTO(Timeslot.SLOT_10_12, List.of()));
	    row2.add(new TimetableCellDTO(Timeslot.SLOT_10_12, List.of()));

	    timetable.add(row2);

	    return timetable;
	}
}
