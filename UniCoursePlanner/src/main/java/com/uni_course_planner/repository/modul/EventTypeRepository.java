package com.uni_course_planner.repository.modul;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uni_course_planner.relation.modul.event_type.*;
import com.uni_course_planner.relation.modul.modul.Modul;

@Repository
public interface EventTypeRepository extends JpaRepository<EventType, EventTypeId>
{
	@Query("SELECT e FROM event_type e WHERE e.modul = :modul")
	List<EventType> findAllByModul(@Param("modul") Modul modul);
}
