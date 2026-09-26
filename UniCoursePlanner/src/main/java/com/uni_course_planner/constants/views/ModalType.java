package com.uni_course_planner.constants.views;

//Enum for identifying the type of modal and selecting the corresponding fragment and service
public enum ModalType 
{
	MODULE("fragments/modal/module-modal"),
	TIMETABLE("fragments/modal/timetable-modal"),
	CALENDAR("fragments/modal/calendar-modal"),
	CREDITS("fragments/modal/credits-modal");
	
	private String fragmentFile;
	
	ModalType(String fragmentFile)
	{
		this.fragmentFile = fragmentFile;
	}

	public String getFragmentFile() 
	{
		return fragmentFile;
	}
}
