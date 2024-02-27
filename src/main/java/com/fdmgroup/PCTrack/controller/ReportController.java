package com.fdmgroup.PCTrack.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.PCTrack.model.Report;
import com.fdmgroup.PCTrack.service.ReportService;
@RestController
public class ReportController {
	private ReportService reportService;

	public ReportController(ReportService reportService) {
		super();
		this.reportService = reportService;
	}
	
	public List<Report> getReports() {
		return reportService.findAllReports();
	}
	
	public Report findById(@PathVariable int reportId) {
		return reportService.findById(reportId);
	}
	
	public Report createNewReport(@RequestBody Report newReport) {
		reportService.save(newReport);
		return reportService.findById(newReport.getReportId());
	}
	
	public Report updateReport(@RequestBody Report newReport) {
		reportService.update(newReport);
		return reportService.findById(newReport.getReportId());
	}
	
	public void deleteReport(@PathVariable int reportId) {
		reportService.deleteById(reportId);
	}

}
