package com.fdmgroup.PCTrack.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.PCTrack.model.RoomAdmin;
import com.fdmgroup.PCTrack.model.Staff;

@Repository
public interface RoomAdminRepository extends JpaRepository<RoomAdmin, Integer> {

	RoomAdmin findByStaffId(int staffId);

}

