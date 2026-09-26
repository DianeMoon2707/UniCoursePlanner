package com.uni_course_planner.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uni_course_planner.entity.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{	
	@Query("SELECT u FROM users u WHERE u.email = :email")
	Optional<User> findUserByEmail(@Param("email") String email);
	
	@Query("SELECT u.email FROM users u WHERE u.id = :id")
	String getEmail(@Param("id") Long id);
}
