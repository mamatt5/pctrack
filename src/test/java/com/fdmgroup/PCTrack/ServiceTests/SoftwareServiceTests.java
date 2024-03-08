package com.fdmgroup.PCTrack.ServiceTests;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PCTrack.dal.SoftwareRepository;
import com.fdmgroup.PCTrack.model.Software;
import com.fdmgroup.PCTrack.service.SoftwareService;

import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@ExtendWith(MockitoExtension.class)
public class SoftwareServiceTests {
	@Mock
	SoftwareRepository softwareRepo;
	
	SoftwareService softwareService;
	

	
	@BeforeEach
	void setup() {
		
		this.softwareService = new SoftwareService(softwareRepo);
		
	}
	
	@Test
	void save_software_test() {
		
		Software sql8wb = new Software("MySQL 8 Workbench");
		
		softwareService.save(sql8wb);
		verify(softwareRepo, times(1)).save(sql8wb);
	}
	
	@Test
	void save_software_exists_test() {
		Software sql8wb = new Software("MySQL 8 Workbench");
		sql8wb.setSoftwareId(1);
		
		when(softwareRepo.existsById(1)).thenReturn(true);
		
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            softwareService.save(sql8wb);
        });
		
		assertEquals("Software already exists", exception.getMessage());
        verify(softwareRepo, times(1)).existsById(1);
        verify(softwareRepo, never()).save(sql8wb);
	}
	
	@Test
	void save_multiple_software_test() {
		

		Software microsoftSSMS = new Software("Microsoft MySQL");
		Software pnpm = new Software("PNPM");
		Software git = new Software("Git");
		Software sql8wb = new Software("MySQL 8 Workbench");
		Software sqlShell = new Software("MySQL Shell");
		Software powerBi = new Software("PowerBi");
		
      
		
        softwareService.save(microsoftSSMS);
		verify(softwareRepo, times(1)).save(microsoftSSMS);
		
		softwareService.save(pnpm);
		verify(softwareRepo, times(1)).save(pnpm);
		
		softwareService.save(git);
		verify(softwareRepo, times(1)).save(git);
		
		softwareService.save(sql8wb);
		verify(softwareRepo, times(1)).save(sql8wb);
		
		softwareService.save(sqlShell);
		verify(softwareRepo, times(1)).save(sqlShell);
		
		softwareService.save(powerBi);
		verify(softwareRepo, times(1)).save(powerBi);
		

	}
	
	  @Test
	    public void save_all_software_exists_test() {
	        Software existingSoftware = new Software();
	        existingSoftware.setSoftwareId(1);

	        List<Software> softwares = new ArrayList<>();
	        softwares.add(existingSoftware);

	        when(softwareRepo.existsById(existingSoftware.getSoftwareId())).thenReturn(true);

	       assertThrows(RuntimeException.class, () -> {softwareService.saveAll(softwares);});

	        verify(softwareRepo, times(1)).existsById(existingSoftware.getSoftwareId());
	        verify(softwareRepo, never()).save(any(Software.class));
	    }

	    @Test
	    public void save_all_software_test() {
	        Software newSoftware1 = new Software();
	        newSoftware1.setSoftwareId(2);

	        Software newSoftware2 = new Software();
	        newSoftware2.setSoftwareId(3);

	        List<Software> softwares = new ArrayList<>();
	        softwares.add(newSoftware1);
	        softwares.add(newSoftware2);

	        when(softwareRepo.existsById(newSoftware1.getSoftwareId())).thenReturn(false);
	        when(softwareRepo.existsById(newSoftware2.getSoftwareId())).thenReturn(false);

	        softwareService.saveAll(softwares);

	        verify(softwareRepo, times(1)).existsById(newSoftware1.getSoftwareId());
	        verify(softwareRepo, times(1)).existsById(newSoftware2.getSoftwareId());
	        verify(softwareRepo, times(1)).save(newSoftware1);
	        verify(softwareRepo, times(1)).save(newSoftware2);
	    }
	
	@Test
	void find_all_software_test() {
		
	
		Software git = new Software("Git");
		Software sql8wb = new Software("MySQL 8 Workbench");
		Software sqlShell = new Software("MySQL Shell");
        
		List<Software> allSoftwares= new ArrayList<>();
		
		allSoftwares.add(git);
		allSoftwares.add(sql8wb);
		allSoftwares.add(sqlShell);
		
		
		when(softwareRepo.findAll()).thenReturn(allSoftwares);
		
		List<Software> foundLocations = softwareService.findAllSoftwares();
	
		verify(softwareRepo, times(1)).findAll();
		assertSame(foundLocations, allSoftwares);
	}
	
	@Test
	void find_software_by_id_test() {
	
		Optional<Software> sql8wb = Optional.of(new Software("MySQL 8 Workbench"));

		when(softwareRepo.findById(1)).thenReturn(sql8wb);
		Software foundSoftware1 = softwareService.findById(1);
		
		verify(softwareRepo, times(1)).findById(1);
		assertSame(sql8wb.get(), foundSoftware1);

	}
	
	@Test
	void find_software_by_id_fail_test() {

		assertThrows(RuntimeException.class, () -> softwareService.findById(1));
		verify(softwareRepo, times(1)).findById(1);
	}
	
	@Test
	void update_software_test() {
		
		Software microsoftSSMS = new Software("Microsoft MySQL");
		microsoftSSMS.setSoftwareId(1);
		
		when(softwareRepo.existsById(1)).thenReturn(true);
		softwareService.update(microsoftSSMS);
		
		verify(softwareRepo, times(1)).existsById(1);
		verify(softwareRepo, times(1)).save(microsoftSSMS);

	}
	
	@Test
	void update_software_fail_test() {
		
		Software nodejs3 = new Software("Node.js");
		nodejs3.setSoftwareId(1);
		
		when(softwareRepo.existsById(1)).thenReturn(false);
		
		assertThrows(RuntimeException.class, () -> softwareService.update(nodejs3));
		verify(softwareRepo, times(1)).existsById(1);
		verify(softwareRepo, times(0)).save(nodejs3);
	}
	
	@Test
	void delete_software_test() {
		
		when(softwareRepo.existsById(1)).thenReturn(true);
		
		softwareService.deleteById(1);
		
		verify(softwareRepo, times(1)).existsById(1);
		verify(softwareRepo, times(1)).deleteById(1);

	}
	
	@Test
	void delete_software_fail_test() {
		
		when(softwareRepo.existsById(1)).thenReturn(false);
		
		assertThrows(RuntimeException.class, () -> softwareService.deleteById(1));
		verify(softwareRepo, times(1)).existsById(1);
		verify(softwareRepo, times(0)).deleteById(1);
	}
}
