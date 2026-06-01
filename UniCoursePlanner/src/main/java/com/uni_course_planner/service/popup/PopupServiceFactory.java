package com.uni_course_planner.service.popup;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.uni_course_planner.constants.popup.InsertType;
import com.uni_course_planner.service.popup.strategy.InsertStrategy;

@Service
public class PopupServiceFactory
{
	private final Map<InsertType, InsertStrategy> insertService;
	
	public PopupServiceFactory(List<InsertStrategy> insertServiceList)
	{
		insertService = insertServiceList.stream().collect(
				Collectors.toMap(
				InsertStrategy::getType, 
				Function.identity()));
	}
	
	
	public InsertStrategy getInsertService(InsertType type)
	{
		return insertService.get(type);
	}
}
