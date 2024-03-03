package com.fdmgroup.PCTrack.dal;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.PCTrack.model.Staff;
import com.fdmgroup.PCTrack.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);
	List<User> findByUsernameContainingIgnoreCase(String username);
	boolean existsByUsername(String username);
	
	
	Page<User> findAll(Pageable pageable);
	
	@Query("SELECT u FROM User u WHERE u.username LIKE :query%")
	Page<User> findPartial(@Param("query") String query, Pageable pageable);
	

	@Query("SELECT COUNT(s) FROM User s WHERE s.username LIKE %:query%")
	long countByUsernameLike(String query); 
	
	
	
	
}
 