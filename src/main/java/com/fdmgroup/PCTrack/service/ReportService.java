package com.fdmgroup.PCTrack.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fdmgroup.PCTrack.dal.ReportRepository;
import com.fdmgroup.PCTrack.model.Report;
import com.fdmgroup.PCTrack.model.Staff;

@Service
public class ReportService {
	private ReportRepository reportRepo;
	
	@Autowired
	public ReportService(ReportRepository reportRepo) {
		super();
		this.reportRepo = reportRepo;
	}
	
	public List<Report> findAllReports() {
		return this.reportRepo.findAll();
	}
	
	public Report findById(int reportId) {
		return this.reportRepo.findById(reportId).orElseThrow(() -> new RuntimeException("Report not found"));
	}
	
	public List<Report> findByComputerId(int computerId) {
		return this.reportRepo.findByComputerId(computerId);
	}
	
	public void save(Report newReport) {
		if (this.reportRepo.existsById(newReport.getReportId())) {
			throw new RuntimeException("Report already exists");
		} else {
			this.reportRepo.save(newReport);
		}
	}
	
	public void deleteById(int reportId) {
		if (this.reportRepo.existsById(reportId)) {
			reportRepo.deleteById(reportId);
		}
	}
	
	public void update(Report newReport) {
		if (this.reportRepo.existsById(newReport.getReportId())) {
			this.reportRepo.save(newReport);
		} else {
			throw new RuntimeException("Report does not exist");
		}
	}
}

