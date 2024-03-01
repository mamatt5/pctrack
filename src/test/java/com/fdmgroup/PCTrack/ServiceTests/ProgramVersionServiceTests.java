package com.fdmgroup.PCTrack.ServiceTests;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PCTrack.dal.LocationRepository;
import com.fdmgroup.PCTrack.dal.ProgramRepository;
import com.fdmgroup.PCTrack.dal.ProgramVersionRepository;
import com.fdmgroup.PCTrack.dal.VersionRepository;
import com.fdmgroup.PCTrack.model.Location;
import com.fdmgroup.PCTrack.model.Program;
import com.fdmgroup.PCTrack.model.ProgramVersion;
import com.fdmgroup.PCTrack.model.Room;
import com.fdmgroup.PCTrack.model.Version;
import com.fdmgroup.PCTrack.service.LocationService;
import com.fdmgroup.PCTrack.service.ProgramVersionService;

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
public class ProgramVersionServiceTests {
	@Mock
	ProgramVersionRepository programVersionRepo;
	@Mock
	ProgramRepository programRepo;
	@Mock
	VersionRepository versionRepo;
	
	ProgramVersionService programService;
	

	
	@BeforeEach
	void setup() {
		
		this.programService = new ProgramVersionService(programVersionRepo, programRepo, versionRepo);
		
	}
	
	@Test
	void save_program_test() {
		
		ProgramVersion sql8wb = new ProgramVersion(new Program("MySQL 8 Workbench"), new Version("8.0.32"));
		
		programService.save(sql8wb);
		verify(programVersionRepo, times(1)).save(sql8wb);
	}
	
	@Test
	void save_multiple_program_test() {
		

		ProgramVersion microsoftSSMS = new ProgramVersion(new Program("Microsoft MySQL"), new Version("15.0.18333.0"));
		ProgramVersion pnpm = new ProgramVersion(new Program("PNPM"), new Version("8.15.4"));
		ProgramVersion git = new ProgramVersion(new Program("Git"), new Version("2.27.0"));
		ProgramVersion sql8wb = new ProgramVersion(new Program("MySQL 8 Workbench"), new Version("8.0.32"));
		ProgramVersion sqlShell = new ProgramVersion(new Program("MySQL Shell"), new Version("8.0.32"));
		ProgramVersion powerBi = new ProgramVersion(new Program("PowerBi"), new Version("2.126.927.0"));
		
      
		
        programService.save(microsoftSSMS);
		verify(programVersionRepo, times(1)).save(microsoftSSMS);
		
		programService.save(pnpm);
		verify(programVersionRepo, times(1)).save(pnpm);
		
		programService.save(git);
		verify(programVersionRepo, times(1)).save(git);
		
		programService.save(sql8wb);
		verify(programVersionRepo, times(1)).save(sql8wb);
		
		programService.save(sqlShell);
		verify(programVersionRepo, times(1)).save(sqlShell);
		
		programService.save(powerBi);
		verify(programVersionRepo, times(1)).save(powerBi);
		

	}
	
	
	@Test
	void find_all_program_test() {
		
	
		ProgramVersion git = new ProgramVersion(new Program("Git"), new Version("2.27.0"));
		ProgramVersion sql8wb = new ProgramVersion(new Program("MySQL 8 Workbench"), new Version("8.0.32"));
		ProgramVersion sqlShell = new ProgramVersion(new Program("MySQL Shell"), new Version("8.0.32"));
        
		List<ProgramVersion> allProgramVersions= new ArrayList<>();
		
		allProgramVersions.add(git);
		allProgramVersions.add(sql8wb);
		allProgramVersions.add(sqlShell);
		
		
		when(programVersionRepo.findAll()).thenReturn(allProgramVersions);
		
		List<ProgramVersion> foundLocations = programService.findAllProgramVersions();
	
		verify(programVersionRepo, times(1)).findAll();
		assertSame(foundLocations, allProgramVersions);
	}
	
	@Test
	void find_program_by_id_test() {
	
		Optional<ProgramVersion> sql8wb = Optional.of(new ProgramVersion(new Program("MySQL 8 Workbench"), new Version("8.0.32")));

		when(programVersionRepo.findById(1)).thenReturn(sql8wb);
		ProgramVersion foundProgramVersion1 = programService.findById(1);
		
		verify(programVersionRepo, times(1)).findById(1);
		assertSame(sql8wb.get(), foundProgramVersion1);

	}
	
	@Test
	void find_program_by_id_fail_test() {

		assertThrows(RuntimeException.class, () -> programService.findById(1));
		verify(programVersionRepo, times(1)).findById(1);
	}
	
	@Test
	void update_program_test() {
		
		ProgramVersion microsoftSSMS = new ProgramVersion(new Program("Microsoft MySQL"), new Version("15.0.18333.0"));
		microsoftSSMS.setProgramVersionId(1);
		
		when(programVersionRepo.existsById(1)).thenReturn(true);
		programService.update(microsoftSSMS);
		
		verify(programVersionRepo, times(1)).existsById(1);
		verify(programVersionRepo, times(1)).save(microsoftSSMS);

	}
	
	@Test
	void update_program_fail_test() {
		
		ProgramVersion nodejs3 = new ProgramVersion(new Program("Node.js"),new Version("20.2.0"));
		nodejs3.setProgramVersionId(1);
		
		when(programVersionRepo.existsById(1)).thenReturn(false);
		
		assertThrows(RuntimeException.class, () -> programService.update(nodejs3));
		verify(programVersionRepo, times(1)).existsById(1);
		verify(programVersionRepo, times(0)).save(nodejs3);
	}
	
	@Test
	void delete_program_test() {
		
		when(programVersionRepo.existsById(1)).thenReturn(true);
		
		programService.deleteById(1);
		
		verify(programVersionRepo, times(1)).existsById(1);
		verify(programVersionRepo, times(1)).deleteById(1);

	}
	
	@Test
	void delete_program_fail_test() {
		
		when(programVersionRepo.existsById(1)).thenReturn(false);
		
		assertThrows(RuntimeException.class, () -> programService.deleteById(1));
		verify(programVersionRepo, times(1)).existsById(1);
		verify(programVersionRepo, times(0)).deleteById(1);
	}
}
