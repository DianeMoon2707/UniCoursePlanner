package com.uni_course_planner.relation.modul.modulnote;

import com.uni_course_planner.constants.modulnote.Grades;
import com.uni_course_planner.relation.modul.modul.*;

import jakarta.persistence.*;

@Entity(name = "modulnote")
public class Modulnote
{
	@EmbeddedId
    private ModulId mId;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Grades note;
	
	@MapsId
	@OneToOne
	private Modul modul;
	
	protected Modulnote() {}

	public Modulnote(ModulId mId, Grades note, Modul modul) 
	{
		this.mId = mId;
		this.note = note;
		
		this.modul = modul;
	}

	public ModulId getmId()
	{
		return mId;
	}

	public void setmId(ModulId mId) 
	{
		this.mId = mId;
	}

	public Grades getNote()
	{
		return note;
	}

	public void setNote(Grades note) 
	{
		this.note = note;
	}

	public Modul getModul()
	{
		return modul;
	}

	public void setModul(Modul modul) 
	{
		this.modul = modul;
	}
}
