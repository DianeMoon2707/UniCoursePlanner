package com.uni_course_planner.repository.timetable;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uni_course_planner.constants.timetable.*;
import com.uni_course_planner.entity.module.event_type.EventType;
import com.uni_course_planner.entity.timetable.Timetable;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long>
{
	@Query("""
			SELECT t 
			FROM timetable t 
			WHERE t.event.eId.mId.userId = :userId 
			ORDER BY 
				CASE t.time 
					WHEN 'SLOT_08_10' THEN 0
					WHEN 'SLOT_10_12' THEN 1
					WHEN 'SLOT_12_14' THEN 2
					WHEN 'SLOT_14_16' THEN 3
					WHEN 'SLOT_16_18' THEN 4 
				END,
				CASE t.day 
					WHEN 'MO' THEN 0
					WHEN 'DI' THEN 1
					WHEN 'MI' THEN 2
					WHEN 'DO' THEN 3
					WHEN 'FR' THEN 4
				END
			""")
	List<Timetable> findAllByUserId(@Param("userId") Long user);
	
	@Query("""
			SELECT t
			FROM timetable t
			WHERE t.time = :time
			AND t.day = :day
			AND (
				(:room IS NULL AND t.room IS NULL)
				OR t.room = :room
			)
			AND t.event = :modul
			""")
	Timetable findByAttributs(
			@Param("time") Timeslot time, 
			@Param("day") Weekday day,
			@Param("room") String room,
			@Param("modul") EventType modul);
	
	@Query("""
			SELECT t
			FROM timetable t
			WHERE t.time = :time
			AND t.day = :day
			AND t.event = :modul
			""")
	Optional<Timetable> findEntryOfACell(
			@Param("time") Timeslot time, 
			@Param("day") Weekday day,
			@Param("modul") EventType modul);
}
