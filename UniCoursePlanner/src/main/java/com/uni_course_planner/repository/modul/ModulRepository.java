package com.uni_course_planner.repository.modul;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.uni_course_planner.relation.modul.modul.*;

@Repository
public interface ModulRepository extends JpaRepository<Modul, ModulId>
{
	@Query("SELECT COALESCE(MAX(m.mId.modulId), 0) FROM modul m")
	Long getMaxModulId();
}
