package com.fdmgroup.PCTrack.ControllerTests;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PCTrack.controller.VersionController;
import com.fdmgroup.PCTrack.model.Version;
import com.fdmgroup.PCTrack.service.ComputerService;
import com.fdmgroup.PCTrack.service.VersionService;

import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@ExtendWith(MockitoExtension.class)
public class VersionControllerTests {
	
	@Mock
	VersionService versionService;
	@Mock
	ComputerService computerService;
	
	VersionController versionController;
	
	@BeforeEach
	void setup() {
		this.versionController = new VersionController(versionService);
	}
	
	@Test
	void findVersionById_test() {
		
		Version vscode = new Version("1.46.1");
		
		when(versionService.findById(1)).thenReturn(vscode);
		assertSame(vscode, versionController.findById(1));
		verify(versionService, times(1)).findById(1);
	}
	
	@Test
	void createVersion_test() {
		Version v1 = new Version("2107");
		
		when(versionService.findById(0)).thenReturn(v1);
		assertSame(v1, versionController.createNewVersion(v1));
		verify(versionService, times(1)).save(v1);
	}
	
	@Test
	void updateVersion_test() {
		Version v1 = new Version("2.27.0");
		Version newV1 = new Version("3.27.0");

		when(versionService.findById(0)).thenReturn(newV1);
		assertSame(versionController.updateVersion(v1), newV1);
		verify(versionService, times(1)).update(v1);
	}
	
	@Test
	void deleteVersion_test() {
		versionController.deleteVersion(1);
		verify(versionService, times(1)).deleteById(1);
	}
	
	@Test
	void findAllVersions() {
		Version v1 = new Version("1.46.1");
		Version v2 = new Version("4.22");
		Version v3 = new Version("20.11.0");
		Version v4 = new Version("3.9.7427.0");
		Version v5 = new Version("10.2.4");
		Version v6 = new Version("8.0.32");
		Version v7 = new Version("8.0.32");
		Version v8 = new Version("2.126.927.0");


		
		List<Version> allVersions = new ArrayList<>();
		allVersions.add(v1);
		allVersions.add(v2);
		allVersions.add(v3);
		allVersions.add(v4);
		allVersions.add(v5);
		allVersions.add(v6);
		allVersions.add(v7);
		allVersions.add(v8);
		
		when(versionService.findAllVersions()).thenReturn(allVersions);
		assertSame(versionController.getVersions(), allVersions);
		verify(versionService, times(1)).findAllVersions();
	}
}
