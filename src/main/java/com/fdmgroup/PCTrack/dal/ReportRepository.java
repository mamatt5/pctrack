package com.fdmgroup.PCTrack.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.PCTrack.model.Report;

public interface ReportRepository extends JpaRepository<Report, Integer> {

}
