package com.fdmgroup.PCTrack.dal;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fdmgroup.PCTrack.model.Program;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Integer> {
	Optional<Program> findByName(String name);
}
