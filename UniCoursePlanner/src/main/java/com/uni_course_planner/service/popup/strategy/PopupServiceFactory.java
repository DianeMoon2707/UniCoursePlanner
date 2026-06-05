package com.uni_course_planner.service.popup.strategy;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.uni_course_planner.constants.views.PopupType;

@Service
public class PopupServiceFactory
{
	private final Map<PopupType, InsertStrategy> insertMap;
	private final Map<PopupType, EditStrategy> editMap;
	private final Map<PopupType, DeleteStrategy> deleteMap;
	
	public PopupServiceFactory(List<InsertStrategy> insertServiceList, 
			List<EditStrategy> editServiceList, 
			List<DeleteStrategy> deleteServiceList)
	{
		insertMap = insertServiceList.stream().collect(
				Collectors.toMap(
				InsertStrategy::getType, 
				Function.identity()));
		
		this.editMap = editServiceList.stream().collect(
				Collectors.toMap(
				EditStrategy::getType, 
				Function.identity()));
		
		this.deleteMap = deleteServiceList.stream().collect(
				Collectors.toMap(
				DeleteStrategy::getType, 
				Function.identity()));
	}
	
	
	public InsertStrategy getInsertService(PopupType type)
	{
		return insertMap.get(type);
	}
	
	public EditStrategy getEditService(PopupType type)
	{
		return editMap.get(type);
	}
	
	public DeleteStrategy getDeleteService(PopupType type)
	{
		return deleteMap.get(type);
	}
}
