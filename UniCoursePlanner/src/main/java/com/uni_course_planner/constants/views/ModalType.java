package com.uni_course_planner.constants.views;

public enum ModalType 
{
	MODUL("fragments/modal/modul-modal"),
	STUNDENPLAN("fragments/modal/stundenplan-modal"),
	LEISTUNGSPUNKTE("fragments/modal/leistungspunkte-modal");
	
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
