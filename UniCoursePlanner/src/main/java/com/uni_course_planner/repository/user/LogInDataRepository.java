package com.uni_course_planner.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uni_course_planner.relation.user.LogInData;

@Repository
public interface LogInDataRepository extends JpaRepository<LogInData, Long>
{
	@Query("""
		SELECT l 
		FROM log_in_data l 
		WHERE l.username = :username
			""")
	Optional<LogInData> findLogInDataByUsername(@Param("username") String username);
	
	@Query("""
			SELECT l.username
			FROM log_in_data l 
			WHERE l.user.email = :email
			""")
	String getUsernameByEmail(@Param("email") String email);
}
