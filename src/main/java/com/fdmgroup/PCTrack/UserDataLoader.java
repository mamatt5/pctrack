package com.fdmgroup.PCTrack;

import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import com.fdmgroup.PCTrack.model.*;
import com.fdmgroup.PCTrack.service.*;

@Service
public class UserDataLoader implements ApplicationRunner {
	
	private UserService userService;
	private LocationService locationService;
	private StaffService staffService;
	private ComputerService computerService;
	private ProgramService programService;
	
	@Autowired
	public UserDataLoader(UserService userService, LocationService locationService, StaffService staffService,
			ComputerService computerService, ProgramService programService) {
		super();
		this.userService = userService;
		this.locationService = locationService;
		this.staffService = staffService;
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
		Program powerBi = new Program("PowerBi", "2.126.927.0");
		Program excel = new Program("Excel", "2107");
		Program excel1 = new Program("Excel", "2208");
		Program microsoftSSMS = new Program("Microsoft MySQL", "15.0.18333.0");
		Program pnpm = new Program("PNPM", "8.15.4");
		Program git = new Program("Git", "2.27.0");
		Program jdk = new Program("JDK", "17.0.2");

		List<Program> programs = Arrays.asList(vscode, eclipse, nodejs, nodejs1, nodejs2, nodejs3, nodejs4, nodejs5,
		        nodejs6, python, npm, npm1, npm2, npm3, npm4, npm5, sql8wb, sqlShell, powerBi, excel, excel1,
		        microsoftSSMS, pnpm, git, jdk);
		programService.saveAll(programs);

		// bondi rooms
		Computer c1 = new Computer(15040,
		        Arrays.asList(vscode, eclipse, nodejs, python, npm, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk));
		Computer c2 = new Computer(70156,
		        Arrays.asList(vscode, eclipse, nodejs1, python, npm1, excel, microsoftSSMS, git, jdk));
		Computer c3 = new Computer(15046,
		        Arrays.asList(vscode, eclipse, nodejs, python, npm, sql8wb, sqlShell, excel1, microsoftSSMS, git, jdk));
		Computer c4 = new Computer(15068,
		        Arrays.asList(vscode, eclipse, nodejs1, python, npm2, excel, microsoftSSMS, git, jdk));
		Computer c5 = new Computer(15048,
		        Arrays.asList(vscode, eclipse, nodejs, python, npm2, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk));
		Computer c6 = new Computer(15052, null);
		Computer c7 = new Computer(15036, Arrays.asList(vscode, eclipse, nodejs2, python, npm2, sql8wb, sqlShell, excel,
		        microsoftSSMS, git, jdk));
		Computer c8 = new Computer(70101, Arrays.asList(vscode, eclipse, nodejs2, python, npm2, sql8wb, sqlShell, excel,
		        microsoftSSMS, git, jdk));
		Computer c9 = new Computer(70108, null);
		Computer c10 = new Computer(15054, Arrays.asList(vscode, eclipse, nodejs2, python, npm3, sql8wb, sqlShell,
		        excel, microsoftSSMS, git, jdk));
		Computer c11 = new Computer(15037,
		        Arrays.asList(vscode, eclipse, nodejs3, python, npm, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk));
		Computer c12 = new Computer(70104,
		        Arrays.asList(vscode, eclipse, nodejs4, python, npm4, excel, microsoftSSMS, git, jdk));
		Computer c13 = new Computer(15038, Arrays.asList(vscode, eclipse, nodejs5, python, npm5, sql8wb, sqlShell,
		        excel1, microsoftSSMS, git, jdk));
		Computer c14 = new Computer(70160, null);
		Computer c15 = new Computer(15070, null);

		// coogee
		Computer c16 = new Computer(15030,
		        Arrays.asList(vscode, eclipse, nodejs2, python, npm, excel, microsoftSSMS, git, jdk));
		Computer c17 = new Computer(70213, null);
		Computer c18 = new Computer(70161,
		        Arrays.asList(vscode, eclipse, nodejs6, python, npm6, excel, microsoftSSMS, git, jdk));
		Computer c19 = new Computer(70098, null);
		Computer c20 = new Computer(70093, null);
		Computer c21 = new Computer(15035, null);
		Computer c22 = new Computer(15064, null);
		Computer c23 = new Computer(70106, null);
		Computer c24 = new Computer(70162, null);

		// balmoral
		Computer c25 = new Computer(70215, Arrays.asList(vscode, eclipse, python, npm, excel, microsoftSSMS, git, jdk));
		Computer c26 = new Computer(70111, Arrays.asList(vscode, eclipse, python, npm, excel, microsoftSSMS, git, jdk));
		Computer c27 = new Computer(15042, null);
		Computer c28 = new Computer(15033,
		        Arrays.asList(vscode, eclipse, python, npm2, excel, microsoftSSMS, git, jdk));
		Computer c29 = new Computer(70163,
		        Arrays.asList(vscode, eclipse, python, npm3, excel, microsoftSSMS, git, jdk));
		Computer c30 = new Computer(15066,
		        Arrays.asList(vscode, eclipse, python, npm3, excel, microsoftSSMS, git, jdk));
		Computer c31 = new Computer(15032, null);
		Computer c32 = new Computer(70159, null);
		Computer c33 = new Computer(15031, null);

		// bronte
		Computer c34 = new Computer(70196,
		        Arrays.asList(vscode, nodejs2, python, npm2, excel, microsoftSSMS, git, jdk));
		Computer c35 = new Computer(70204,
		        Arrays.asList(vscode, eclipse, nodejs2, python, npm2, excel, microsoftSSMS, git, jdk));
		Computer c36 = new Computer(70095,
		        Arrays.asList(vscode, eclipse, nodejs2, python, npm3, excel, microsoftSSMS, git, jdk));
		Computer c37 = new Computer(70207,
		        Arrays.asList(vscode, nodejs2, python, npm1, excel, microsoftSSMS, git, jdk));
		Computer c38 = new Computer(70198, null);
		Computer c39 = new Computer(15056, null);
		Computer c40 = new Computer(70210, null);
		Computer c41 = new Computer(70212, null);

		// tamarama
		Computer c42 = new Computer(15059, Arrays.asList(vscode, eclipse, nodejs1, python, npm2, sql8wb, sqlShell,
		        excel, microsoftSSMS, git, jdk, powerBi));
		Computer c43 = new Computer(15091, Arrays.asList(vscode, eclipse, nodejs2, python, npm3, sql8wb, sqlShell,
		        excel, microsoftSSMS, git, jdk, powerBi));
		Computer c44 = new Computer(70263, Arrays.asList(vscode, eclipse, nodejs1, python, npm, sql8wb, sqlShell, excel,
		        microsoftSSMS, git, jdk, powerBi));
		Computer c45 = new Computer(70272, Arrays.asList(vscode, eclipse, nodejs2, python, npm, sql8wb, sqlShell, excel,
		        microsoftSSMS, git, jdk, powerBi));
		Computer c46 = new Computer(70613, Arrays.asList(vscode, eclipse, nodejs3, python, npm, sql8wb, sqlShell, excel,
		        microsoftSSMS, git, jdk, powerBi));
		Computer c47 = new Computer(70707,
		        Arrays.asList(vscode, eclipse, nodejs1, python, npm2, excel, microsoftSSMS, git, jdk));
		Computer c48 = new Computer(70898, Arrays.asList(vscode, eclipse, nodejs1, python, npm2, sql8wb, sqlShell,
		        excel, microsoftSSMS, git, jdk, powerBi));
		Computer c49 = new Computer(15656, Arrays.asList(vscode, eclipse, nodejs4, python, npm3, sql8wb, sqlShell,
		        excel, microsoftSSMS, git, jdk));
		Computer c50 = new Computer(70410,
		        Arrays.asList(vscode, eclipse, nodejs1, python, npm, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk));
		Computer c51 = new Computer(70412, Arrays.asList(vscode, eclipse, nodejs2, python, npm2, sql8wb, sqlShell,
		        excel, microsoftSSMS, git, jdk, powerBi));
		Computer c52 = new Computer(15231,
		        Arrays.asList(vscode, eclipse, nodejs2, python, npm, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk));
		Computer c53 = new Computer(72196, Arrays.asList(vscode, eclipse, nodejs1, python, npm3, sql8wb, sqlShell,
		        excel, microsoftSSMS, git, jdk, powerBi));
		Computer c54 = new Computer(72210,
		        Arrays.asList(vscode, eclipse, nodejs3, python, npm3, excel, microsoftSSMS, git, jdk, powerBi));
		Computer c55 = new Computer(72212, Arrays.asList(vscode, eclipse, nodejs1, python, npm2, sql8wb, sqlShell,
		        excel, microsoftSSMS, git, jdk));
		Computer c56 = new Computer(15091, Arrays.asList(vscode, eclipse, nodejs1, python, npm, sql8wb, sqlShell, excel,
		        microsoftSSMS, git, jdk, powerBi));
		Computer c57 = new Computer(72196, Arrays.asList(vscode, eclipse, nodejs6, python, npm, sql8wb, sqlShell, excel,
		        microsoftSSMS, git, jdk, powerBi));

//		computerService.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16,
//		        c17, c18, c19, c20, c21, c22, c23, c24, c25, c26, c27, c28, c29, c30, c31, c32, c33, c34, c35, c36, c37,
//		        c38, c39, c40, c41, c42, c43, c44, c45, c46, c47, c48, c49, c50, c51, c52, c53, c54, c55, c56, c57));
		User u0 = new User("admin", "admin", "Root", "Admin");
        User u1 = new User("andy.joe", "password123", "Andy", "Joe", LocalDate.of(2023, 11, 27));
        User u2 = new User("ahri.foxian", "password123", "Ahri", "Foxian", LocalDate.of(2023, 11, 27));
        User u3 = new User("aatrox.damion", "password123", "Aatrox", "Damion", LocalDate.of(2023, 11, 27));
        User u4 = new User("amumu.munsen", "password123", "Amumu", "Munsen", LocalDate.of(2023, 11, 27));
        User u5 = new User("blitzcrank.botter", "password123", "Blitzcrank", "Botter", LocalDate.of(2023, 11, 27));
        User u6 = new User("baron.nashor", "password123", "Baron", "Nashor", LocalDate.of(2023, 11, 27));
        User u7 = new User("darwin.norman", "password123", "Darwin", "Norman", LocalDate.of(2023, 11, 27));
        User u8 = new User("camden.leonard", "password123", "Camden", "Leonard", LocalDate.of(2023, 11, 27));
        User u9 = new User("quincy.jarvis", "password123", "Quincy", "Jarvis", LocalDate.of(2023, 11, 27));
        User u10 = new User("chaim.harrison", "password123", "Chaim", "Harrison", LocalDate.of(2023, 11, 27));
        // no need to add a join date, can automatically generate it. 
        User u11 = new User("po.po", "password123", "Po", "Po");
        
        userService.register(u0);
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
        userService.register(u11);
        
        // sample data for inheritance, pls delete Jenny
        Location location1 = new Location("FDM Sydney", "Sydney");
        locationService.save(location1);
        
        // root admin has access to all locations 
        Staff staff1 = new Staff(u1, location1); 
        RoomAdmin roomAdmin1 = new RoomAdmin(u2, location1);
        LocationAdmin locationAdmin1 = new LocationAdmin(u3, location1);
        BusinessAdmin businessAdmin1 = new BusinessAdmin(u0, location1);
        
        
        staffService.save(staff1);
        staffService.save(roomAdmin1);
        staffService.save(locationAdmin1);
        staffService.save(businessAdmin1);
	}
}