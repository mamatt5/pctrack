package com.fdmgroup.PCTrack.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.PCTrack.model.Software;

@Repository
public interface SoftwareRepository extends JpaRepository<Software, Integer> {

}
