package com.uni_course_planner.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uni_course_planner.relation.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{

}
