package com.fdmgroup.PCTrack.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.PCTrack.model.ProgramVersion;
@Repository
public interface ProgramVersionRepository extends JpaRepository<ProgramVersion, Integer> {

}
