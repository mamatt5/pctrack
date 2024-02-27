package com.fdmgroup.PCTrack.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.PCTrack.model.Computer;

public interface ComputerRepository extends JpaRepository<Computer, Integer> {

}
