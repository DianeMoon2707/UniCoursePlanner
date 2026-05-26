package com.uni_course_planner.relation.user;

import jakarta.persistence.*;

@Entity(name="users")
public class User 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String email;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private LogInData logInData;
	
	protected User() {}
	
	public User(String email) 
	{
		this.email = email;
	}

	public Long getId() 
	{
		return id;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public LogInData getLogInData() 
	{
		return logInData;
	}

	public void setLogInData(LogInData logInData) 
	{
		this.logInData = logInData;
	}
}
