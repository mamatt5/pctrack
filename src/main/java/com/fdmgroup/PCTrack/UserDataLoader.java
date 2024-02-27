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
	private RoomService roomService;
	
	@Autowired
	public UserDataLoader(UserService userService, LocationService locationService, StaffService staffService,
			ComputerService computerService, ProgramService programService, RoomService roomService) {
		super();
		this.userService = userService;
		this.locationService = locationService;
		this.staffService = staffService;
		this.computerService = computerService;
		this.programService = programService;
		this.roomService = roomService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		// Locations
        Location location1 = new Location("FDM Sydney", "Sydney");
        Location location2 = new Location("FDM Hongkong", "Hong Kong");
        Location location3 = new Location("FDM Singapore", "Singapore");
        
        List<Location> locations = Arrays.asList(location1, location2, location3);
        
        for (Location location : locations) {
        	locationService.save(location);
        }
        
        // Rooms
        Room room1 = new Room("Bondi", location1);
        Room room2 = new Room("Coogee", location1);
        Room room3 = new Room("Balmoral",location1);
        Room room4 = new Room("Bronte",location1);
        Room room5 = new Room("Tamarama",location1);
        Room room6 = new Room("Aberdeen", location2);
        Room room7 = new Room("Lantau", location2);
        Room room8 = new Room("Stanley", location2);
        Room room9 = new Room("Causeway Bay", location2);
        Room room10 = new Room("Raffles Place", location3);
        Room room11 = new Room("Clarke Quay", location3);
        Room room12 = new Room("Sentosa", location3);
        
        List<Room> rooms = Arrays.asList(room1, room2, room3, room4, room5, room6, room7, room8, room9);
        
        for (Room room : rooms) {
        	roomService.save(room);
        }
        
        // Programs
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
		Computer c1 = new Computer(15040, room1);
		computerService.save(c1);
		c1.setProgramList(Arrays.asList(vscode, eclipse, nodejs, python, npm, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk));
		computerService.update(c1);
		
		Computer c2 = new Computer(70156, room1);
		computerService.save(c2);
		c2.setProgramList(Arrays.asList(vscode, eclipse, nodejs1, python, npm1, excel, microsoftSSMS, git, jdk));
		computerService.update(c2);

		Computer c3 = new Computer(15046, room1);
		computerService.save(c3);
		c3.setProgramList(Arrays.asList(vscode, eclipse, nodejs, python, npm, sql8wb, sqlShell, excel1, microsoftSSMS, git, jdk));
		computerService.update(c3);

		Computer c4 = new Computer(15068, room1);
		computerService.save(c4);
		c4.setProgramList(Arrays.asList(vscode, eclipse, nodejs1, python, npm2, excel, microsoftSSMS, git, jdk));
		computerService.update(c4);

		Computer c5 = new Computer(15048, room1);
		computerService.save(c5);
		c5.setProgramList(Arrays.asList(vscode, eclipse, nodejs, python, npm2, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk));
		computerService.update(c5);

		Computer c6 = new Computer(15052, room1);
		computerService.save(c6);

		Computer c7 = new Computer(15036, room1);
		computerService.save(c7);
		c7.setProgramList(Arrays.asList(vscode, eclipse, nodejs2, python, npm2, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk));
		computerService.update(c7);

		Computer c8 = new Computer(70101, room1);
		computerService.save(c8);
		c8.setProgramList(Arrays.asList(vscode, eclipse, nodejs2, python, npm2, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk));
		computerService.update(c8);

		Computer c9 = new Computer(70108, room1);
		computerService.save(c9);

		Computer c10 = new Computer(15054, room1);
		computerService.save(c10);
		c10.setProgramList(Arrays.asList(vscode, eclipse, nodejs2, python, npm3, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk));
		computerService.update(c10);

		Computer c11 = new Computer(15037, room1);
		computerService.save(c11);
		c11.setProgramList(Arrays.asList(vscode, eclipse, nodejs3, python, npm, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk));
		computerService.update(c11);

		Computer c12 = new Computer(70104, room1);
		computerService.save(c12);
		c12.setProgramList(Arrays.asList(vscode, eclipse, nodejs4, python, npm4, excel, microsoftSSMS, git, jdk));
		computerService.update(c12);

		Computer c13 = new Computer(15038, room1);
		computerService.save(c13);
		c13.setProgramList(Arrays.asList(vscode, eclipse, nodejs5, python, npm5, sql8wb, sqlShell, excel1, microsoftSSMS, git, jdk));
		computerService.update(c13);

		Computer c14 = new Computer(70160, room1);
		computerService.save(c14);

		Computer c15 = new Computer(15070, room1);
		computerService.save(c15);



		// coogee
		Computer c16 = new Computer(15030, room2);
		computerService.save(c16);
		c16.setProgramList(Arrays.asList(vscode, eclipse, nodejs2, python, npm, excel, microsoftSSMS, git, jdk));
		computerService.update(c16);

		Computer c17 = new Computer(70213, room2);
		computerService.save(c17);

		Computer c18 = new Computer(70161, room2);
		computerService.save(c18);
		c18.setProgramList(Arrays.asList(vscode, eclipse, nodejs6, python, npm6, excel, microsoftSSMS, git, jdk));
		computerService.update(c18);

		Computer c19 = new Computer(70098, room2);
		computerService.save(c19);

		Computer c20 = new Computer(70093, room2);
		computerService.save(c20);

		Computer c21 = new Computer(15035, room2);
		computerService.save(c21);

		Computer c22 = new Computer(15064, room2);
		computerService.save(c22);

		Computer c23 = new Computer(70106, room2);
		computerService.save(c23);

		Computer c24 = new Computer(70162, room2);
		computerService.save(c24);


		// balmoral
		Computer c25 = new Computer(70215, room3);
		computerService.save(c25);
		c25.setProgramList(Arrays.asList(vscode, eclipse, python, npm, excel, microsoftSSMS, git, jdk));
		computerService.update(c25);

		Computer c26 = new Computer(70111, room3);
		computerService.save(c26);
		c26.setProgramList(Arrays.asList(vscode, eclipse, python, npm, excel, microsoftSSMS, git, jdk));
		computerService.update(c26);

		Computer c27 = new Computer(15042, room3);
		computerService.save(c27);

		Computer c28 = new Computer(15033, room3);
		computerService.save(c28);
		c28.setProgramList(Arrays.asList(vscode, eclipse, python, npm2, excel, microsoftSSMS, git, jdk));
		computerService.update(c28);

		Computer c29 = new Computer(70163, room3);
		computerService.save(c29);
		c29.setProgramList(Arrays.asList(vscode, eclipse, python, npm3, excel, microsoftSSMS, git, jdk));
		computerService.update(c29);

		Computer c30 = new Computer(15066, room3);
		computerService.save(c30);
		c30.setProgramList(Arrays.asList(vscode, eclipse, python, npm3, excel, microsoftSSMS, git, jdk));
		computerService.update(c30);

		Computer c31 = new Computer(15032, room3);
		computerService.save(c31);

		Computer c32 = new Computer(70159, room3);
		computerService.save(c32);

		Computer c33 = new Computer(15031, room3);
		computerService.save(c33);


		// bronte
		Computer c34 = new Computer(70196, room4);
		computerService.save(c34);
		c34.setProgramList(Arrays.asList(vscode, nodejs2, python, npm2, excel, microsoftSSMS, git, jdk));
		computerService.update(c34);

		Computer c35 = new Computer(70204, room4);
		computerService.save(c35);
		c35.setProgramList(Arrays.asList(vscode, eclipse, nodejs2, python, npm2, excel, microsoftSSMS, git, jdk));
		computerService.update(c35);

		Computer c36 = new Computer(70095, room4);
		computerService.save(c36);
		c36.setProgramList(Arrays.asList(vscode, eclipse, nodejs2, python, npm3, excel, microsoftSSMS, git, jdk));
		computerService.update(c36);

		Computer c37 = new Computer(70207, room4);
		computerService.save(c37);
		c37.setProgramList(Arrays.asList(vscode, nodejs2, python, npm1, excel, microsoftSSMS, git, jdk));
		computerService.update(c37);

		Computer c38 = new Computer(70198, room4);
		computerService.save(c38);

		Computer c39 = new Computer(15056, room4);
		computerService.save(c39);

		Computer c40 = new Computer(70210, room4);
		computerService.save(c40);

		Computer c41 = new Computer(70212, room4);
		computerService.save(c41);


		// tamarama
		Computer c42 = new Computer(15059, room5);
		computerService.save(c42);
		c42.setProgramList(Arrays.asList(vscode, eclipse, nodejs1, python, npm2, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk, powerBi));
		computerService.update(c42);

		Computer c43 = new Computer(15091, room5);
		computerService.save(c43);
		c43.setProgramList(Arrays.asList(vscode, eclipse, nodejs2, python, npm3, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk, powerBi));
		computerService.update(c43);

		Computer c44 = new Computer(70263, room5);
		computerService.save(c44);
		c44.setProgramList(Arrays.asList(vscode, eclipse, nodejs1, python, npm, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk, powerBi));
		computerService.update(c44);

		Computer c45 = new Computer(70272, room5);
		computerService.save(c45);
		c45.setProgramList(Arrays.asList(vscode, eclipse, nodejs2, python, npm, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk, powerBi));
		computerService.update(c45);

		Computer c46 = new Computer(70613, room5);
		computerService.save(c46);
		c46.setProgramList(Arrays.asList(vscode, eclipse, nodejs3, python, npm, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk, powerBi));
		computerService.update(c46);

		Computer c47 = new Computer(70707, room5);
		computerService.save(c47);
		c47.setProgramList(Arrays.asList(vscode, eclipse, nodejs1, python, npm2, excel, microsoftSSMS, git, jdk));
		computerService.update(c47);

		Computer c48 = new Computer(70898, room5);
		computerService.save(c48);
		c48.setProgramList(Arrays.asList(vscode, eclipse, nodejs1, python, npm2, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk, powerBi));
		computerService.update(c48);

		Computer c49 = new Computer(15656, room5);
		computerService.save(c49);
		c49.setProgramList(Arrays.asList(vscode, eclipse, nodejs4, python, npm3, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk));
		computerService.update(c49);

		Computer c50 = new Computer(70410, room5);
		computerService.save(c50);
		c50.setProgramList(Arrays.asList(vscode, eclipse, nodejs1, python, npm, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk));
		computerService.update(c50);

		Computer c51 = new Computer(70412, room5);
		computerService.save(c51);
		c51.setProgramList(Arrays.asList(vscode, eclipse, nodejs2, python, npm2, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk, powerBi));
		computerService.update(c51);

		Computer c52 = new Computer(15231, room5);
		computerService.save(c52);
		c52.setProgramList(Arrays.asList(vscode, eclipse, nodejs2, python, npm, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk));
		computerService.update(c52);

		Computer c53 = new Computer(72196, room5);
		computerService.save(c53);
		c53.setProgramList(Arrays.asList(vscode, eclipse, nodejs1, python, npm3, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk, powerBi));
		computerService.update(c53);

		Computer c54 = new Computer(72210, room5);
		computerService.save(c54);
		c54.setProgramList(Arrays.asList(vscode, eclipse, nodejs3, python, npm3, excel, microsoftSSMS, git, jdk, powerBi));
		computerService.update(c54);

		Computer c55 = new Computer(72212, room5);
		computerService.save(c55);
		c55.setProgramList(Arrays.asList(vscode, eclipse, nodejs1, python, npm2, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk));
		computerService.update(c55);

		Computer c56 = new Computer(15091, room5);
		computerService.save(c56);
		c56.setProgramList(Arrays.asList(vscode, eclipse, nodejs1, python, npm, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk, powerBi));
		computerService.update(c56);

		Computer c57 = new Computer(72196, room5);
		computerService.save(c57);
		c57.setProgramList(Arrays.asList(vscode, eclipse, nodejs6, python, npm, sql8wb, sqlShell, excel, microsoftSSMS, git, jdk, powerBi));
		computerService.update(c57);


//		computerService.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16,
//		        c17, c18, c19, c20, c21, c22, c23, c24, c25, c26, c27, c28, c29, c30, c31, c32, c33, c34, c35, c36, c37,
//		        c38, c39, c40, c41, c42, c43, c44, c45, c46, c47, c48, c49, c50, c51, c52, c53, c54, c55, c56, c57));
        
		
		User u0 = new User("admin1", "0000!!", "Root", "Admin");
        User u1 = new User("andy.joe", "password123", "Andy", "Joe");
        User u2 = new User("ahri.foxian", "password123", "Ahri", "Foxian");
        User u3 = new User("aatrox.damion", "password123", "Aatrox", "Damion");
        User u4 = new User("amumu.munsen", "password123", "Amumu", "Munsen", LocalDate.of(2023, 11, 27));
        User u5 = new User("blitzcrank.botter", "password123", "Blitzcrank", "Botter", LocalDate.of(2023, 11, 27));
        User u6 = new User("baron.nashor", "password123", "Baron", "Nashor", LocalDate.of(2023, 11, 27));
        User u7 = new User("darwin.norman", "password123", "Darwin", "Norman", LocalDate.of(2023, 11, 27));
        User u8 = new User("camden.leonard", "password123", "Camden", "Leonard", LocalDate.of(2023, 11, 27));
        User u9 = new User("quincy.jarvis", "password123", "Quincy", "Jarvis", LocalDate.of(2023, 11, 27));
        User u10 = new User("chaim.harrison", "password123", "Chaim", "Harrison", LocalDate.of(2023, 11, 27));
        
        User u11 = new User("ryan.wilson", "password123", "Ryan", "Wilson", LocalDate.of(2023, 11, 27));
    	User u12 = new User("tejasva.saboo", "password123", "Tejasva", "Saboo", LocalDate.of(2023, 11, 27));
    	User u13 = new User("donald.witcombe", "password123", "Donald", "Witcombe", LocalDate.of(2023, 11, 27));
    	User u14 = new User("flor.crencic", "password123", "Florencia", "Crencic", LocalDate.of(2023, 11, 27));
    	User u15 = new User("james.mccarthy", "password123", "James", "McCarthy", LocalDate.of(2023, 11, 27));
    	User u16 = new User("dan.solomon", "password123", "Dan", "Solomon", LocalDate.of(2023, 11, 27));
    	User u17 = new User("chris.spencer", "password123", "Chris", "Spencer", LocalDate.of(2023, 11, 27));
    	User u18 = new User("carolina.portugal", "password123", "Carolina", "Portugal", LocalDate.of(2023, 11, 27));
    	User u19 = new User("alex.zlatevska", "password123", "Aleksandra", "Zlatevska", LocalDate.of(2023, 11, 27));
    	User u20 = new User("joe.mclaren", "password123", "Joe", "Mclaren", LocalDate.of(2023, 11, 27));
    	User u21 = new User("rod.flavell", "password123", "Rod", "Flavell", LocalDate.of(2023, 11, 27));
        
    	List<User> users = Arrays.asList(u0, u1, u2, u3, u4, u5, u6, u7, u8, u9, u10, u11, u12, u13, u14, u15, u16, u17, u18, u19, u20, u21);
    	
    	for (User user : users) {
    		userService.register(user);
    	}
        
    	// Business admin
    	BusinessAdmin businessAdmin = new BusinessAdmin(u21, location1);
    	staffService.save(businessAdmin);
    	
    	// Location admin
    	LocationAdmin locationAdmin1 = new LocationAdmin(u20, location1);
    	LocationAdmin locationAdmin2 = new LocationAdmin(u17, location1);
    	LocationAdmin locationAdmin3 = new LocationAdmin(u6, location2);
    	LocationAdmin locationAdmin4 = new LocationAdmin(u7, location2);
    	LocationAdmin locationAdmin5 = new LocationAdmin(u8, location3);
    	
    	staffService.save(locationAdmin1);
    	staffService.save(locationAdmin2);
    	staffService.save(locationAdmin3);
    	staffService.save(locationAdmin4);
    	staffService.save(locationAdmin5);
    	
    	// Room admin
    	RoomAdmin roomAdmin1 = new RoomAdmin(u14, location1);
    	RoomAdmin roomAdmin2 = new RoomAdmin(u15, location1);
    	RoomAdmin roomAdmin3 = new RoomAdmin(u1, location2);
    	RoomAdmin roomAdmin4 = new RoomAdmin(u2, location3);
    	RoomAdmin roomAdmin5 = new RoomAdmin(u3, location3);
    	
    	staffService.save(roomAdmin1);
    	staffService.save(roomAdmin2);
    	staffService.save(roomAdmin3);
    	staffService.save(roomAdmin4);
    	staffService.save(roomAdmin5);
    	
    	
    	// Staff
    	Staff staff1 = new Staff(u9, location1);
    	Staff staff2 = new Staff(u10, location1);
    	Staff staff3 = new Staff(u11, location1);
    	Staff staff4 = new Staff(u12, location1);
    	Staff staff5 = new Staff(u13, location1);
    	Staff staff6 = new Staff(u16, location1);
    	
    	staffService.save(staff1);
    	staffService.save(staff2);
    	staffService.save(staff3);
    	staffService.save(staff4);
    	staffService.save(staff5);
    	staffService.save(staff6);

	
        
//        // sample data for inheritance, pls delete Jenny
//        Location location1 = new Location("FDM Sydney", "Sydney");
//        locationService.save(location1);
//        
//        Staff staff1 = new Staff(u1, location1);
//        RoomAdmin roomAdmin1 = new RoomAdmin(u2, location1);
//        LocationAdmin locationAdmin1 = new LocationAdmin(u3, location1);
//        BusinessAdmin businessAdmin1 = new BusinessAdmin(u4, location1);
//        
//        staffService.save(staff1);
//        staffService.save(roomAdmin1);
//        staffService.save(locationAdmin1);
//        staffService.save(businessAdmin1);
	}
}