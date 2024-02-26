package com.fdmgroup.PCTrack.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fdmgroup.PCTrack.model.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
	
}
