package com.uni_course_planner.constants.views;

public enum PopupType 
{
	MODUL("fragments/popup/modul-popup");
	
	private String fragmentFile;
	
	PopupType(String fragmentFile)
	{
		this.fragmentFile = fragmentFile;
	}

	public String getFragmentFile() 
	{
		return fragmentFile;
	}
}
