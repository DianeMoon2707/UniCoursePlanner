package com.uni_course_planner.repository.module;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uni_course_planner.constants.module.EventTypes;
import com.uni_course_planner.entity.module.event_type.*;
import com.uni_course_planner.entity.module.module.*;
import com.uni_course_planner.entity.module.module.Module;

@Repository
public interface EventTypeRepository extends JpaRepository<EventType, EventTypeId>
{
	@Query("""
			SELECT e 
			FROM event_type e
			WHERE e.module.modulename = :module 
			AND e.eId.type = :type
			AND e.module.user.id = :user
			""")
	EventType getByModulnameAndType(@Param("module") String module, @Param("type") EventTypes type, @Param("user") Long user);
	
	@Query("SELECT e FROM event_type e WHERE e.module = :module")
	List<EventType> findAllByModul(@Param("module") Module module);
	
	@Query("SELECT e FROM event_type e WHERE e.eId.mId.userId = :userId")
	List<EventType> findAllByUserId(@Param("userId") Long user);
	
	@Query("""
			SELECT COUNT(e) > 0
			FROM event_type e
			WHERE e.eId.mId = :mId
			""")
	boolean existsByMId(@Param("mId") ModuleId mId);
}
