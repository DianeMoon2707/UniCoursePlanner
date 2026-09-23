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
	
	String email = "test@gmail.com";
	String username = "Max";
	Long id = 1L;
	
	//Register-Tests
	//Email
	@Test
	public void testEmailAlreadyExists_emailExists_throwsException()
	{		
		User user = mock(User.class);
		
		when(userRep.findUserByEmail(email)).thenReturn(Optional.of(user));
		
		assertThrows(
			IllegalArgumentException.class,
			() -> userValidation.validateEmailAlreadyExists(email)
		);
		
		verify(userRep).findUserByEmail(email);
	}
	
	@Test
	public void testEmailAlreadyExists_emailDoesNotExist_throwsNoException()
	{		
		when(userRep.findUserByEmail(email)).thenReturn(Optional.empty());
		
		assertDoesNotThrow(
			() -> userValidation.validateEmailAlreadyExists(email)
		);
		
		verify(userRep).findUserByEmail(email);
	}
	
	//Username
	@Test
	public void testUsernameAlreadyExists_usernameExists_throwsException()
	{
		LogInData user = mock(LogInData.class);
		
		when(logInDataRep.findLogInDataByUsername(username)).thenReturn(Optional.of(user));
		
		assertThrows(
			IllegalArgumentException.class,
			() -> userValidation.validateUsernameAlreadyExists(username)
		);
		
		verify(logInDataRep).findLogInDataByUsername(username);
	}
	
	@Test
	public void testUsernameAlreadyExists_usernameDoesNotExist_throwsNoException()
	{
		when(logInDataRep.findLogInDataByUsername(username)).thenReturn(Optional.empty());
		
		assertDoesNotThrow(
			() -> userValidation.validateUsernameAlreadyExists(username)
		);
		
		verify(logInDataRep).findLogInDataByUsername(username);
	}
	
	//Update-Tests
	//Email
	@Test
	public void testOtherUserHasEmail_emailExistsByOtherUser_throwsException()
	{
		User user = mock(User.class);
		
		when(userRep.findUserByEmail(email)).thenReturn(Optional.of(user));
		when(user.getId()).thenReturn(2L);
		
		assertThrows(
			IllegalArgumentException.class,	
			() -> userValidation.validateOtherUserHasEmail(id, email)
		);
		
		verify(userRep).findUserByEmail(email);
		verify(user).getId();
	}
	
	@Test
	public void testOtherUserHasEmail_emailExistsByThisUser_throwsNoException()
	{		
		User user = mock(User.class);
		
		when(userRep.findUserByEmail(email)).thenReturn(Optional.of(user));
		when(user.getId()).thenReturn(id);
		
		assertDoesNotThrow(
			() -> userValidation.validateOtherUserHasEmail(id, email)
		);
		
		verify(userRep).findUserByEmail(email);
		verify(user).getId();
	}
	
	@Test
	public void testOtherUserHasEmail_emailDoesNotExist_throwsNoException()
	{		
		when(userRep.findUserByEmail(email)).thenReturn(Optional.empty());
		assertDoesNotThrow(
			() -> userValidation.validateOtherUserHasEmail(id, email)
		);
		
		verify(userRep).findUserByEmail(email);
	}
	
	//Username
	@Test
	public void testOtherUserHasUsername_usernameExistsByOtherUser_throwsException()
	{		
		LogInData user = mock(LogInData.class);
		
		when(logInDataRep.findLogInDataByUsername(username)).thenReturn(Optional.of(user));
		when(user.getId()).thenReturn(2L);
		
		assertThrows(
			IllegalArgumentException.class,	
			() -> userValidation.validatOtherUserHasUsername(id, username)
		);
		
		verify(logInDataRep).findLogInDataByUsername(username);
		verify(user).getId();
	}
	
	@Test
	public void testOtherUserHasUsername_usernameExistsByThisUser_throwsNoException()
	{
		LogInData user = mock(LogInData.class);
		
		when(logInDataRep.findLogInDataByUsername(username)).thenReturn(Optional.of(user));
		when(user.getId()).thenReturn(id);
		
		assertDoesNotThrow(
			() -> userValidation.validatOtherUserHasUsername(id, username)
		);
		
		verify(logInDataRep).findLogInDataByUsername(username);
		verify(user).getId();
	}
	
	@Test
	public void testOtherUserHasUsername_usernameDoesNotExist_throwsNoException()
	{
		when(logInDataRep.findLogInDataByUsername(username)).thenReturn(Optional.empty());
		assertDoesNotThrow(
			() -> userValidation.validatOtherUserHasUsername(id, username)
		);
		
		verify(logInDataRep).findLogInDataByUsername(username);
	}
}
