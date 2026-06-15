package com.uni_course_planner.repository.zeitraum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uni_course_planner.relation.zeitraum.Zeitraum;

@Repository
public interface ZeitraumRepository extends JpaRepository<Zeitraum, Long>
{

}
