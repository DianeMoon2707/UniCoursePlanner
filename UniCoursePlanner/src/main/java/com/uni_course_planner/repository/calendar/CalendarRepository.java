package com.uni_course_planner.repository.calendar;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uni_course_planner.entity.calendar.Calendar;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long>
{
	@Query("""
			SELECT c 
			FROM calendar c 
			WHERE c.user.id = :user 
			AND c.date = :date
			ORDER BY c.time ASC
			""")
	List<Calendar> findEventsOfDay(@Param("user") Long user, @Param("date") LocalDate date);

	@Query("""
			SELECT c.date 
			FROM calendar c 
			WHERE c.user.id = :user 
			AND c.date BETWEEN :startDate AND :endDate
			ORDER BY c.date
			""")
	List<LocalDate> findEventDates(@Param("user") Long user, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
