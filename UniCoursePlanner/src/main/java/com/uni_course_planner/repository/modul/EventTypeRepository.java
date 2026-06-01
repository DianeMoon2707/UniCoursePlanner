package com.uni_course_planner.repository.modul;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uni_course_planner.relation.modul.event_type.*;

@Repository
public interface EventTypeRepository extends JpaRepository<EventType, EventTypeId>
{

}
