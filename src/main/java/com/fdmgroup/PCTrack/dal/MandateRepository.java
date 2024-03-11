package com.fdmgroup.PCTrack.dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fdmgroup.PCTrack.model.Mandate;
import com.fdmgroup.PCTrack.model.RoomAdmin;

public interface MandateRepository extends JpaRepository<Mandate, Integer>{

	List<Mandate> findByRoomAdmin(RoomAdmin roomAdmin);

}
