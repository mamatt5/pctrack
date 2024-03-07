package com.fdmgroup.PCTrack.ModelTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.PCTrack.model.Computer;
import com.fdmgroup.PCTrack.model.Program;
import com.fdmgroup.PCTrack.model.Software;

@ExtendWith(MockitoExtension.class)
public class ComputerTests {
	
	Software vscode = new Software("Visual Studio Code");
	Software eclipse = new Software("Eclipse");
	Software nodejs = new Software("Node.js");
	Software python = new Software("Python Launcher");
	Software npm = new Software("NPM");
	Software sql8wb = new Software("MySQL 8 Workbench");
	Software sqlShell = new Software("MySQL Shell");
	Software powerBi = new Software("PowerBi");
	Software excel = new Software("Excel");
	Software microsoftSSMS = new Software("Microsoft MySQL");
	Software pnpm = new Software("PNPM");
	Software git = new Software("Git");
	Software jdk = new Software("JDK");
	Software visualStudio = new Software("Visual Studio");
	
	Program vscodeV1 = new Program(vscode, "1.46.1");
	Program eclipseV1 = new Program(eclipse, "4.22");
	Program nodejsV1 = new Program(nodejs, "20.11.0");
	Program pythonV1 = new Program(python, "3.9.7427");
	Program npmV1 = new Program(npm, "10.2.4");
	Program sql8wbV1 = new Program(sql8wb, "8.0.32");
	Program sqlShellV1 = new Program(sqlShell, "8.0.32");
	Program powerBiV1 = new Program(powerBi, "2.126.927.0");
	Program excelV1 = new Program(excel, "2107");
	Program microsoftSSMSV1 = new Program(microsoftSSMS, "15.0.18333.0");
	Program pnpmV1 = new Program(pnpm, "8.15.4");
	Program gitV1 = new Program(git, "2.27.0");
	Program jdkV1 = new Program(jdk, "17.0.2");
	Program visualStudioV1 = new Program(visualStudio, "1.87.0");
	
	@Test
	public void computer_initialized_is_not_ready() {
		Computer computer = new Computer(00000);
		assertEquals("NONE", computer.getRole());
	}
	
	@Test
	public void computer_initialized_is_dev_ready() {
		Computer computer = new Computer(00000);
		computer.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV1, pythonV1, npmV1, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1, visualStudioV1));
		
		assertEquals("DEV", computer.getRole());
	}
	
	@Test
	public void computer_initialized_is_bi_ready() {
		Computer computer = new Computer(00000);
		computer.setProgramList(Arrays.asList(excelV1, powerBiV1));
		
		assertEquals("BI", computer.getRole());
	}
	
	@Test
	public void computer_initialized_is_ready_for_both() {
		Computer computer = new Computer(00000);
		computer.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV1, pythonV1, npmV1, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1, visualStudioV1, excelV1, powerBiV1));
		
		assertEquals("BOTH", computer.getRole());
	}

}
