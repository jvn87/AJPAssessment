package com.repository;

import com.entity.User;	
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	//Find user by username
	@Query("SELECT u FROM User u WHERE u.username = :username")
	User findUserByUsername(@Param("username") String username);
	
	//Find user by ID
	@Query("SELECT u FROM User u WHERE u.id = :id")
	User findUserById(@Param("id") Long id);
	
	//Find user by email
	@Query("SELECT u FROM User u WHERE u.email = :email")
	User findUserByEmail(@Param("email") String email);
	
	//Find user role by username
	@Query("SELECT u.role FROM User u WHERE u.username = :username")
	String findRoleByUsername(@Param("username") String username);
	
}
