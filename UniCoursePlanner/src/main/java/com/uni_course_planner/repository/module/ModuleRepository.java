package com.uni_course_planner.repository.module;

import java.util.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uni_course_planner.entity.module.module.*;
import com.uni_course_planner.entity.module.module.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, ModuleId>
{
	@Query("SELECT COALESCE(MAX(m.mId.moduleId), 0) FROM module m WHERE m.mId.userId = :userId")
	Long getMaxModuleId(@Param("userId") Long user);
	
	@Query("SELECT SUM(m.credits) FROM module m WHERE m.mId.userId = :userId AND m.grade IS NOT NULL")
	Integer sumByUserId(@Param("userId") Long user);
	
	@Query("SELECT m FROM module m WHERE m.mId.userId = :userId ORDER BY m.modulename ASC")
	List<Module> findAllByUserId(@Param("userId") Long user);
	
	@Query("""
		SELECT m
		FROM module m
		WHERE m.modulename = :modulename
		AND m.mId.userId = :userId
	""")
	Optional<Module> findModuleByModulenameAndUserId(@Param("modulename") String modulename, @Param("userId") Long user);
}
