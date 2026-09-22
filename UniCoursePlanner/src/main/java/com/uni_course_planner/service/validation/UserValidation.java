package com.uni_course_planner.service.validation;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.uni_course_planner.relation.user.*;
import com.uni_course_planner.repository.user.*;

@Service
public class UserValidation 
{
	private UserRepository userRep;
	private LogInDataRepository logInDataRep;
	
	public UserValidation(UserRepository userRep, LogInDataRepository logInDataRep) 
	{
		this.userRep = userRep;
		this.logInDataRep = logInDataRep;
	}
	
	public void validateEmailAlreadyExists(String email)
	{
		Optional<User> user = userRep.findUserByEmail(email);
		
		if(user.isPresent())
		{
			throw new IllegalArgumentException("Bitte wähle eine andere Emailadresse");
		}
	}
	
	public void validateUsernameAlreadyExists(String username)
	{
		Optional<LogInData> user = logInDataRep.findLogInDataByUsername(username);
		
		if(user.isPresent())
		{
			throw new IllegalArgumentException("Dieser Benutzername existiert bereits!");
		}
	}
	
	public void validateOtherUserHasEmail(Long id, String email)
	{
		Optional<User> user = userRep.findUserByEmail(email);

		if(user.isPresent())
		{
			if(!user.get().getId().equals(id))
			{
				throw new IllegalArgumentException("Bitte wähle eine andere Emailadresse");
			}
		}
	}
	
	public void validatOtherUserHasUsername(Long id, String username)
	{
		Optional<LogInData> user = logInDataRep.findLogInDataByUsername(username);
		
		if(user.isPresent())
		{
			if(!user.get().getId().equals(id))
			{
				throw new IllegalArgumentException("Dieser Benutzername existiert bereits!");
			}
		}
	}
}
