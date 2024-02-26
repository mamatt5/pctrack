package com.fdmgroup.PCTrack.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.PCTrack.model.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {

}
