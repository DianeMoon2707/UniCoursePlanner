package com.uni_course_planner.service.relation.user;

import org.springframework.stereotype.Service;

import com.uni_course_planner.relation.User;
import com.uni_course_planner.repository.UserRepository;

@Service
public class UserService
{
	private UserRepository userRep;
	
	public UserService(UserRepository userRep)
	{
		this.userRep = userRep;
	}
	
	public User registerUser(String email)
	{
		User user = new User(email);
		return userRep.save(user);
	}

}
