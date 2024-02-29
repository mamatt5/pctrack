package com.fdmgroup.PCTrack.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.PCTrack.model.Version;

@Repository
public interface VersionRepository extends JpaRepository<Version, Integer> {

}
