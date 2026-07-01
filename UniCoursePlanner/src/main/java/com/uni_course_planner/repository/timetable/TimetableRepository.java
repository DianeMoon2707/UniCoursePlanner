package com.uni_course_planner.repository.timetable;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uni_course_planner.relation.timetable.Timetable;

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
}
