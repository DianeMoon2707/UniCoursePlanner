package com.uni_course_planner.constants.popup;

import com.uni_course_planner.service.field.FieldService;
import com.uni_course_planner.service.field.ModulFieldService;

public enum InsertType
{
	MODUL("Neues Modul anlegen", ModulFieldService.class);
	
	private final String headline;
	private final Class<? extends FieldService> serviceClass;
	
	InsertType(String headline, Class<? extends FieldService> serviceClass)
	{
		this.headline = headline;
		this.serviceClass = serviceClass;
	}
	
	public String getHeadline()
	{
		return headline;
	}

	public Class<? extends FieldService> getServiceClass() 
	{
		return serviceClass;
	}
}
