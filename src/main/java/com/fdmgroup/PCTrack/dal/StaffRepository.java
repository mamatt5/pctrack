package com.fdmgroup.PCTrack.dal;

import java.util.List;
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

	@Query("SELECT r FROM Room r WHERE r.location.id IN (SELECT s.location.id FROM Staff s WHERE s.user.id = :userId AND (s.adminLevel like 'Location' OR s.adminLevel like 'Business'))")
	List<Room> findRoomsStaffIsAdmin(@Param("userId") Integer userId);
}
