package com.uni_course_planner.service.email.text;

public class PasswortVergessenText extends EmailText
{
	public PasswortVergessenText(String username, String password, String code) 
	{
		this.text = "Hallo " + username + ", \n\n"
				+ "dein neues Passwort lautet: " + password	+ ".\n"
				+ "Gebe bitte diesen Code ein, um das neue Passwort zu aktivieren: " + code + ".\n\n"
				+ "Dein Uni-Course-Planner-Team";
		
		this.topic = "Passwort zurücksetzen";
	}
}
