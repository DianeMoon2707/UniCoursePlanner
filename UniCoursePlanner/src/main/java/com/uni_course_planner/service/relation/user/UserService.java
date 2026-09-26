package com.uni_course_planner.service.relation.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uni_course_planner.entity.user.*;
import com.uni_course_planner.repository.user.*;
import com.uni_course_planner.service.validation.UserValidation;

@Service
public class UserService
{
	private UserRepository userRep;
	private LogInDataRepository logInDataRep;
	
	private PasswordEncoder passwordEncoder;
	
	private UserValidation validation;
	
	public UserService(UserRepository userRep, LogInDataRepository logInDataRep, PasswordEncoder passwordEncoder,
			UserValidation validation) 
	{
		this.userRep = userRep;
		this.logInDataRep = logInDataRep;
		
		this.passwordEncoder = passwordEncoder;
		
		this.validation = validation;
	}

	// User speichern	
	public void registerUser(String email, String username, String password)
	{
		validation.validateEmailAlreadyExists(email);
		validation.validateUsernameAlreadyExists(username);
		
		User user = userRep.save(new User(email));
		String hashedPassword = passwordEncoder.encode(password);
		logInDataRep.save(new LogInData(user, username, hashedPassword));
	}
	
	public void changeUserData(LogInData user, String username, String email)
	{
		validation.validateOtherUserHasEmail(user.getId(), email);
		validation.validatOtherUserHasUsername(user.getId(), username);

		User userForEmail = userRep.findById(user.getId()).get();
		
		user.setUsername(username);
		userForEmail.setEmail(email);
		
		userRep.save(userForEmail);
		logInDataRep.save(user);
	}
	
	public void changePassword(String username, String password) 
	{
		LogInData user = logInDataRep.findLogInDataByUsername(username).get();
		
		String hashedPassword = passwordEncoder.encode(password);
		user.setPassword(hashedPassword);
		
		logInDataRep.save(user);
	}
	
	//User laden
	public LogInData getUserByUsername(String username)
	{
		return logInDataRep.findLogInDataByUsername(username).get();
	}
	
	public String getUsernameFromAuthenticationField(String authentication)
	{
		if(authentication.contains("@"))
		{
			return logInDataRep.getUsernameByEmail(authentication);
		}
		else
		{
			return authentication;
		}
	}
	
	public String getEmailFromAuthenticationField(String authentication)
	{
		if(!authentication.contains("@"))
		{
			LogInData user = logInDataRep.findLogInDataByUsername(authentication).get();
			return user.getUser().getEmail();
		}
		else
		{
			return authentication;
		}
	}
	
	public String getEmailFromUser(Long id)
	{
		return userRep.getEmail(id);
	}
	
	public boolean userExistsByUsername(String username)
	{
		return logInDataRep.findLogInDataByUsername(username).isPresent();
	}
}
