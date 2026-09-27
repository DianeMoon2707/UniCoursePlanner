package com.uni_course_planner.service.email.text;

//Base class for email texts containing the message and subject
public abstract class EmailText 
{
	protected String text;
	protected String topic;

	public String getText() 
	{
		return text;
	}

	public String getTopic() 
	{
		return topic;
	}
}
