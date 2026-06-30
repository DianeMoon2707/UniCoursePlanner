package com.uni_course_planner.repository.timetable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uni_course_planner.relation.timetable.Timetable;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long>
{

}
