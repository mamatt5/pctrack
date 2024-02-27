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
		        nodejs6, python, npm, npm1, npm2, npm3, npm4, npm5, npm6, sql8wb, sqlShell, powerBi, excel, excel1,
		        microsoftSSMS, pnpm, git, jdk);
		programService.saveAll(programs);

		// bondi rooms
		Computer c1 = new Computer(15040);
		computerService.save(c1);
		c1.setProgramList(Arrays.asList(vscode, eclipse, nodejs, python, npm, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk));
		
		Computer c2 = new Computer(70156);
		computerService.save(c2);
		c2.setProgramList(Arrays.asList(vscode, eclipse, nodejs1, python, npm1, excel, microsoftSSMS, git, jdk));

		Computer c3 = new Computer(15046);
		computerService.save(c3);
		c3.setProgramList(Arrays.asList(vscode, eclipse, nodejs, python, npm, sql8wb, sqlShell, excel1, microsoftSSMS, git, jdk));

		Computer c4 = new Computer(15068);
		computerService.save(c4);
		c4.setProgramList(Arrays.asList(vscode, eclipse, nodejs1, python, npm2, excel, microsoftSSMS, git, jdk));

		Computer c5 = new Computer(15048);
		computerService.save(c5);
		c5.setProgramList(Arrays.asList(vscode, eclipse, nodejs, python, npm2, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk));

		Computer c6 = new Computer(15052);
		computerService.save(c6);

		Computer c7 = new Computer(15036);
		computerService.save(c7);
		c7.setProgramList(Arrays.asList(vscode, eclipse, nodejs2, python, npm2, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk));

		Computer c8 = new Computer(70101);
		computerService.save(c8);
		c8.setProgramList(Arrays.asList(vscode, eclipse, nodejs2, python, npm2, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk));

		Computer c9 = new Computer(70108);
		computerService.save(c9);

		Computer c10 = new Computer(15054);
		computerService.save(c10);
		c10.setProgramList(Arrays.asList(vscode, eclipse, nodejs2, python, npm3, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk));

		Computer c11 = new Computer(15037);
		computerService.save(c11);
		c11.setProgramList(Arrays.asList(vscode, eclipse, nodejs3, python, npm, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk));

		Computer c12 = new Computer(70104);
		computerService.save(c12);
		c12.setProgramList(Arrays.asList(vscode, eclipse, nodejs4, python, npm4, excel, microsoftSSMS, git, jdk));

		Computer c13 = new Computer(15038);
		computerService.save(c13);
		c13.setProgramList(Arrays.asList(vscode, eclipse, nodejs5, python, npm5, sql8wb, sqlShell, excel1, microsoftSSMS, git, jdk));

		Computer c14 = new Computer(70160);
		computerService.save(c14);

		Computer c15 = new Computer(15070);
		computerService.save(c15);


		// coogee
		Computer c16 = new Computer(15030);
		computerService.save(c16);
		c16.setProgramList(Arrays.asList(vscode, eclipse, nodejs2, python, npm, excel, microsoftSSMS, git, jdk));

		Computer c17 = new Computer(70213);
		computerService.save(c17);

		Computer c18 = new Computer(70161);
		computerService.save(c18);
		c18.setProgramList(Arrays.asList(vscode, eclipse, nodejs6, python, npm6, excel, microsoftSSMS, git, jdk));

		Computer c19 = new Computer(70098);
		computerService.save(c19);

		Computer c20 = new Computer(70093);
		computerService.save(c20);

		Computer c21 = new Computer(15035);
		computerService.save(c21);

		Computer c22 = new Computer(15064);
		computerService.save(c22);

		Computer c23 = new Computer(70106);
		computerService.save(c23);

		Computer c24 = new Computer(70162);
		computerService.save(c24);


		// balmoral
		Computer c25 = new Computer(70215);
		computerService.save(c25);
		c25.setProgramList(Arrays.asList(vscode, eclipse, python, npm, excel, microsoftSSMS, git, jdk));

		Computer c26 = new Computer(70111);
		computerService.save(c26);
		c26.setProgramList(Arrays.asList(vscode, eclipse, python, npm, excel, microsoftSSMS, git, jdk));

		Computer c27 = new Computer(15042);
		computerService.save(c27);

		Computer c28 = new Computer(15033);
		computerService.save(c28);
		c28.setProgramList(Arrays.asList(vscode, eclipse, python, npm2, excel, microsoftSSMS, git, jdk));

		Computer c29 = new Computer(70163);
		computerService.save(c29);
		c29.setProgramList(Arrays.asList(vscode, eclipse, python, npm3, excel, microsoftSSMS, git, jdk));

		Computer c30 = new Computer(15066);
		computerService.save(c30);
		c30.setProgramList(Arrays.asList(vscode, eclipse, python, npm3, excel, microsoftSSMS, git, jdk));

		Computer c31 = new Computer(15032);
		computerService.save(c31);

		Computer c32 = new Computer(70159);
		computerService.save(c32);

		Computer c33 = new Computer(15031);
		computerService.save(c33);


		// bronte
		Computer c34 = new Computer(70196);
		computerService.save(c34);
		c34.setProgramList(Arrays.asList(vscode, nodejs2, python, npm2, excel, microsoftSSMS, git, jdk));

		Computer c35 = new Computer(70204);
		computerService.save(c35);
		c35.setProgramList(Arrays.asList(vscode, eclipse, nodejs2, python, npm2, excel, microsoftSSMS, git, jdk));

		Computer c36 = new Computer(70095);
		computerService.save(c36);
		c36.setProgramList(Arrays.asList(vscode, eclipse, nodejs2, python, npm3, excel, microsoftSSMS, git, jdk));

		Computer c37 = new Computer(70207);
		computerService.save(c37);
		c37.setProgramList(Arrays.asList(vscode, nodejs2, python, npm1, excel, microsoftSSMS, git, jdk));

		Computer c38 = new Computer(70198);
		computerService.save(c38);

		Computer c39 = new Computer(15056);
		computerService.save(c39);

		Computer c40 = new Computer(70210);
		computerService.save(c40);

		Computer c41 = new Computer(70212);
		computerService.save(c41);


		// tamarama
		Computer c42 = new Computer(15059);
		computerService.save(c42);
		c42.setProgramList(Arrays.asList(vscode, eclipse, nodejs1, python, npm2, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk, powerBi));

		Computer c43 = new Computer(15091);
		computerService.save(c43);
		c43.setProgramList(Arrays.asList(vscode, eclipse, nodejs2, python, npm3, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk, powerBi));

		Computer c44 = new Computer(70263);
		computerService.save(c44);
		c44.setProgramList(Arrays.asList(vscode, eclipse, nodejs1, python, npm, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk, powerBi));

		Computer c45 = new Computer(70272);
		computerService.save(c45);
		c45.setProgramList(Arrays.asList(vscode, eclipse, nodejs2, python, npm, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk, powerBi));

		Computer c46 = new Computer(70613);
		computerService.save(c46);
		c46.setProgramList(Arrays.asList(vscode, eclipse, nodejs3, python, npm, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk, powerBi));

		Computer c47 = new Computer(70707);
		computerService.save(c47);
		c47.setProgramList(Arrays.asList(vscode, eclipse, nodejs1, python, npm2, excel, microsoftSSMS, git, jdk));

		Computer c48 = new Computer(70898);
		computerService.save(c48);
		c48.setProgramList(Arrays.asList(vscode, eclipse, nodejs1, python, npm2, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk, powerBi));

		Computer c49 = new Computer(15656);
		computerService.save(c49);
		c49.setProgramList(Arrays.asList(vscode, eclipse, nodejs4, python, npm3, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk));

		Computer c50 = new Computer(70410);
		computerService.save(c50);
		c50.setProgramList(Arrays.asList(vscode, eclipse, nodejs1, python, npm, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk));

		Computer c51 = new Computer(70412);
		computerService.save(c51);
		c51.setProgramList(Arrays.asList(vscode, eclipse, nodejs2, python, npm2, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk, powerBi));

		Computer c52 = new Computer(15231);
		computerService.save(c52);
		c52.setProgramList(Arrays.asList(vscode, eclipse, nodejs2, python, npm, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk));

		Computer c53 = new Computer(72196);
		computerService.save(c53);
		c53.setProgramList(Arrays.asList(vscode, eclipse, nodejs1, python, npm3, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk, powerBi));

		Computer c54 = new Computer(72210);
		computerService.save(c54);
		c54.setProgramList(Arrays.asList(vscode, eclipse, nodejs3, python, npm3, excel, microsoftSSMS, git, jdk, powerBi));

		Computer c55 = new Computer(72212);
		computerService.save(c55);
		c55.setProgramList(Arrays.asList(vscode, eclipse, nodejs1, python, npm2, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk));

		Computer c56 = new Computer(15091);
		computerService.save(c56);
		c56.setProgramList(Arrays.asList(vscode, eclipse, nodejs1, python, npm, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk, powerBi));

		Computer c57 = new Computer(72196);
		computerService.save(c57);
		c57.setProgramList(Arrays.asList(vscode, eclipse, nodejs6, python, npm, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk, powerBi));


//		computerService.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16,
//		        c17, c18, c19, c20, c21, c22, c23, c24, c25, c26, c27, c28, c29, c30, c31, c32, c33, c34, c35, c36, c37,
//		        c38, c39, c40, c41, c42, c43, c44, c45, c46, c47, c48, c49, c50, c51, c52, c53, c54, c55, c56, c57));
        
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
        
        
        // sample data for inheritance, pls delete Jenny
        Location location1 = new Location("FDM Sydney", "Sydney");
        locationService.save(location1);
        
        Staff staff1 = new Staff(u1, location1);
        RoomAdmin roomAdmin1 = new RoomAdmin(u2, location1);
        LocationAdmin locationAdmin1 = new LocationAdmin(u3, location1);
        BusinessAdmin businessAdmin1 = new BusinessAdmin(u4, location1);
        
        staffService.save(staff1);
        staffService.save(roomAdmin1);
        staffService.save(locationAdmin1);
        staffService.save(businessAdmin1);
	}
}