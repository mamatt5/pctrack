package com.fdmgroup.PCTrack.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.PCTrack.model.Program;

public interface ProgramRepository extends JpaRepository<Program, Integer> {

}
