package com.fdmgroup.PCTrack.dal;


import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fdmgroup.PCTrack.model.Computer;
import com.fdmgroup.PCTrack.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
	Optional<Room> findByName(String name);
	
	@Query("SELECT r FROM Room r WHERE r.name LIKE CONCAT('%', :name ,'%')")
	List<Room> searchByName(@Param("name") String name);
	
	@Query("SELECT c FROM Computer c WHERE c.room.id = :roomId")
	List<Computer> getComputersInRoom(@Param("roomId") Integer roomId);
}
