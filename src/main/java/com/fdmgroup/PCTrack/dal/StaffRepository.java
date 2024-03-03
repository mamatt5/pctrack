package com.fdmgroup.PCTrack.dal;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fdmgroup.PCTrack.model.Location;
import com.fdmgroup.PCTrack.model.Room;
import com.fdmgroup.PCTrack.model.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
	@Query("SELECT s FROM Staff s WHERE s.user.id = :userId")
	List<Staff> findByUserId(@Param("userId") Integer userId);


	@Query("SELECT r FROM Room r WHERE r.location.id IN (SELECT s.location.id FROM Staff s WHERE s.user.id = :userId AND (s.adminLevel.name like 'Location' OR s.adminLevel.name like 'Business'))")
	List<Room> findRoomsStaffIsAdmin(@Param("userId") Integer userId);

	Page<Staff> findAll(Pageable pageable);

	@Query("SELECT COUNT(s) FROM Staff s WHERE s.user.username LIKE %:query%")
	long countByUsernameLike(String query);

	@Query("SELECT s FROM Staff s WHERE s.user.username LIKE %:query%")
	Page<Staff> findPartial(@Param("query") String query, Pageable pageable);

}
