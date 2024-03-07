package com.fdmgroup.PCTrack.ControllerTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PCTrack.controller.AdminLevelController;
import com.fdmgroup.PCTrack.model.AdminLevel;
import com.fdmgroup.PCTrack.service.AdminLevelService;

@ExtendWith(MockitoExtension.class)
public class AdminLevelControllerTests {
	
	@Mock
	AdminLevelService adminService;
	
	AdminLevelController adminLevelController;
	AdminLevel room;
	AdminLevel location;
	AdminLevel business;
	
	@BeforeEach
	public void setup() {
		adminLevelController = new AdminLevelController(adminService);
	}
	
	@Test
	public void get_admin_levels() {
		List<AdminLevel> allLevels = new ArrayList<>();
		allLevels.add(room);
		allLevels.add(location);
		allLevels.add(business);
		
		when(adminService.findAllLevels()).thenReturn(allLevels);
		assertEquals(allLevels, adminLevelController.getAdminLevels());
		
	}

}
