package com.uni_course_planner.constants.popup;

public enum InsertType 
{
	MODUL("fragments/popup/modul-popup");
	
	private String fragmentFile;
	
	InsertType(String fragmentFile)
	{
		this.fragmentFile = fragmentFile;
	}

	public String getFragmentFile() 
	{
		return fragmentFile;
	}
}
