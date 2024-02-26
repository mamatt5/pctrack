package com.fdmgroup.PCTrack;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import com.fdmgroup.PCTrack.model.Computer;
import com.fdmgroup.PCTrack.model.Program;
import com.fdmgroup.PCTrack.model.Room;
import com.fdmgroup.PCTrack.model.User;
import com.fdmgroup.PCTrack.service.ComputerService;
import com.fdmgroup.PCTrack.service.ProgramService;
import com.fdmgroup.PCTrack.service.UserService;

@Service
public class Dataloader implements ApplicationRunner {
	private ComputerService computerService;
	private ProgramService programService;
	private UserService userService;

	@Autowired
	public Dataloader(ComputerService computerService, ProgramService programService, UserService userService) {
		super();
		this.computerService = computerService;
		this.programService = programService;
		this.userService = userService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Program vscode = new Program("Visual Studio Code", "1.46.1");
		Program eclipse = new Program("Eclipse", "4.22");
		Program nodejs = new Program("Node.js", "20.11.0");
		Program nodejs1 = new Program("Node.js", "20.10.0");
		Program nodejs2 = new Program("Node.js", "12.18.1");
		Program nodejs3 = new Program("Node.js", "20.2.0");
		Program nodejs4 = new Program("Node.js", "20.5");
		Program nodejs5 = new Program("Node.js", "20.9");
		Program nodejs6 = new Program("Node.js", "20.1.0");
		
		Program python = new Program("Python Laucher", "3.9.7427.0");
		Program npm = new Program("NPM", "10.2.4");
		Program npm1 = new Program("NPM", "10.2.3");
		Program npm2 = new Program("NPM", "6.14.5");
		Program npm3 = new Program("NPM", "9.6.6"); 
		Program npm4 = new Program("NPM", "9.8"); 
		Program npm5 = new Program("NPM", "10.1.0");
		Program npm6 = new Program("NPM", "9.6.4");
		Program sql8wb = new Program("MySQL 8 Workbench", "8.0.32");
		Program sqlShell = new Program("MySQL Shell", "8.0.32");
		Program powerBi = new Program("PowerBi", "-");
		Program excel = new Program("Excel", "2107");
		Program excel1 = new Program("Excel", "2208");
		Program microsoftSSMS = new Program("Microsoft MySQL", "15.0.18333.0");
		Program pnpm = new Program("PNPM", "8.15.4");
		Program git = new Program("Git", "2.27.0");
		Program jdk = new Program("JDK", "17.0.2");

		List<Program> programs = Arrays.asList(vscode, eclipse, nodejs, nodejs1, nodejs2, nodejs3, nodejs4, nodejs5,nodejs6, python, npm, npm1, npm2, npm3, npm4, npm5,
				sql8wb, sqlShell, powerBi, excel, excel1, microsoftSSMS, pnpm, git, jdk);
		programService.saveAll(programs);
	

		//bondi rooms
		Computer c1 = new Computer(15040, new Room(),
				Arrays.asList(vscode, eclipse, nodejs, python, npm, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk));
		Computer c2 = new Computer(70156, new Room(),
				Arrays.asList(vscode, eclipse, nodejs1, python, npm1, excel, microsoftSSMS, git, jdk));
		Computer c3 = new Computer(15046, new Room(),
				Arrays.asList(vscode, eclipse, nodejs, python, npm, sql8wb, sqlShell, excel1, microsoftSSMS, git, jdk));
		Computer c4 = new Computer(15068, new Room(),
				Arrays.asList(vscode, eclipse, nodejs1, python, npm2, excel, microsoftSSMS, git, jdk));
		Computer c5 = new Computer(15048, new Room(),
				Arrays.asList(vscode, eclipse, nodejs, python, npm2, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk));		
		Computer c6 = new Computer(15052, new Room(), null);
		Computer c7 = new Computer(15036, new Room(), Arrays.asList(vscode, eclipse, nodejs2, python, npm2, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk));
		Computer c8 = new Computer(70101, new Room(), Arrays.asList(vscode, eclipse, nodejs2, python, npm2, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk));
		Computer c9 = new Computer(70108, new Room(), null);
		Computer c10 = new Computer(15054, new Room(), Arrays.asList(vscode, eclipse, nodejs2, python, npm3, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk));
		Computer c11 = new Computer(15037, new Room(), Arrays.asList(vscode, eclipse, nodejs3, python, npm, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk));
		Computer c12 = new Computer(70104, new Room(), Arrays.asList(vscode, eclipse, nodejs4, python, npm4, excel, microsoftSSMS, git, jdk));
		Computer c13 = new Computer(15038, new Room(), Arrays.asList(vscode, eclipse, nodejs5, python, npm5, sql8wb, sqlShell, excel1, microsoftSSMS, git, jdk));
		Computer c14 = new Computer(70160, new Room(), null);	
		Computer c15 = new Computer(15070, new Room(), null);
		
		//coogee
		Computer c16 = new Computer(15030, new Room(), Arrays.asList(vscode,eclipse,nodejs2,python,npm,excel,microsoftSSMS,git,jdk));
		Computer c17 = new Computer(70213, new Room(), null);
		Computer c18 = new Computer(70161, new Room(), Arrays.asList(vscode,eclipse,nodejs6,python,npm6, excel, microsoftSSMS, git, jdk));
		Computer c19 = new Computer(70098, new Room(), null);
		Computer c20 = new Computer(70093, new Room(), null);
		Computer c21 = new Computer(15035, new Room(), null);
		Computer c22 = new Computer(15064, new Room(), null);
		Computer c23 = new Computer(70106, new Room(), null);
		Computer c24 = new Computer(70162, new Room(), null);
		
		computerService.save(c1);
		computerService.save(c2);
		computerService.save(c3);
		computerService.save(c4);
		computerService.save(c5);
		computerService.save(c6);
		computerService.save(c7);
		computerService.save(c8);
		computerService.save(c9);
		computerService.save(c10);
		computerService.save(c11);
		computerService.save(c12);
		computerService.save(c13);
		computerService.save(c14);
		computerService.save(c15);
		computerService.save(c16);
		computerService.save(c17);
		computerService.save(c18);
		computerService.save(c19);
		computerService.save(c20);
		computerService.save(c21);
		computerService.save(c22);
		computerService.save(c23);
		computerService.save(c24);
		
		User u1 = new User("andy.joe", "password123", "Andy", "Joe", LocalDate.of(27, 11, 2023));
		User u2 = new User("ahri.foxian", "password123", "Ahri", "Foxian", LocalDate.of(27, 11, 2023));
		User u3 = new User("aatrox.damion", "password123", "Aatrox", "Damion", LocalDate.of(27, 11, 2023));
		User u4 = new User("amumu.munsen", "password123", "Amumu", "Munsen", LocalDate.of(27, 11, 2023));
		User u5 = new User("blitzcrank.botter", "password123", "Blitzcrank", "Botter", LocalDate.of(27, 11, 2023));
		User u6 = new User("baron.nashor", "password123", "Baron", "Nashor", LocalDate.of(27, 11, 2023));
		User u7 = new User("darwin.norman", "password123", "Darwin", "Norman", LocalDate.of(27, 11, 2023));
		User u8 = new User("camden.leonard", "password123", "Camden", "Leonard", LocalDate.of(27, 11, 2023));
		User u9 = new User("quincy.jarvis", "password123", "Quincy", "Jarvis", LocalDate.of(27, 11, 2023));
		User u10 = new User("chaim.harrison", "password123", "Chaim", "Harrison", LocalDate.of(27, 11, 2023));
		
		userService.register(u1);
		userService.register(u2);
		userService.register(u3);
		userService.register(u4);
		userService.register(u5);
		userService.register(u6);
		userService.register(u7);
		userService.register(u8);
		userService.register(u9);
		userService.register(u10);
		
	}

}
