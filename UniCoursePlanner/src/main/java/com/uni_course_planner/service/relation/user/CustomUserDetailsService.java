package com.uni_course_planner.service.relation.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.repository.user.LogInDataRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService
{
	@Autowired
	private LogInDataRepository logInDataRep;

	// Eingeloggter Nutzer rufen
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		Optional<LogInData> user = logInDataRep.findLogInDataByUsername(username);
		
		if(!user.isPresent())
		{
			throw new UsernameNotFoundException("Benutzer nicht gefunden");
		}
		
		return User.builder()
                .username(user.get().getUsername())
                .password(user.get().getPassword())
                .roles("USER")
                .build();
	}
}
