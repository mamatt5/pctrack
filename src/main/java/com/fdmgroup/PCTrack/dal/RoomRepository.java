package com.fdmgroup.PCTrack.dal;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fdmgroup.PCTrack.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer>{
	Optional<Room> findByName(String name);

}
