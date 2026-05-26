package com.uni_course_planner.service.relation.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uni_course_planner.relation.user.*;
import com.uni_course_planner.repository.user.*;

@Service
public class UserService
{
	private UserRepository userRep;
	private LogInDataRepository logInDataRep;
	
	public UserService(UserRepository userRep, LogInDataRepository logInDataRep)
	{
		this.userRep = userRep;
		this.logInDataRep = logInDataRep;
	}
	
	// User speichern
	private User registerUserData(String email)
	{
		User user = new User(email);		
		return userRep.save(user);
	}

	private LogInData saveLogInData(User user, String username, String password)
	{
		LogInData logInData = new LogInData(user, username, password);
		return logInDataRep.save(logInData);
	}
	
	@Transactional
	public void registerUser(String email, String username, String password)
	{
		User user = this.registerUserData(email);
		this.saveLogInData(user, username, password);
	}
	
	// User finden
	public boolean checkLogInData(String username, String password)
	{
		return logInDataRep.existsByLogInData(username, password) == true;
			
	}
}
