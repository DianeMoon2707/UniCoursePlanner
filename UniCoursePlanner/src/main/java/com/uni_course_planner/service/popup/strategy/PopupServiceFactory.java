package com.uni_course_planner.service.popup.strategy;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.uni_course_planner.constants.popup.InsertType;

@Service
public class PopupServiceFactory
{
	private final Map<InsertType, InsertStrategy> map;
	
	public PopupServiceFactory(List<InsertStrategy> insertServiceList)
	{
		map = insertServiceList.stream().collect(
				Collectors.toMap(
				InsertStrategy::getType, 
				Function.identity()));
	}
	
	
	public InsertStrategy get(InsertType type)
	{
		return map.get(type);
	}
}
