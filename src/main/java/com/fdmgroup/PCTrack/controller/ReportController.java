package com.fdmgroup.PCTrack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.PCTrack.model.Report;
import com.fdmgroup.PCTrack.service.ReportService;
@RestController
@CrossOrigin("http://localhost:5173")
public class ReportController {
	private ReportService reportService;

	@Autowired
	public ReportController(ReportService reportService) {
		super();
		this.reportService = reportService;
	}
	
	@GetMapping("reports")
	public List<Report> getReports() {
		return reportService.findAllReports();
	}
	
	@GetMapping("reports/date")
	public List<Report> sortReportsByDate() {
		return reportService.sortReportsByDate();
	}
	
	@GetMapping("reports/computerCode")
	public List<Report> sortReportsByComputerCode() {
		return reportService.sortReportsByComputerCode();
	}
	
	@GetMapping("reports/resolved")
	public List<Report> sortReportsByResolvedStatus() {
		return reportService.sortReportsByResolvedStatus();
	}
	
	@GetMapping("reports/{reportId}")
	public Report findById(@PathVariable int reportId) {
		return reportService.findById(reportId); 
	}
	
	@PostMapping("reports")
	public Report createNewReport(@RequestBody Report newReport) {
		reportService.save(newReport);
		return reportService.findById(newReport.getReportId());
	}
	
	@PutMapping("reports")
	public Report updateReport(@RequestBody Report newReport) {
		reportService.update(newReport);
		return reportService.findById(newReport.getReportId());
	}
	 
	@DeleteMapping("reports/{reportId}")
	public void deleteReport(@PathVariable int reportId) {
		reportService.deleteById(reportId);
	}

}
