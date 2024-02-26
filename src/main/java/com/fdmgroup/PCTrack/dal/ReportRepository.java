package com.fdmgroup.PCTrack.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fdmgroup.PCTrack.model.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
	
}