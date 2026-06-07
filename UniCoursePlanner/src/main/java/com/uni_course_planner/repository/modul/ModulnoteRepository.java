package com.uni_course_planner.repository.modul;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uni_course_planner.relation.modul.modul.ModulId;
import com.uni_course_planner.relation.modul.modulnote.Modulnote;

@Repository
public interface ModulnoteRepository extends JpaRepository<Modulnote, ModulId>
{
	@Query("SELECT m FROM modulnote m WHERE m.mId.userId = :userId ORDER BY m.modul.modulname ASC")
	List<Modulnote> findAllByUserId(@Param("userId") Long user);
	
	@Query("SELECT SUM(m.modul.lp) FROM modulnote m WHERE m.mId.userId = :userId")
	Integer sumByUserId(@Param("userId") Long user);
}
