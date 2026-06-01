package com.uni_course_planner.constants.fields;

public enum FieldType 
{
	TEXT("text"),
	NUMBER("number"),
	CHECKBOX("checkbox");
	
	private String htmlType;
	
	FieldType(String htmlType)
	{
		this.htmlType = htmlType;
	}

	public String getHtmlType()
	{
		return htmlType;
	}

	public void setHtmlType(String htmlType) 
	{
		this.htmlType = htmlType;
	}
}
