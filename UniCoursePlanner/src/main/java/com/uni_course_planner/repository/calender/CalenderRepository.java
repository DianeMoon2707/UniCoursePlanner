package com.uni_course_planner.repository.calender;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uni_course_planner.relation.calender.Calender;

@Repository
public interface CalenderRepository extends JpaRepository<Calender, Long>
{
	@Query("""
			SELECT c 
			FROM calender c 
			WHERE c.user.id = :user 
			AND c.date = :date
			ORDER BY c.time ASC
			""")
	List<Calender> getEventsOfDay(@Param("user") Long user, @Param("date") LocalDate date);

	@Query("""
			SELECT c.date 
			FROM calender c 
			WHERE c.user.id = :user 
			AND c.date BETWEEN :startDate AND :endDate
			ORDER BY c.date
			""")
	List<LocalDate> getEventDates(@Param("user") Long user, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
