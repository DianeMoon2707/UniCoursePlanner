package com.uni_course_planner.repository.user;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uni_course_planner.relation.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
	@Query("""
			SELECT u.email 
			FROM users u 
			WHERE u.id = :id
			""")
	String getEmail(@Param("id") Long id);
}
