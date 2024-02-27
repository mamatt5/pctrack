package com.fdmgroup.PCTrack.dal;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.fdmgroup.PCTrack.model.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
	@Query("SELECT s FROM Staff s WHERE s.user.id = :userId")
	List<Staff> findByUserId(@Param("userId") Integer userId);
}
