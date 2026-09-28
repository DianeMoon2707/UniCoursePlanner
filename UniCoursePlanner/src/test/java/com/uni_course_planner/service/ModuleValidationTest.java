package com.uni_course_planner.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.uni_course_planner.entity.module.module.*;
import com.uni_course_planner.entity.module.module.Module;
import com.uni_course_planner.repository.module.ModuleRepository;
import com.uni_course_planner.service.validation.ModuleValidation;

@ExtendWith(MockitoExtension.class)
public class ModuleValidationTest 
{
	@Mock
	private ModuleRepository moduleRep;
	
	@InjectMocks
	private ModuleValidation moduleValidation;
	
	private final String modulename = "OoP Java";
	private final Long moduleId = 1L;
	private final Long userId = 1L;
	
	//Modulename validation
	@Test 
	public void testModulenameDoesNotContainHyphen_moduleContainsHyphen_throwsException()
	{
		String modulenameHyphen = modulename + "-";
		
		assertThrows(
			IllegalArgumentException.class,	
			() -> moduleValidation.validateModulenameDoesNotContainHyphen(modulenameHyphen));
	}
	
	@Test 
	public void testModulenameDoesNotContainHyphen_moduleDoesNotContainHyphen_throwsNoException()
	{		
		assertDoesNotThrow(
			() -> moduleValidation.validateModulenameDoesNotContainHyphen(modulename));
	}
	
	//Module creation
	@Test
	public void testUserAlreadyGeneratedModul_modulExists_ThrowsException()
	{
		Module module = mock(Module.class);
		
		when(moduleRep.findModuleByModulenameAndUserId(modulename, userId)).thenReturn(Optional.of(module));
		
		assertThrows(
			IllegalArgumentException.class,
			() -> moduleValidation.validateUserAlreadyGeneratedModul(modulename, userId));
		
		verify(moduleRep).findModuleByModulenameAndUserId(modulename, userId);
	}
	
	@Test
	public void testUserAlreadyGeneratedModul_modulDoesNotExist_ThrowsNoException()
	{
		when(moduleRep.findModuleByModulenameAndUserId(modulename, userId)).thenReturn(Optional.empty());
		
		assertDoesNotThrow(
			() -> moduleValidation.validateUserAlreadyGeneratedModul(modulename, userId));
		
		verify(moduleRep).findModuleByModulenameAndUserId(modulename, userId);
	}
	
	//Modul editing
	@Test
	public void testUserChangesModulnameToAExistingOne_otherModulExists_ThrowsException()
	{
		Module module = mock(Module.class);
		ModuleId mId = mock(ModuleId.class);
		
		when(moduleRep.findModuleByModulenameAndUserId(modulename, userId)).thenReturn(Optional.of(module));
		when(module.getmId()).thenReturn(mId);
		when(mId.getModuleId()).thenReturn(2L);
		
		assertThrows(
			IllegalArgumentException.class,
			() -> moduleValidation.validateUserChangesModulnameToAExistingOne(modulename, moduleId, userId)
		);
		
		verify(moduleRep).findModuleByModulenameAndUserId(modulename, userId);
		verify(module).getmId();
		verify(mId).getModuleId();
	}
	
	@Test
	public void testUserChangesModulnameToAExistingOne_thisModulExists_ThrowsNoException()
	{
		Module module = mock(Module.class);
		ModuleId mId = mock(ModuleId.class);
		
		when(moduleRep.findModuleByModulenameAndUserId(modulename, userId)).thenReturn(Optional.of(module));
		when(module.getmId()).thenReturn(mId);
		when(mId.getModuleId()).thenReturn(moduleId);
		
		assertDoesNotThrow(
			() -> moduleValidation.validateUserChangesModulnameToAExistingOne(modulename, moduleId, userId)
		);
		
		verify(moduleRep).findModuleByModulenameAndUserId(modulename, userId);
		verify(module).getmId();
		verify(mId).getModuleId();
	}
	
	@Test
	public void testUserChangesModulnameToAExistingOne_modulDoesNotExist_ThrowsNoException()
	{
		when(moduleRep.findModuleByModulenameAndUserId(modulename, userId)).thenReturn(Optional.empty());
		
		assertDoesNotThrow(
			() -> moduleValidation.validateUserChangesModulnameToAExistingOne(modulename, moduleId, userId)
		);
		
		verify(moduleRep).findModuleByModulenameAndUserId(modulename, userId);
	}
}
