package com.fdmgroup.PCTrack.dal;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fdmgroup.PCTrack.model.Computer;

public interface ComputerRepository extends JpaRepository<Computer, Integer> {
	
	@Query("SELECT c FROM Computer c WHERE CAST(c.computerCode AS text) LIKE CONCAT('%', :code, '%')")
	List<Computer> searchByComputerCode(@Param("code") int code);
}
