package com.uni_course_planner.entity.user;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;

@Entity(name="log_in_data")
public class LogInData 
{
	@Id
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@MapsId
	@OneToOne(optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id")
    private User user;
	
	protected LogInData() {}
	
	public LogInData(User user, String username, String password)
	{
		this.user = user;
		this.username = username;
		this.password = password;
	}

	public Long getId() 
	{
		return id;
	}

	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public User getUser() 
	{
		return user;
	}

	public void setUser(User user) 
	{
		this.user = user;
	}
}
