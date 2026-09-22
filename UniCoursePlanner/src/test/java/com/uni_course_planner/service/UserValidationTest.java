package com.uni_course_planner.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.relation.user.User;
import com.uni_course_planner.repository.user.*;
import com.uni_course_planner.service.validation.UserValidation;

@ExtendWith(MockitoExtension.class)
public class UserValidationTest 
{
	@Mock
	private UserRepository userRep;
	
	@Mock
	private LogInDataRepository logInDataRep;
	
	@InjectMocks
	private UserValidation userValidation;
	
	@Test
	public void testIfEmailAlreadyExists()
	{
		String email = "test@gmail.com";
		
		User user = new User(email);
		
		when(userRep.findUserByEmail(email)).thenReturn(Optional.of(user));
		
		assertThrows(
			IllegalArgumentException.class,
			() -> userValidation.validateEmailAlreadyExists(email)
		);
		
		verify(userRep).findUserByEmail(email);
	}
	
	@Test
	public void testIfEmailDoesNotExist()
	{
		String email = "test@gmail.com";
		
		when(userRep.findUserByEmail(email)).thenReturn(Optional.empty());
		
		assertDoesNotThrow(
			() -> userValidation.validateEmailAlreadyExists(email)
		);
		
		verify(userRep).findUserByEmail(email);
	}
	
	@Test
	public void testIfUsernameAlreadyExists()
	{
		String username = "Max";
		LogInData user = mock(LogInData.class);
		
		when(logInDataRep.findLogInDataByUsername(username)).thenReturn(Optional.of(user));
		
		assertThrows(
			IllegalArgumentException.class,
			() -> userValidation.validateUsernameAlreadyExists(username)
		);
		
		verify(logInDataRep).findLogInDataByUsername(username);
	}
	
	@Test
	public void testIfUsernameDoesNotExist()
	{
		String username = "Max";
		
		when(logInDataRep.findLogInDataByUsername(username)).thenReturn(Optional.empty());
		
		assertDoesNotThrow(
			() -> userValidation.validateUsernameAlreadyExists(username)
		);
		
		verify(logInDataRep).findLogInDataByUsername(username);
	}
}
