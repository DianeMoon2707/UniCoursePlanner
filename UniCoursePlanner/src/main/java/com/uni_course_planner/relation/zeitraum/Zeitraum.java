package com.uni_course_planner.relation.zeitraum;

import java.time.LocalDate;

import com.uni_course_planner.relation.user.User;

import jakarta.persistence.*;

@Entity(name = "zeitraum")
public class Zeitraum 
{
	@Id
	private Long id;
	
	@Column(name = "valid_from", nullable = false)
	private LocalDate validFrom;
	
	@Column(name = "valid_to", nullable = false)
	private LocalDate validTo;
	
	@MapsId
	@OneToOne(optional = false)
    @JoinColumn(name = "id")
    private User user;
	
	protected Zeitraum() {}
	
	public Zeitraum(User user, LocalDate validFrom, LocalDate validTo)
	{
		this.user = user;
		this.validFrom = validFrom;
		this.validTo = validTo;
	}

	public Long getId()
	{
		return id;
	}
	
	public LocalDate getValidFrom()
	{
		return validFrom;
	}

	public LocalDate getValidTo()
	{
		return validTo;
	}

	public void setValidTo(LocalDate validTo)
	{
		this.validTo = validTo;
	}

	public User getUser() 
	{
		return user;
	}
}
