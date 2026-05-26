package com.uni_course_planner.repository.user;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uni_course_planner.relation.user.LogInData;


@Repository
public interface LogInDataRepository extends JpaRepository<LogInData, Long>
{
	@Query("""
		SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END 
		FROM log_in_data l 
		WHERE l.username = :username 
			AND l.password = :password
			""")
	boolean existsByLogInData(@Param("username") String username, @Param("password") String password);
}
