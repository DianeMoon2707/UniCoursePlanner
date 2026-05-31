package com.uni_course_planner.service.field;

import com.uni_course_planner.constants.fields.FieldType;

public class FieldDTO 
{
	private String label;
	private String input;
	private FieldType type;
	
	public FieldDTO(String label, String input, FieldType type) 
	{
		this.label = label;
		this.input = input;
		this.type = type;
	}

	public String getLabel()
	{
		return label;
	}
	
	public String getInput() 
	{
		return input;
	}

	public FieldType getType() 
	{
		return type;
	}
}
