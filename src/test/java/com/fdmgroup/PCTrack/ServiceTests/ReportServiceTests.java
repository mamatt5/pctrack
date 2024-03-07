package com.fdmgroup.PCTrack.ServiceTests;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PCTrack.dal.ReportRepository;
import com.fdmgroup.PCTrack.model.Computer;
import com.fdmgroup.PCTrack.model.Report;
import com.fdmgroup.PCTrack.model.User;
import com.fdmgroup.PCTrack.service.ReportService;

import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTests {

	@Mock
	ReportRepository reportRepo;
	
	ReportService reportService;
	Report r1;
	Computer c1;
	User u1;
	LocalDate d1;
	
	
	@BeforeEach
	void setup() {
		
		this.reportService = new ReportService(reportRepo);
		c1 = new Computer(130);
		u1 = new User("username1", "password1", "first1", "last1", "email1@email.com");
		d1= LocalDate.now();
		r1 = new Report(c1, u1, d1, "No mouse");
		
	}
	
	@Test
	void save_report_test() {
		
		Report report1 = r1;
		
		reportService.save(report1);
		verify(reportRepo, times(1)).save(report1);
	}
	
	@Test
	void save_report_existed_test() {
		Report report1 = r1;
		
		when(reportRepo.existsById(report1.getReportId())).thenReturn(true);
		
		assertThrows(RuntimeException.class, () -> reportService.save(report1));
		
		verify(reportRepo, never()).save(report1);
	}
	
	@Test
	void save_multiple_report_test() {
		
		Computer comp1 = new Computer(130);
		User user1 = new User("username1", "password1", "first1", "last1", "email1@email.com");
		LocalDate date1= LocalDate.now();
		Computer comp2 = new Computer(130);
		User user2 = new User("username2", "password2", "first2", "last2", "email2@email.com");
		LocalDate date2= LocalDate.now();
		Computer comp3 = new Computer(130);
		User user3 = new User("username3", "password3", "first3", "last3", "email3@email.com");
		LocalDate date3= LocalDate.now();
		
		Report report1 = new Report(comp1, user1, date1, "Can't connet to internet");
        Report report2 = new Report(comp2, user2, date2, "No keyboard");
        Report report3 = new Report(comp3, user3, date3, "Cannot boot");
				
        reportService.save(report1);
		verify(reportRepo, times(1)).save(report1);
		
		reportService.save(report2);
		verify(reportRepo, times(1)).save(report2);
		
		reportService.save(report3);
		verify(reportRepo, times(1)).save(report3);
		

	}
	
	
	@Test
	void find_all_report_test() {
		Computer comp1 = new Computer(130);
		User user1 = new User("username1", "password1", "first1", "last1", "email1@email.com");
		LocalDate date1= LocalDate.now();
		Computer comp2 = new Computer(130);
		User user2 = new User("username2", "password2", "first2", "last2", "email2@email.com");
		LocalDate date2= LocalDate.now();
		Computer comp3 = new Computer(130);
		User user3 = new User("username3", "password3", "first3", "last3", "email3@email.com");
		LocalDate date3= LocalDate.now();
	
	
		Report report1 = new Report(comp1, user1, date1, "Can't connet to internet");
        Report report2 = new Report(comp2, user2, date2, "No keyboard");
        Report report3 = new Report(comp3, user3, date3, "Cannot boot");
        
		List<Report> allReports = new ArrayList<>();
		
		allReports.add(report1);
		allReports.add(report2);
		allReports.add(report3);
		
		
		when(reportRepo.findAll()).thenReturn(allReports);
		
		List<Report> foundReports = reportService.findAllReports();
	
		verify(reportRepo, times(1)).findAll();
		assertSame(foundReports, allReports);
	}
	
	@Test
	void find_report_by_id_test() {
		
		Optional<Report> report1 = Optional.of(r1);

		when(reportRepo.findById(1)).thenReturn(report1);
		Report foundReport1 = reportService.findById(1);
		
		verify(reportRepo, times(1)).findById(1);
		assertSame(report1.get(), foundReport1);

	}
	
	@Test
	void find_report_by_id_fail_test() {

		assertThrows(RuntimeException.class, () -> reportService.findById(1));
		verify(reportRepo, times(1)).findById(1);
	}
	
	@Test
	void update_report_test_when_nonexist() {
		
		Report report1 = r1;
		report1.setReportId(1);
		
		when(reportRepo.existsById(1)).thenReturn(true);
		reportService.update(report1);
		
		verify(reportRepo, times(1)).existsById(1);
		verify(reportRepo, times(1)).save(report1);
	}
	
	@Test
	void update_report_test_when_exist() {
		
		Report report1 = r1;
		report1.setReportId(1);
		reportService.save(r1);
		
		when(reportRepo.existsById(1)).thenReturn(true);
		reportService.update(report1);
		
		verify(reportRepo, times(2)).existsById(1);
		verify(reportRepo, times(2)).save(report1);
	}
	
	@Test
	void update_report_fail_test() {
		
		Report report1 = r1;
		report1.setReportId(1);
		
		when(reportRepo.existsById(1)).thenReturn(false);
		
		assertThrows(RuntimeException.class, () -> reportService.update(report1));
		verify(reportRepo, times(0)).save(report1);
	}
	
	@Test
	void delete_report_test() {
		
		when(reportRepo.existsById(1)).thenReturn(true);
		
		reportService.deleteById(1);
		
		verify(reportRepo, times(1)).existsById(1);
		verify(reportRepo, times(1)).deleteById(1);

	}
	
	@Test
	void delete_report_fail_test() {
		
		when(reportRepo.existsById(1)).thenThrow(RuntimeException.class);
		
		assertThrows(RuntimeException.class, () -> reportService.deleteById(1));
		verify(reportRepo, times(1)).existsById(1);
		verify(reportRepo, times(0)).deleteById(1);
	}
}
