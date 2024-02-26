package com.fdmgroup.PCTrack.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fdmgroup.PCTrack.model.Computer;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Integer>{

}
