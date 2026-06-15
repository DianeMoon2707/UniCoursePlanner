package com.uni_course_planner.service.modal.strategy;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.uni_course_planner.constants.views.ModalType;

@Service
public class ModalServiceFactory
{
	private final Map<ModalType, InsertStrategy> insertMap;
	private final Map<ModalType, EditStrategy> editMap;
	private final Map<ModalType, DeleteStrategy> deleteMap;
	
	public ModalServiceFactory(List<InsertStrategy> insertServiceList, 
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
	
	
	public InsertStrategy getInsertService(ModalType type)
	{
		return insertMap.get(type);
	}
	
	public EditStrategy getEditService(ModalType type)
	{
		return editMap.get(type);
	}
	
	public DeleteStrategy getDeleteService(ModalType type)
	{
		return deleteMap.get(type);
	}
}
