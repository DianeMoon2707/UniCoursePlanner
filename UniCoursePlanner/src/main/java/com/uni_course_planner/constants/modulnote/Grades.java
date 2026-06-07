package com.uni_course_planner.constants.modulnote;

public enum Grades
{
	GRADE_1_0(1.0),
	GRADE_1_3(1.3),
	GRADE_1_7(1.7),
	GRADE_2_0(2.0),
	GRADE_2_3(2.3),
	GRADE_2_7(2.7),
	GRADE_3_0(3.0),
	GRADE_3_3(3.3),
	GRADE_3_7(3.7),
	GRADE_4_0(4.0),
	GRADE_5_0(5.0)
	;
	
	private double numeric;
	
	Grades(double numeric)
	{
		this.numeric = numeric;
	}

	public double getNumeric() 
	{
		return numeric;
	}
	
	@Override
	public String toString()
	{
		return numeric + "";
	}
}
