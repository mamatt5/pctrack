package com.fdmgroup.PCTrack.dal;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fdmgroup.PCTrack.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
	Optional<Location> findByName(String name);
}
