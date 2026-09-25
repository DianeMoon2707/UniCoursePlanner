package com.uni_course_planner.repository.modul;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uni_course_planner.relation.modul.modul.*;

@Repository
public interface ModulRepository extends JpaRepository<Modul, ModulId>
{
	@Query("SELECT COALESCE(MAX(m.mId.modulId), 0) FROM modul m WHERE m.mId.userId = :userId")
	Long getMaxModulId(@Param("userId") Long user);
	
	@Query("SELECT SUM(m.lp) FROM modul m WHERE m.mId.userId = :userId AND m.grade IS NOT NULL")
	Integer sumByUserId(@Param("userId") Long user);
	
	@Query("SELECT m FROM modul m WHERE m.mId.userId = :userId ORDER BY m.modulname ASC")
	List<Modul> findAllByUserId(@Param("userId") Long user);
	
	@Query("""
		SELECT m
		FROM modul m
		WHERE m.modulname = :modulname
		AND m.mId.userId = :userId
	""")
	Optional<Modul> findModulByModulnameAndUserId(@Param("modulname") String modulname, @Param("userId") Long user);
}
