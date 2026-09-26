package com.uni_course_planner.service.relation.user;

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

	//Für die Authentifizierung durch Spring Security: Lade einen Benutzer anhand seines Benutzernamens
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		Optional<LogInData> user = logInDataRep.findLogInDataByUsername(username);
		
		if(!user.isPresent())
		{
			throw new UsernameNotFoundException("Benutzer nicht gefunden");
		}
		
		//Datenbankeintrag in ein für Spring Security verwendbares UserDetails-Objekt umwandeln
		return User.builder()
                .username(user.get().getUsername())
                .password(user.get().getPassword())
                .roles("USER")
                .build();
	}
}
