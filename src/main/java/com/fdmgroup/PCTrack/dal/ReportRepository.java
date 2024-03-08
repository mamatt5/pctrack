package com.fdmgroup.PCTrack.dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.fdmgroup.PCTrack.model.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
	@Query("SELECT r FROM Report r WHERE r.computer.computerId = :computerId")
	List<Report> findByComputerId(@Param("computerId") Integer computerId);
}

