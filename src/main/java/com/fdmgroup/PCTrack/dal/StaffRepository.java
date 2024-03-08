package com.fdmgroup.PCTrack.dal;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fdmgroup.PCTrack.model.AdminLevel;
import com.fdmgroup.PCTrack.model.Location;
import com.fdmgroup.PCTrack.model.Room;
import com.fdmgroup.PCTrack.model.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
	@Query("SELECT s FROM Staff s WHERE s.user.id = :userId")
	List<Staff> findByUserId(@Param("userId") Integer userId);

	@Query("SELECT r FROM Room r WHERE r.location.id IN (SELECT s.location.id FROM Staff s WHERE s.user.id = :userId AND "
			+ "(s.adminLevel.name like 'Location' OR s.adminLevel.name like 'Business'))")
	List<Room> findRoomsStaffIsAdmin(@Param("userId") Integer userId);
	
	@Query("SELECT s.roomAssigned FROM Staff s WHERE s.user.id = :userId")
	List<Room> findRoomAssignedByUserId(@Param("userId") Integer userId);
	
	@Query("SELECT r FROM Room r WHERE r.location.id IN (SELECT s.location.id FROM Staff s WHERE s.user.id = :userId)")
	List<Room> findRoomsStaffIsRegisteredIn(@Param("userId") Integer userId);

	Page<Staff> findAll(Pageable pageable);

	@Query("SELECT COUNT(s) FROM Staff s " +
		       "JOIN s.location l " +
		       "JOIN s.adminLevel al " +
		       "WHERE s.user.username LIKE %:query% " +
		       "AND (:locations IS NULL OR l.locationId IN :locations) " +
		       "AND (:adminLevels IS NULL OR al.id IN :adminLevels)")
	long countByUsernameLike(String query, @Param("locations") List<Integer> locations,
			@Param("adminLevels") List<Integer> adminLevels);

	@Query("SELECT s FROM Staff s " + "JOIN s.location l " + "JOIN s.adminLevel al "
			+ "WHERE s.user.username LIKE :query% " + "AND (:locations IS NULL OR l.locationId IN :locations) "
			+ "AND (:adminLevels IS NULL OR al.id IN :adminLevels) " + "ORDER BY s.user.username ASC")
	Page<Staff> findPartial(@Param("query") String query, Pageable pageable,
			@Param("locations") List<Integer> locations, @Param("adminLevels") List<Integer> adminLevels);

	List<Staff> findAllByOrderByUserUsernameAsc();

	// Custom query method to find staff by multiple locations and admin levels
	Page<Staff> findByLocationInAndAdminLevelIn(List<Location> locations, List<AdminLevel> adminLevels,
			Pageable pageable);

	long countByLocationInAndAdminLevelIn(List<Location> locations, List<AdminLevel> adminLevels);

}
