package com.fdmgroup.PCTrack.ServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PCTrack.dal.AdminLevelRepository;
import com.fdmgroup.PCTrack.model.AdminLevel;
import com.fdmgroup.PCTrack.service.AdminLevelService;

@ExtendWith(MockitoExtension.class)
public class AdminLevelServiceTests {
	
	@Mock
	AdminLevelRepository adminRepo;
	
	AdminLevelService adminLevelService;
	AdminLevel admin;
	
	@BeforeEach
	public void setup() {
		adminLevelService = new AdminLevelService(adminRepo);
	}
	
	@Test
	public void get_all_levels() {
		List<AdminLevel> adminLevels = new ArrayList<>();
		when(adminRepo.findAll()).thenReturn(adminLevels);
		assertEquals(adminLevels, adminLevelService.findAllLevels());
	}
	
	@Test
	public void save_admin_level() {
		admin = new AdminLevel();
		when(adminRepo.existsById(admin.getId())).thenReturn(false);
		adminLevelService.save(admin);
		verify(adminRepo, times(1)).save(admin);
	}
	
	@Test
	public void save_throws_when_admin_level_exists() {
		admin = new AdminLevel();
		when(adminRepo.existsById(admin.getId())).thenReturn(true);
		assertThrows(RuntimeException.class, ()-> adminLevelService.save(admin));
	}
	
	@Test
	public void get_admin_levels_by_ids() {
		List<AdminLevel> adminLevels = new ArrayList<>();
		List<Integer> adminLevelIds = new ArrayList<>();
		when(adminRepo.findAllById(adminLevelIds)).thenReturn(adminLevels);
		assertEquals(adminLevels, adminLevelService.getAdminLevelsByIds(adminLevelIds));
	}

}
