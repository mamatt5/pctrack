package com.fdmgroup.PCTrack.ServiceTests;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PCTrack.dal.LocationRepository;
import com.fdmgroup.PCTrack.dal.SoftwareRepository;
import com.fdmgroup.PCTrack.dal.ProgramRepository;
import com.fdmgroup.PCTrack.model.Location;
import com.fdmgroup.PCTrack.model.Program;
import com.fdmgroup.PCTrack.model.Software;
import com.fdmgroup.PCTrack.model.Room;
import com.fdmgroup.PCTrack.service.LocationService;
import com.fdmgroup.PCTrack.service.ProgramService;
import com.fdmgroup.PCTrack.service.SoftwareService;

import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@ExtendWith(MockitoExtension.class)
public class ProgramServiceTests {
	@Mock
	ProgramRepository programRepo;
	@Mock
	SoftwareRepository softwareRepo;

	
	ProgramService programService;
	

	
	@BeforeEach
	void setup() {
		
		this.programService = new ProgramService(programRepo, softwareRepo);
		
	}
	
	@Test
	void save_software_test() {
		
		Program sql8wb = new Program(new Software("MySQL 8 Workbench"), "8.0.32");
		
		programService.save(sql8wb);
		verify(programRepo, times(1)).save(sql8wb);
	}
	
	@Test
	void save_multiple_software_test() {
		

		Program microsoftSSMS = new Program(new Software("Microsoft MySQL"), "15.0.18333.0");
		Program pnpm = new Program(new Software("PNPM"), "8.15.4");
		Program git = new Program(new Software("Git"), "2.27.0");
		Program sql8wb = new Program(new Software("MySQL 8 Workbench"), "8.0.32");
		Program sqlShell = new Program(new Software("MySQL Shell"), "8.0.32");
		Program powerBi = new Program(new Software("PowerBi"), "2.126.927.0");
		
      
		
        programService.save(microsoftSSMS);
		verify(programRepo, times(1)).save(microsoftSSMS);
		
		programService.save(pnpm);
		verify(programRepo, times(1)).save(pnpm);
		
		programService.save(git);
		verify(programRepo, times(1)).save(git);
		
		programService.save(sql8wb);
		verify(programRepo, times(1)).save(sql8wb);
		
		programService.save(sqlShell);
		verify(programRepo, times(1)).save(sqlShell);
		
		programService.save(powerBi);
		verify(programRepo, times(1)).save(powerBi);
		

	}
	
	
	@Test
	void find_all_program_test() {
		
	
		Program git = new Program(new Software("Git"), "2.27.0");
		Program sql8wb = new Program(new Software("MySQL 8 Workbench"), "8.0.32");
		Program sqlShell = new Program(new Software("MySQL Shell"), "8.0.32");
        
		List<Program> allPrograms= new ArrayList<>();
		
		allPrograms.add(git);
		allPrograms.add(sql8wb);
		allPrograms.add(sqlShell);
		
		
		when(programRepo.findAll()).thenReturn(allPrograms);
		
		List<Program> foundLocations = programService.findAllPrograms();
	
		verify(programRepo, times(1)).findAll();
		assertSame(foundLocations, allPrograms);
	}
	
	@Test
	void find_program_by_id_test() {
	
		Optional<Program> sql8wb = Optional.of(new Program(new Software("MySQL 8 Workbench"), "8.0.32"));

		when(programRepo.findById(1)).thenReturn(sql8wb);
		Program foundProgram1 = programService.findById(1);
		
		verify(programRepo, times(1)).findById(1);
		assertSame(sql8wb.get(), foundProgram1);

	}
	
	@Test
	void find_program_by_id_fail_test() {

		assertThrows(RuntimeException.class, () -> programService.findById(1));
		verify(programRepo, times(1)).findById(1);
	}
	
	@Test
	void update_program_test() {
		
		Program microsoftSSMS = new Program(new Software("Microsoft MySQL"), "15.0.18333.0");
		microsoftSSMS.setProgramId(1);
		
		when(programRepo.existsById(1)).thenReturn(true);
		programService.update(microsoftSSMS);
		
		verify(programRepo, times(1)).existsById(1);
		verify(programRepo, times(1)).save(microsoftSSMS);

	}
	
	@Test
	void update_program_fail_test() {
		
		Program nodejs3 = new Program(new Software("Node.js"),"20.2.0");
		nodejs3.setProgramId(1);
		
		when(programRepo.existsById(1)).thenReturn(false);
		
		assertThrows(RuntimeException.class, () -> programService.update(nodejs3));
		verify(programRepo, times(1)).existsById(1);
		verify(programRepo, times(0)).save(nodejs3);
	}
	
	@Test
	void delete_program_test() {
		
		when(programRepo.existsById(1)).thenReturn(true);
		
		programService.deleteById(1);
		
		verify(programRepo, times(1)).existsById(1);
		verify(programRepo, times(1)).deleteById(1);

	}
	
	@Test
	void delete_program_fail_test() {
		
		when(programRepo.existsById(1)).thenReturn(false);
		
		assertThrows(RuntimeException.class, () -> programService.deleteById(1));
		verify(programRepo, times(1)).existsById(1);
		verify(programRepo, times(0)).deleteById(1);
	}
}
