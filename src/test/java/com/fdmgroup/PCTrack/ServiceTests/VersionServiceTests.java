package com.fdmgroup.PCTrack.ServiceTests;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PCTrack.dal.VersionRepository;
import com.fdmgroup.PCTrack.model.Version;
import com.fdmgroup.PCTrack.service.VersionService;

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
public class VersionServiceTests {
	@Mock
	VersionRepository versionRepo;
	
	VersionService versionService;
	

	
	@BeforeEach
	void setup() {
		
		this.versionService = new VersionService(versionRepo);
		
	}
	
	@Test
	void save_version_test() {
		
		Version sql8wb = new Version("8.0.32");
		
		versionService.save(sql8wb);
		verify(versionRepo, times(1)).save(sql8wb);
	}
	
	@Test
	void save_multiple_version_test() {
		

		Version microsoftSSMS = new Version("15.0.18333.0");
		Version pnpm = new Version("8.15.4");
		Version git = new Version("2.27.0");
		Version sql8wb = new Version("8.0.32");
		Version sqlShell = new Version("8.0.32");
		Version powerBi = new Version("2.126.927.0");
		
      
		
        versionService.save(microsoftSSMS);
		verify(versionRepo, times(1)).save(microsoftSSMS);
		
		versionService.save(pnpm);
		verify(versionRepo, times(1)).save(pnpm);
		
		versionService.save(git);
		verify(versionRepo, times(1)).save(git);
		
		versionService.save(sql8wb);
		verify(versionRepo, times(1)).save(sql8wb);
		
		versionService.save(sqlShell);
		verify(versionRepo, times(1)).save(sqlShell);
		
		versionService.save(powerBi);
		verify(versionRepo, times(1)).save(powerBi);
		

	}
	
	
	@Test
	void find_all_version_test() {
		
	
		Version git = new Version("2.27.0");
		Version sql8wb = new Version("8.0.32");
		Version sqlShell = new Version("8.0.32");
        
		List<Version> allVersions= new ArrayList<>();
		
		allVersions.add(git);
		allVersions.add(sql8wb);
		allVersions.add(sqlShell);
		
		
		when(versionRepo.findAll()).thenReturn(allVersions);
		
		List<Version> foundLocations = versionService.findAllVersions();
	
		verify(versionRepo, times(1)).findAll();
		assertSame(foundLocations, allVersions);
	}
	
	@Test
	void find_version_by_id_test() {
	
		Optional<Version> sql8wb = Optional.of(new Version("8.0.32"));

		when(versionRepo.findById(1)).thenReturn(sql8wb);
		Version foundVersion1 = versionService.findById(1);
		
		verify(versionRepo, times(1)).findById(1);
		assertSame(sql8wb.get(), foundVersion1);

	}
	
	@Test
	void find_version_by_id_fail_test() {

		assertThrows(RuntimeException.class, () -> versionService.findById(1));
		verify(versionRepo, times(1)).findById(1);
	}
	
	@Test
	void update_version_test() {
		
		Version microsoftSSMS = new Version("15.0.18333.0");
		microsoftSSMS.setVersionId(1);
		
		when(versionRepo.existsById(1)).thenReturn(true);
		versionService.update(microsoftSSMS);
		
		verify(versionRepo, times(1)).existsById(1);
		verify(versionRepo, times(1)).save(microsoftSSMS);

	}
	
	@Test
	void update_version_fail_test() {
		
		Version nodejs3 = new Version("20.2.0");
		nodejs3.setVersionId(1);
		
		when(versionRepo.existsById(1)).thenReturn(false);
		
		assertThrows(RuntimeException.class, () -> versionService.update(nodejs3));
		verify(versionRepo, times(1)).existsById(1);
		verify(versionRepo, times(0)).save(nodejs3);
	}
	
	@Test
	void delete_version_test() {
		
		when(versionRepo.existsById(1)).thenReturn(true);
		
		versionService.deleteById(1);
		
		verify(versionRepo, times(1)).existsById(1);
		verify(versionRepo, times(1)).deleteById(1);

	}
	
	@Test
	void delete_version_fail_test() {
		
		when(versionRepo.existsById(1)).thenReturn(false);
		
		assertThrows(RuntimeException.class, () -> versionService.deleteById(1));
		verify(versionRepo, times(1)).existsById(1);
		verify(versionRepo, times(0)).deleteById(1);
	}
}
