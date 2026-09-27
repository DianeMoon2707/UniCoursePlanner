package com.uni_course_planner.service.security;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

//Generates verification codes for password changes
@Service
public class CodeGenerator 
{
	private final SecureRandom random = new SecureRandom();
	
	public String generateCode()
	{
		return String.format("%06d", random.nextInt(1_000_000));
	}
}
