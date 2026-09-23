package com.uni_course_planner.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.uni_course_planner.relation.modul.modul.*;
import com.uni_course_planner.repository.modul.ModulRepository;
import com.uni_course_planner.service.validation.ModulValidation;

@ExtendWith(MockitoExtension.class)
public class ModulValidationTest 
{
	@Mock
	private ModulRepository modulRep;
	
	@InjectMocks
	private ModulValidation modulValidation;
	
	private final String modulname = "OoP Java";
	private final Long modulId = 1L;
	private final Long userId = 1L;
	
	//Modul-Erstellung
	@Test
	public void testUserAlreadyGeneratedModul_modulExists_ThrowsException()
	{
		Modul modul = mock(Modul.class);
		
		when(modulRep.findModulByModulnameAndUserId(modulname, userId)).thenReturn(Optional.of(modul));
		
		assertThrows(
			IllegalArgumentException.class,
			() -> modulValidation.validateUserAlreadyGeneratedModul(modulname, userId));
		
		verify(modulRep).findModulByModulnameAndUserId(modulname, userId);
	}
	
	@Test
	public void testUserAlreadyGeneratedModul_modulDoesNotExist_ThrowsNoException()
	{
		when(modulRep.findModulByModulnameAndUserId(modulname, userId)).thenReturn(Optional.empty());
		
		assertDoesNotThrow(
			() -> modulValidation.validateUserAlreadyGeneratedModul(modulname, userId));
		
		verify(modulRep).findModulByModulnameAndUserId(modulname, userId);
	}
	
	//Modul-Änderung
	@Test
	public void testUserChangesModulnameToAExistingOne_otherModulExists_ThrowsException()
	{
		Modul modul = mock(Modul.class);
		ModulId mId = mock(ModulId.class);
		
		when(modulRep.findModulByModulnameAndUserId(modulname, userId)).thenReturn(Optional.of(modul));
		when(modul.getmId()).thenReturn(mId);
		when(mId.getModulId()).thenReturn(2L);
		
		assertThrows(
			IllegalArgumentException.class,
			() -> modulValidation.validateUserChangesModulnameToAExistingOne(modulname, modulId, userId)
		);
		
		verify(modulRep).findModulByModulnameAndUserId(modulname, userId);
		verify(modul).getmId();
		verify(mId).getModulId();
	}
	
	@Test
	public void testUserChangesModulnameToAExistingOne_thisModulExists_ThrowsNoException()
	{
		Modul modul = mock(Modul.class);
		ModulId mId = mock(ModulId.class);
		
		when(modulRep.findModulByModulnameAndUserId(modulname, userId)).thenReturn(Optional.of(modul));
		when(modul.getmId()).thenReturn(mId);
		when(mId.getModulId()).thenReturn(modulId);
		
		assertDoesNotThrow(
			() -> modulValidation.validateUserChangesModulnameToAExistingOne(modulname, modulId, userId)
		);
		
		verify(modulRep).findModulByModulnameAndUserId(modulname, userId);
		verify(modul).getmId();
		verify(mId).getModulId();
	}
	
	@Test
	public void testUserChangesModulnameToAExistingOne_modulDoesNotExist_ThrowsNoException()
	{
		when(modulRep.findModulByModulnameAndUserId(modulname, userId)).thenReturn(Optional.empty());
		
		assertDoesNotThrow(
			() -> modulValidation.validateUserChangesModulnameToAExistingOne(modulname, modulId, userId)
		);
		
		verify(modulRep).findModulByModulnameAndUserId(modulname, userId);
	}
}
