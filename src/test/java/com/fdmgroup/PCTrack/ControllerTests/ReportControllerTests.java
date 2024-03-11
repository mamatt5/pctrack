package com.fdmgroup.PCTrack.ControllerTests;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PCTrack.controller.ReportController;
import com.fdmgroup.PCTrack.model.Computer;
import com.fdmgroup.PCTrack.model.Report;
import com.fdmgroup.PCTrack.model.SearchConfig;
import com.fdmgroup.PCTrack.service.ReportService;

import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@ExtendWith(MockitoExtension.class)
public class ReportControllerTests {
	
	@Mock
	ReportService reportService;
	
	ReportController reportController;
	Report report1;
	Report report2;
	Report report3;
	
	@BeforeEach
	public void setup() {
		reportController = new ReportController(reportService);
	}

	
	@Test
	public void get_all_reports() {
		List<Report> allReports = new ArrayList<>();
		allReports.add(report1);
		allReports.add(report2);
		allReports.add(report3);
		
		when(reportService.findAllReports()).thenReturn(allReports);
		assertEquals(allReports, reportController.getReports());
		
	}
	
	@Test
	public void find_report_by_id() {
		when(reportService.findById(0)).thenReturn(report1);
		assertEquals(report1, reportController.findById(0));
	}
	
	@Test
	public void filter_by_date() {
		Report report1 = new Report();
		Report report2 = new Report();
		Report report3 = new Report();
		Report report4 = new Report();
		List<Report> reports = new ArrayList<>();
		reports.add(report1);
		reports.add(report2);
		reports.add(report3);
		reports.add(report4);
		
		when(reportService.sortReportsByDate()).thenReturn(reports);
		
		assertEquals(reports, reportController.sortReportsByDate());
	}
	
	@Test
	public void filter_by_computer_code() {
		Report report1 = new Report();
		Report report2 = new Report();
		Report report3 = new Report();
		Report report4 = new Report();
		List<Report> reports = new ArrayList<>();
		reports.add(report1);
		reports.add(report2);
		reports.add(report3);
		reports.add(report4);
		
		when(reportService.sortReportsByComputerCode()).thenReturn(reports);
		
		assertEquals(reports, reportController.sortReportsByComputerCode());
	}
	
	@Test
	public void filter_by_resolved_status() {
		Report report1 = new Report();
		Report report2 = new Report();
		Report report3 = new Report();
		Report report4 = new Report();
		List<Report> reports = new ArrayList<>();
		reports.add(report1);
		reports.add(report2);
		reports.add(report3);
		reports.add(report4);
		
		when(reportService.sortReportsByResolvedStatus()).thenReturn(reports);
		
		assertEquals(reports, reportController.sortReportsByResolvedStatus());
	}
	
	@Test
	public void create_report() {
		report1 = new Report();
		when(reportService.findById(0)).thenReturn(report1);
		assertEquals(report1, reportController.createNewReport(report1));
		verify(reportService, times(1)).save(report1);
	}
	
	@Test
	public void update_report() {
		report1 = new Report();
		when(reportService.findById(0)).thenReturn(report1);
		assertEquals(report1, reportController.updateReport(report1));
		verify(reportService, times(1)).update(report1);
	}
	
	@Test
	public void delete_report() {
		reportController.deleteReport(0);
		verify(reportService, times(1)).deleteById(0);
	}
}
