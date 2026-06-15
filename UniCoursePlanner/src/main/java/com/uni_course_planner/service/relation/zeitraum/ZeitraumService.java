package com.uni_course_planner.service.relation.zeitraum;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.uni_course_planner.relation.user.*;
import com.uni_course_planner.relation.zeitraum.Zeitraum;
import com.uni_course_planner.repository.user.UserRepository;
import com.uni_course_planner.repository.zeitraum.ZeitraumRepository;

@Service
public class ZeitraumService 
{
	private ZeitraumRepository zeitraumRep;
	private UserRepository userRep;

	public ZeitraumService(ZeitraumRepository zeitraumRep, UserRepository userRep)
	{
		this.zeitraumRep = zeitraumRep;
		this.userRep = userRep;
	}
	
	public boolean actuellZeitraumExistsByUser(LogInData user)
	{
		if(!zeitraumRep.existsById(user.getId()))
		{
			return false;
		}
		else
		{
			Zeitraum zeitraum = zeitraumRep.findById(user.getId()).get();
			if(zeitraum.getValidTo().isBefore(LocalDate.now()))
			{
				return false;
			}
			else
			{
				return true;
			}
		}
	}
	
	public Zeitraum saveZeitraum(LocalDate validFrom, LocalDate validTo, LogInData logInData)
	{
		User user = userRep.findById(logInData.getId()).get();
		Zeitraum zeitraum = new Zeitraum(user, validFrom, validTo);
		
		return zeitraumRep.save(zeitraum);
	}
}
