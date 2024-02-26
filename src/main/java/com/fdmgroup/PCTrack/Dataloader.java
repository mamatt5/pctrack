package com.fdmgroup.PCTrack;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import com.fdmgroup.PCTrack.model.Program;
import com.fdmgroup.PCTrack.service.ComputerService;
import com.fdmgroup.PCTrack.service.ProgramService;

@Service
public class Dataloader implements ApplicationRunner {
	private ComputerService computerService;
	private ProgramService programService;

	@Autowired
	public Dataloader(ComputerService computerService, ProgramService programService) {
		super();
		this.computerService = computerService;
		this.programService = programService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Program vscode = new Program("Visual Studio Code", "1.46.1");
		Program eclipse = new Program("Eclipse", "4.22");
		Program nodejs = new Program("Node.js", "20.11.0");
		Program nodejs1 = new Program("Node.js", "20.10.0");
		Program nodejs2 = new Program("Node.js", "12.18.1");
		Program python = new Program("Python Laucher", "3.9.7427.0");
		Program npm = new Program("NPM", "10.2.4");
		Program npm1 = new Program("NPM", "10.2.3");
		Program npm2 = new Program("NPM", "6.14.5");
		Program sql8wb = new Program("MySQL 8 Workbench", "8.0.32");
		Program sqlShell = new Program("MySQL Shell", "8.0.32");
		Program powerBi = new Program("PowerBi", "-");
		Program excel = new Program("Excel", "2107");
		Program excel1 = new Program("Excel", "2208");
		Program microsoftSSMS = new Program("Microsoft MySQL", "15.0.18333.0");
		Program pnpm = new Program("PNPM", "8.15.4");
		Program git = new Program("Git", "2.27.0");
		Program jdk = new Program("JDK", "17.0.2");

		List<Program> programs = Arrays.asList(vscode, eclipse, nodejs, nodejs1, nodejs2, python, npm, npm1, npm2,
				sql8wb, sqlShell, powerBi, excel, excel1, microsoftSSMS, pnpm, git, jdk);
		programService.saveAll(programs);
	}

}
