package com.uni_course_planner.service.popup.strategy;

import java.util.Map;

import com.uni_course_planner.constants.popup.InsertType;
import com.uni_course_planner.relation.user.LogInData;

public interface InsertStrategy 
{
	public InsertType getType();
	public void save(Map<String, String> insertMap, LogInData currentUser);
}
