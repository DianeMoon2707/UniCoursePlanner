package com.uni_course_planner.service.relation.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uni_course_planner.relation.user.*;
import com.uni_course_planner.repository.user.*;

@Service
public class UserService
{
	private UserRepository userRep;
	private LogInDataRepository logInDataRep;
	
	private PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRep, LogInDataRepository logInDataRep, PasswordEncoder passwordEncoder)
	{
		this.userRep = userRep;
		this.logInDataRep = logInDataRep;
		
		this.passwordEncoder = passwordEncoder;
	}
	
	// User speichern	
	@Transactional
	public void registerUser(String email, String username, String password)
	{
		User user = userRep.save(new User(email));
		String hashedPassword = passwordEncoder.encode(password);
		logInDataRep.save(new LogInData(user, username, hashedPassword));
	}
	
	//User laden
	public LogInData getUserByUsername(String username)
	{
		return logInDataRep.findLogInDataByUsername(username).get();
	}
}
