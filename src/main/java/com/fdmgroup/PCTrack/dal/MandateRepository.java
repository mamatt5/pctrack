package com.fdmgroup.PCTrack.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fdmgroup.PCTrack.model.Mandate;

public interface MandateRepository extends JpaRepository<Mandate, Integer>{

}
