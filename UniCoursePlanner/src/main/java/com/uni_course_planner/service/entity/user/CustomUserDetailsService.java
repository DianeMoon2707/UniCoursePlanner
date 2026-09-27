package com.uni_course_planner.service.entity.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.uni_course_planner.entity.user.LogInData;
import com.uni_course_planner.repository.user.LogInDataRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService
{
	@Autowired
	private LogInDataRepository logInDataRep;

	//Loads a user by username for authentication by Spring Security
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		Optional<LogInData> user = logInDataRep.findByUsername(username);
		
		if(!user.isPresent())
		{
			throw new UsernameNotFoundException("Benutzer nicht gefunden");
		}
		
		//Convert the database entity into a UserDetails object that can be used by Spring Security
		return User.builder()
                .username(user.get().getUsername())
                .password(user.get().getPassword())
                .roles("USER")
                .build();
	}
}
