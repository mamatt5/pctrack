package com.fdmgroup.PCTrack.service;

import java.util.List;

import com.fdmgroup.PCTrack.dal.ReportRepository;
import com.fdmgroup.PCTrack.model.Report;

public class ReportService {
	private ReportRepository reportRepository;

	public ReportService(ReportRepository reportRepository) {
		super();
		this.reportRepository = reportRepository;
	}
	
	public List<Report> findAllReports() {
		return this.reportRepository.findAll();
	}
	
	public Report findById(int reportId) {
		return this.reportRepository.findById(reportId).orElseThrow(()-> new RuntimeException("Report not found"));
	}
	
	public void save(Report newReport) {
		if (this.reportRepository.existsById(newReport.getReportId())) {
			throw new RuntimeException("Report already exists");
		
		} else {
			this.reportRepository.save(newReport);
		}
	}
	
	public void deleteById(int reportId) {
		if (this.reportRepository.existsById(reportId)) {
			reportRepository.deleteById(reportId);
		}
	}
	
	public void update(Report newReport) {
		if (this.reportRepository.existsById(newReport.getReportId())) {
			this.reportRepository.save(newReport);
		
		} else {
			throw new RuntimeException("Report does not exist");
		}
	}
}
