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
	private SoftwareService programService;
	private RoomService roomService;
	private ProgramService programVersionService;
	private MandateService mandateService;
	private AdminLevelService adminService;
	private RoomAdminService roomAdminService;
	private ReportService reportService;

	@Autowired
	public UserDataLoader(UserService userService, LocationService locationService, StaffService staffService,
			AdminLevelService adminService, ComputerService computerService, SoftwareService programService,
			RoomService roomService, MandateService mandateService, ProgramService programVersionService,
			RoomAdminService roomAdminService, ReportService reportService)

	{
		super();
		this.userService = userService;
		this.locationService = locationService;
		this.staffService = staffService;
		this.computerService = computerService;
		this.programService = programService;
		this.roomService = roomService;
		this.mandateService = mandateService;
		this.adminService = adminService;
		this.programVersionService = programVersionService;
		this.roomAdminService = roomAdminService;
		this.reportService = reportService;
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

		// Admin
		// perms = 100 mean no perms
		// otherwise perms 1 means highest, 2 second highest etc....
		AdminLevel Business = new AdminLevel("Business", 1);
		AdminLevel Location = new AdminLevel("Location", 2);
		AdminLevel Room = new AdminLevel("Room", 3);
		AdminLevel User = new AdminLevel("", 100);

		List<AdminLevel> adminLevels = Arrays.asList(Location, Business, Room, User);

		for (AdminLevel adminLevel : adminLevels) {
			adminService.save(adminLevel);
		}  

		// Rooms
		Room room1 = new Room("Bondi", location1);
		Room room2 = new Room("Coogee", location1);
		Room room3 = new Room("Balmoral", location1);
		Room room4 = new Room("Bronte", location1);
		Room room5 = new Room("Tamarama", location1);
		Room room6 = new Room("Aberdeen", location2);
		Room room7 = new Room("Lantau", location2);
		Room room8 = new Room("Stanley", location2);
		Room room9 = new Room("Causeway Bay", location2);
		Room room10 = new Room("Raffles Place", location3);
		Room room11 = new Room("Clarke Quay", location3);
		Room room12 = new Room("Sentosa", location3);

		List<Room> rooms = Arrays.asList(room1, room2, room3, room4, room5, room6, room7, room8, room9, room10, room11,
				room12);

		for (Room room : rooms) {
			roomService.save(room);
		}

		// Programs
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

		List<Software> softwares = Arrays.asList(vscode, eclipse, nodejs, python, npm, sql8wb, sqlShell, powerBi, excel,
				microsoftSSMS, pnpm, git, jdk, visualStudio);
		for (Software software : softwares) {
			programService.save(software);
		}

		// Program Versions
		Program vscodeV1 = new Program(vscode, "1.46.1");
		Program eclipseV1 = new Program(eclipse, "4.22");
		Program nodejsV1 = new Program(nodejs, "20.11.0");
		Program nodejsV2 = new Program(nodejs, "20.10.0");
		Program nodejsV3 = new Program(nodejs, "12.18.1");
		Program nodejsV4 = new Program(nodejs, "20.2.0");
		Program nodejsV5 = new Program(nodejs, "20.5");
		Program nodejsV6 = new Program(nodejs, "20.9");
		Program nodejsV7 = new Program(nodejs, "20.1.0");
		Program pythonV1 = new Program(python, "3.9.7427");
		Program npmV1 = new Program(npm, "10.2.4");
		Program npmV2 = new Program(npm, "10.2.3");
		Program npmV3 = new Program(npm, "6.14.5");
		Program npmV4 = new Program(npm, "9.14.5");
		Program npmV5 = new Program(npm, "9.6.6");
		Program npmV6 = new Program(npm, "10.1.0");
		Program npmV7 = new Program(npm, "9.6.4");
		Program sql8wbV1 = new Program(sql8wb, "8.0.32");
		Program sqlShellV1 = new Program(sqlShell, "8.0.32");
		Program powerBiV1 = new Program(powerBi, "2.126.927.0");
		Program excelV1 = new Program(excel, "2107");
		Program excelV2 = new Program(excel, "2208");
		Program microsoftSSMSV1 = new Program(microsoftSSMS, "15.0.18333.0");
		Program pnpmV1 = new Program(pnpm, "8.15.4");
		Program gitV1 = new Program(git, "2.27.0");
		Program jdkV1 = new Program(jdk, "17.0.2");
		Program visualStudioV1 = new Program(visualStudio, "1.87.0");

		List<Program> programsList = Arrays.asList(vscodeV1, eclipseV1, nodejsV1, nodejsV2, nodejsV3, nodejsV4,
				nodejsV5, nodejsV6, nodejsV7, pythonV1, npmV1, npmV2, npmV3, npmV4, npmV5, npmV6, npmV7, sql8wbV1,
				sqlShellV1, powerBiV1, excelV1, excelV2, microsoftSSMSV1, pnpmV1, gitV1, jdkV1, visualStudioV1);
		for (Program pv : programsList) {
			programVersionService.save(pv);
		}

		// SYD bondi
		Computer c1 = new Computer(15040, room1);
		computerService.save(c1);
		c1.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV1, pythonV1, npmV1, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1, visualStudioV1));
		computerService.update(c1);

		Computer c2 = new Computer(70156, room1);
		computerService.save(c2);
		c2.setProgramList(
				Arrays.asList(vscodeV1, eclipseV1, nodejsV2, pythonV1, npmV2, excelV1, microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c2);

		Computer c3 = new Computer(15046, room1);
		computerService.save(c3);
		c3.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV1, pythonV1, npmV1, sql8wbV1, sqlShellV1, excelV2,
				microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c3);

		Computer c4 = new Computer(15068, room1);
		computerService.save(c4);
		c4.setProgramList(
				Arrays.asList(vscodeV1, eclipseV1, nodejsV2, pythonV1, npmV3, excelV1, microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c4);

		Computer c5 = new Computer(15048, room1);
		computerService.save(c5);
		c5.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV1, pythonV1, npmV3, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c5);

		Computer c6 = new Computer(15052, room1);
		computerService.save(c6);

		Computer c7 = new Computer(15036, room1);
		computerService.save(c7);
		c7.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV3, pythonV1, npmV3, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c7);

		Computer c8 = new Computer(70101, room1);
		computerService.save(c8);
		c8.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV3, pythonV1, npmV3, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c8);

		Computer c9 = new Computer(70108, room1);
		computerService.save(c9);

		Computer c10 = new Computer(15054, room1);
		computerService.save(c10);
		c10.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV3, pythonV1, npmV4, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c10);

		Computer c11 = new Computer(15037, room1);
		computerService.save(c11);
		c11.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV4, pythonV1, npmV1, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c11);

		Computer c12 = new Computer(70104, room1);
		computerService.save(c12);
		c12.setProgramList(
				Arrays.asList(vscodeV1, eclipseV1, nodejsV5, pythonV1, npmV5, excelV1, microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c12);

		Computer c13 = new Computer(15038, room1);
		computerService.save(c13);
		c13.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV6, pythonV1, npmV6, sql8wbV1, sqlShellV1, excelV2,
				microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c13);

		Computer c14 = new Computer(70160, room1);
		computerService.save(c14);

		Computer c15 = new Computer(15070, room1);
		computerService.save(c15);

		// SYD coogee
		Computer c16 = new Computer(15030, room2);
		computerService.save(c16);
		c16.setProgramList(
				Arrays.asList(vscodeV1, eclipseV1, nodejsV3, pythonV1, npmV1, excelV1, microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c16);

		Computer c17 = new Computer(70213, room2);
		computerService.save(c17);

		Computer c18 = new Computer(70161, room2);
		computerService.save(c18);
		c18.setProgramList(
				Arrays.asList(vscodeV1, eclipseV1, nodejsV7, pythonV1, npmV7, excelV1, microsoftSSMSV1, gitV1, jdkV1));
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

		// SYD balmoral
		Computer c25 = new Computer(70215, room3);
		computerService.save(c25);
		c25.setProgramList(Arrays.asList(vscodeV1, eclipseV1, pythonV1, npmV1, excelV1, microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c25);

		Computer c26 = new Computer(70111, room3);
		computerService.save(c26);
		c26.setProgramList(Arrays.asList(vscodeV1, eclipseV1, pythonV1, npmV1, excelV1, microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c26);

		Computer c27 = new Computer(15042, room3);
		computerService.save(c27);

		Computer c28 = new Computer(15033, room3);
		computerService.save(c28);
		c28.setProgramList(Arrays.asList(vscodeV1, eclipseV1, pythonV1, npmV3, excelV1, microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c28);

		Computer c29 = new Computer(70163, room3);
		computerService.save(c29);
		c29.setProgramList(Arrays.asList(vscodeV1, eclipseV1, pythonV1, npmV4, excelV1, microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c29);

		Computer c30 = new Computer(15066, room3);
		computerService.save(c30);

		c30.setProgramList(Arrays.asList(vscodeV1, eclipseV1, pythonV1, npmV4, excelV1, microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c30);

		Computer c31 = new Computer(15032, room3);
		computerService.save(c31);

		Computer c32 = new Computer(70159, room3);
		computerService.save(c32);

		Computer c33 = new Computer(15031, room3);
		computerService.save(c33);

		// SYD bronte
		Computer c34 = new Computer(70196, room4);
		computerService.save(c34);
		c34.setProgramList(Arrays.asList(vscodeV1, nodejsV3, pythonV1, npmV3, excelV1, microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c34);

		Computer c35 = new Computer(70204, room4);
		computerService.save(c35);
		c35.setProgramList(
				Arrays.asList(vscodeV1, eclipseV1, nodejsV3, pythonV1, npmV3, excelV1, microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c35);

		Computer c36 = new Computer(70095, room4);
		computerService.save(c36);
		c36.setProgramList(
				Arrays.asList(vscodeV1, eclipseV1, nodejsV3, pythonV1, npmV4, excelV1, microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c36);

		Computer c37 = new Computer(70207, room4);
		computerService.save(c37);
		c37.setProgramList(Arrays.asList(vscodeV1, nodejsV3, pythonV1, npmV2, excelV1, microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c37);

		Computer c38 = new Computer(70198, room4);
		computerService.save(c38);

		Computer c39 = new Computer(15056, room4);
		computerService.save(c39);

		Computer c40 = new Computer(70210, room4);
		computerService.save(c40);

		Computer c41 = new Computer(70212, room4);
		computerService.save(c41);

		// SYD tamarama
		Computer c42 = new Computer(15059, room5);
		computerService.save(c42);
		c42.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV2, pythonV1, npmV3, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1, powerBiV1));
		computerService.update(c42);

		Computer c43 = new Computer(15092, room5);
		computerService.save(c43);
		c43.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV3, pythonV1, npmV4, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1, powerBiV1));
		computerService.update(c43);

		Computer c44 = new Computer(70263, room5);
		computerService.save(c44);
		c44.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV7, pythonV1, npmV1, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1, powerBiV1));
		computerService.update(c44);

		Computer c45 = new Computer(70272, room5);
		computerService.save(c45);
		c45.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV3, pythonV1, npmV1, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1, powerBiV1));
		computerService.update(c45);

		Computer c46 = new Computer(70613, room5);
		computerService.save(c46);
		c46.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV4, pythonV1, npmV1, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1, powerBiV1));
		computerService.update(c46);

		Computer c47 = new Computer(70707, room5);
		computerService.save(c47);
		c47.setProgramList(
				Arrays.asList(vscodeV1, eclipseV1, nodejsV2, pythonV1, npmV3, excelV1, microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c47);

		Computer c48 = new Computer(70898, room5);
		computerService.save(c48);
		c48.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV2, pythonV1, npmV3, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1, powerBiV1));
		computerService.update(c48);

		Computer c49 = new Computer(15656, room5);
		computerService.save(c49);
		c49.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV5, pythonV1, npmV4, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c49);

		Computer c50 = new Computer(70410, room5);
		computerService.save(c50);
		c50.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV2, pythonV1, npmV1, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c50);

		Computer c51 = new Computer(70412, room5);
		computerService.save(c51);
		c51.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV3, pythonV1, npmV3, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1, powerBiV1));
		computerService.update(c51);

		Computer c52 = new Computer(15231, room5);
		computerService.save(c52);
		c52.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV3, pythonV1, npmV1, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c52);

		Computer c53 = new Computer(72196, room5);
		computerService.save(c53);
		c53.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV2, pythonV1, npmV4, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1, powerBiV1));
		computerService.update(c53);

		Computer c54 = new Computer(72210, room5);
		computerService.save(c54);
		c54.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV4, pythonV1, npmV4, excelV1, microsoftSSMSV1,
				gitV1, jdkV1, powerBiV1));
		computerService.update(c54);

		Computer c55 = new Computer(72212, room5);
		computerService.save(c55);
		c55.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV5, pythonV1, npmV3, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c55);

		Computer c56 = new Computer(15093, room5);
		computerService.save(c56);
		c56.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV2, pythonV1, npmV1, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1, powerBiV1));
		computerService.update(c56);

		Computer c57 = new Computer(72190, room5);
		computerService.save(c57);
		c57.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV7, pythonV1, npmV1, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1, powerBiV1));
		computerService.update(c57);

		// HK Aberdeen 14 room6
		Computer c58 = new Computer(16028, room6);
		computerService.save(c58);
		c58.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV6, pythonV1, npmV3, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c58);

		Computer c59 = new Computer(16395, room6);
		computerService.save(c59);
		c59.setProgramList(
				Arrays.asList(vscodeV1, eclipseV1, nodejsV3, pythonV1, npmV4, excelV1, microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c59);

		Computer c60 = new Computer(16245, room6);
		computerService.save(c60);
		c60.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV2, pythonV1, npmV4, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c60);

		Computer c61 = new Computer(16532, room6);
		computerService.save(c61);
		c61.setProgramList(
				Arrays.asList(vscodeV1, eclipseV1, nodejsV3, pythonV1, npmV5, excelV1, microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c61);

		Computer c62 = new Computer(16788, room6);
		computerService.save(c62);
		c62.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV4, pythonV1, npmV6, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c62);

		Computer c63 = new Computer(16975, room6);
		computerService.save(c63);
		c63.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV7, pythonV1, npmV7, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c63);

		Computer c64 = new Computer(16031, room6);
		computerService.save(c64);
		c64.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV5, pythonV1, npmV3, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c64);

		Computer c65 = new Computer(16824, room6);
		computerService.save(c65);
		c65.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV2, pythonV1, npmV3, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c65);

		Computer c66 = new Computer(16992, room6);
		computerService.save(c66);
		c66.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV3, pythonV1, npmV4, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1, powerBiV1));
		computerService.update(c66);

		Computer c67 = new Computer(16098, room6);
		computerService.save(c67);
		c67.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV4, pythonV1, npmV4, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1, powerBiV1));
		computerService.update(c67);

		Computer c68 = new Computer(16333, room6);
		computerService.save(c68);
		c68.setProgramList(Arrays.asList(nodejsV2, pythonV1, npmV1, sql8wbV1, sqlShellV1, excelV1, microsoftSSMSV1,
				gitV1, jdkV1, powerBiV1));
		computerService.update(c68);

		Computer c69 = new Computer(16447, room6);
		computerService.save(c69);
		c69.setProgramList(Arrays.asList(nodejsV3, pythonV1, npmV3, sql8wbV1, sqlShellV1, excelV1, microsoftSSMSV1,
				gitV1, jdkV1, powerBiV1));
		computerService.update(c69);

		Computer c70 = new Computer(16654, room6);
		computerService.save(c70);
		c70.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV7, pythonV1, npmV3, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c70);

		Computer c71 = new Computer(16211, room6);
		computerService.save(c71);
		c71.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV3, npmV4, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1, powerBiV1));
		computerService.update(c71);

		// HK Lantau 20 room7
		Computer c72 = new Computer(16875, room7);
		computerService.save(c72);
		c72.setProgramList(Arrays.asList(nodejsV3, pythonV1, npmV4, sql8wbV1, sqlShellV1, excelV1, microsoftSSMSV1,
				gitV1, jdkV1, powerBiV1));
		computerService.update(c72);

		Computer c73 = new Computer(16030, room7);
		computerService.save(c73);
		c73.setProgramList(Arrays.asList(nodejsV2, npmV3, sql8wbV1, sqlShellV1, excelV1, microsoftSSMSV1, gitV1, jdkV1,
				powerBiV1));
		computerService.update(c73);

		Computer c74 = new Computer(16397, room7);
		computerService.save(c74);
		c74.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV3, pythonV1, npmV4, sql8wbV1, sqlShellV1,
				microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c74);

		Computer c75 = new Computer(16248, room7);
		computerService.save(c75);
		c75.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV2, pythonV1, npmV4, sql8wbV1, sqlShellV1, excelV1,
				gitV1, jdkV1, powerBiV1));
		computerService.update(c75);

		Computer c76 = new Computer(16535, room7);
		computerService.save(c76);
		c76.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV6, pythonV1, npmV5, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c76);

		Computer c77 = new Computer(16781, room7);
		computerService.save(c77);

		Computer c78 = new Computer(16979, room7);
		computerService.save(c78);

		Computer c79 = new Computer(16035, room7);
		computerService.save(c79);

		Computer c80 = new Computer(16828, room7);
		computerService.save(c80);
		c80.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV2, pythonV1, npmV3, sql8wbV1, sqlShellV1, excelV1,
				gitV1, jdkV1));
		computerService.update(c80);

		Computer c81 = new Computer(16998, room7);
		computerService.save(c81);
		c81.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV3, pythonV1, npmV4, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1, powerBiV1));
		computerService.update(c81);

		Computer c82 = new Computer(16093, room7);
		computerService.save(c82);
		c82.setProgramList(
				Arrays.asList(vscodeV1, eclipseV1, nodejsV6, pythonV1, npmV6, excelV1, microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c82);

		Computer c83 = new Computer(16338, room7);
		computerService.save(c83);
		c83.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV2, pythonV1, npmV1, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, powerBiV1));
		computerService.update(c83);

		Computer c84 = new Computer(16452, room7);
		computerService.save(c84);

		Computer c85 = new Computer(16659, room7);
		computerService.save(c85);
		c85.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV2, pythonV1, npmV3, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1, powerBiV1));
		computerService.update(c85);

		Computer c86 = new Computer(16214, room7);
		computerService.save(c86);

		Computer c87 = new Computer(16786, room7);
		computerService.save(c87);
		c87.setProgramList(Arrays.asList(vscodeV1, eclipseV1, pythonV1, npmV4, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1, powerBiV1));
		computerService.update(c87);

		Computer c88 = new Computer(16335, room7);
		computerService.save(c88);
		c88.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV3, pythonV1, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1, powerBiV1));
		computerService.update(c88);

		Computer c89 = new Computer(16596, room7);
		computerService.save(c89);
		c89.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV7, pythonV1, npmV7, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1, powerBiV1));
		computerService.update(c89);

		Computer c90 = new Computer(16836, room7);
		computerService.save(c90);
		c90.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV2, pythonV1, npmV3, excelV1, microsoftSSMSV1,
				gitV1, jdkV1, powerBiV1));
		computerService.update(c90);

		Computer c91 = new Computer(16202, room7);
		computerService.save(c91);
		c91.setProgramList(Arrays.asList(nodejsV3, pythonV1, npmV3, sql8wbV1, sqlShellV1, excelV1, microsoftSSMSV1,
				gitV1, jdkV1, powerBiV1));
		computerService.update(c91);

		// HK Stanley 8 room8
		Computer c92 = new Computer(17524, room8);
		computerService.save(c92);
		c92.setProgramList(Arrays.asList(nodejsV2, pythonV1, npmV4, sql8wbV1, sqlShellV1, excelV1, microsoftSSMSV1));
		computerService.update(c92);

		Computer c93 = new Computer(17005, room8);
		computerService.save(c93);
		c93.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV1, pythonV1, npmV1, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, jdkV1));
		computerService.update(c93);

		Computer c94 = new Computer(17002, room8);
		computerService.save(c94);
		c94.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV1, pythonV1, npmV1, sql8wbV1, sqlShellV1, excelV1,
				gitV1, jdkV1));
		computerService.update(c94);

		Computer c95 = new Computer(17325, room8);
		computerService.save(c95);
		c95.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV3, pythonV1, npmV1, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c95);

		Computer c96 = new Computer(17645, room8);
		computerService.save(c96);
		c96.setProgramList(
				Arrays.asList(vscodeV1, eclipseV1, nodejsV7, pythonV1, npmV7, excelV1, microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c96);

		Computer c97 = new Computer(17835, room8);
		computerService.save(c97);

		Computer c98 = new Computer(17952, room8);
		computerService.save(c98);
		c98.setProgramList(Arrays.asList(nodejsV3, pythonV1, sql8wbV1, sqlShellV1, excelV1, microsoftSSMSV1, jdkV1));
		computerService.update(c98);

		Computer c99 = new Computer(18056, room8);
		computerService.save(c99);
		c99.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV6, pythonV1, npmV4, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c99);

		// HK Causeway Bay 12 room9

		Computer c100 = new Computer(19236, room9);
		computerService.save(c100);
		c100.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV1, pythonV1, npmV4, sql8wbV1, sqlShellV1, excelV1,
				gitV1, jdkV1, powerBiV1));
		computerService.update(c100);

		Computer c101 = new Computer(19056, room9);
		computerService.save(c101);
		c101.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV2, pythonV1, npmV1, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, jdkV1, powerBiV1));
		computerService.update(c101);

		Computer c102 = new Computer(19625, room9);
		computerService.save(c102);
		c102.setProgramList(
				Arrays.asList(vscodeV1, eclipseV1, nodejsV3, pythonV1, npmV1, sql8wbV1, sqlShellV1, gitV1, jdkV1));
		computerService.update(c102);

		Computer c103 = new Computer(19123, room9);
		computerService.save(c103);

		Computer c104 = new Computer(19345, room9);
		computerService.save(c104);
		c104.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV4, pythonV1, npmV1, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1, powerBiV1));
		computerService.update(c104);

		Computer c105 = new Computer(19567, room9);
		computerService.save(c105);
		c105.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV1, pythonV1, npmV3, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, jdkV1));
		computerService.update(c105);

		Computer c106 = new Computer(19789, room9);
		computerService.save(c106);

		Computer c107 = new Computer(19912, room9);
		computerService.save(c107);

		Computer c108 = new Computer(20134, room9);
		computerService.save(c108);
		c108.setProgramList(Arrays.asList(vscodeV1, eclipseV1, npmV4, sql8wbV1, sqlShellV1, excelV1, microsoftSSMSV1,
				gitV1, jdkV1));
		computerService.update(c108);

		Computer c109 = new Computer(20356, room9);
		computerService.save(c109);
		c109.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV2, sql8wbV1, sqlShellV1, excelV1, microsoftSSMSV1,
				gitV1, jdkV1, powerBiV1));
		computerService.update(c109);

		Computer c110 = new Computer(20578, room9);
		computerService.save(c110);
		c110.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV3, pythonV1, npmV1, excelV1, microsoftSSMSV1,
				gitV1, jdkV1, powerBiV1));
		computerService.update(c110);

		Computer c111 = new Computer(20791, room9);
		computerService.save(c111);
		c111.setProgramList(
				Arrays.asList(vscodeV1, eclipseV1, nodejsV2, pythonV1, npmV3, excelV1, microsoftSSMSV1, powerBiV1));
		computerService.update(c111);

		// SG Raffles Place 12 room10

		Computer c112 = new Computer(30932, room10);
		computerService.save(c112);
		c112.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV3, pythonV1, npmV3, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c112);

		Computer c113 = new Computer(31154, room10);
		computerService.save(c113);
		c113.setProgramList(
				Arrays.asList(vscodeV1, eclipseV1, nodejsV2, pythonV1, npmV4, excelV1, microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c113);

		Computer c114 = new Computer(31376, room10);
		computerService.save(c114);
		c114.setProgramList(
				Arrays.asList(vscodeV1, eclipseV1, pythonV1, npmV4, excelV1, microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c114);

		Computer c115 = new Computer(31598, room10);
		computerService.save(c115);
		c115.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV4, pythonV1, npmV5, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c115);

		Computer c116 = new Computer(31820, room10);
		computerService.save(c116);
		c116.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV2, pythonV1, npmV5, excelV1, microsoftSSMSV1));
		computerService.update(c116);

		Computer c117 = new Computer(32042, room10);
		computerService.save(c117);
		c117.setProgramList(Arrays.asList(nodejsV4, pythonV1, npmV6, excelV1, microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c117);

		Computer c118 = new Computer(32264, room10);
		computerService.save(c118);
		c118.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV5, pythonV1, npmV6, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c118);

		Computer c119 = new Computer(32486, room10);
		computerService.save(c119);
		c119.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV3, pythonV1, npmV7, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c119);

		Computer c120 = new Computer(32708, room10);
		computerService.save(c120);
		c120.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV4, pythonV1, npmV7, sql8wbV1, sqlShellV1, excelV1,
				gitV1, jdkV1));
		computerService.update(c120);

		Computer c121 = new Computer(32930, room10);
		computerService.save(c121);
		c121.setProgramList(
				Arrays.asList(vscodeV1, eclipseV1, nodejsV6, pythonV1, npmV1, excelV1, microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c121);

		Computer c122 = new Computer(33152, room10);
		computerService.save(c122);
		c122.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV7, pythonV1, npmV1, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1));
		computerService.update(c122);

		Computer c123 = new Computer(33374, room10);
		computerService.save(c123);
		c123.setProgramList(
				Arrays.asList(vscodeV1, eclipseV1, nodejsV1, pythonV1, npmV1, excelV1, microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c123);

		// SG Clarke Quay 12 room11
		Computer c124 = new Computer(33596, room11);
		computerService.save(c124);
		c124.setProgramList(
				Arrays.asList(vscodeV1, eclipseV1, nodejsV2, pythonV1, npmV2, excelV1, microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c124);

		Computer c125 = new Computer(33818, room11);
		computerService.save(c125);
		c125.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV3, pythonV1, npmV3, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c125);

		Computer c126 = new Computer(34040, room11);
		computerService.save(c126);
		c126.setProgramList(Arrays.asList(nodejsV4, pythonV1, npmV4, excelV1, microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c126);

		Computer c127 = new Computer(34262, room11);
		computerService.save(c127);
		c127.setProgramList(
				Arrays.asList(vscodeV1, eclipseV1, nodejsV5, pythonV1, npmV5, sql8wbV1, sqlShellV1, excelV1));
		computerService.update(c127);

		Computer c128 = new Computer(34484, room11);
		computerService.save(c128);
		c128.setProgramList(
				Arrays.asList(vscodeV1, eclipseV1, nodejsV6, pythonV1, npmV6, excelV1, microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c128);

		Computer c129 = new Computer(34706, room11);
		computerService.save(c129);
		c129.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV7, pythonV1, npmV7, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c129);

		Computer c130 = new Computer(34928, room11);
		computerService.save(c130);
		c130.setProgramList(
				Arrays.asList(vscodeV1, eclipseV1, nodejsV3, pythonV1, npmV3, excelV1, microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c130);

		Computer c131 = new Computer(35150, room11);
		computerService.save(c131);

		Computer c132 = new Computer(35372, room11);
		computerService.save(c132);
		c132.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV5, pythonV1, npmV1, excelV1, gitV1, jdkV1));
		computerService.update(c132);

		Computer c133 = new Computer(35594, room11);
		computerService.save(c133);
		c133.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV2, pythonV1, npmV6, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c133);

		Computer c134 = new Computer(35816, room11);
		computerService.save(c134);
		c134.setProgramList(
				Arrays.asList(vscodeV1, eclipseV1, pythonV1, npmV5, excelV1, microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c134);

		Computer c135 = new Computer(36038, room11);
		computerService.save(c135);
		c135.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV3, pythonV1, npmV4, excelV1, microsoftSSMSV1,
				gitV1, jdkV1, powerBiV1));
		computerService.update(c135);

		// SG Sentonsa Place 12 room12
		Computer c136 = new Computer(36260, room12);
		computerService.save(c136);
		c136.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV2, pythonV1, npmV2, excelV1, microsoftSSMSV1,
				gitV1, jdkV1, powerBiV1));
		computerService.update(c136);

		Computer c137 = new Computer(36482, room12);
		computerService.save(c137);
		c137.setProgramList(Arrays.asList(nodejsV3, pythonV1, npmV3, sql8wbV1, sqlShellV1, excelV1, microsoftSSMSV1));
		computerService.update(c137);

		Computer c138 = new Computer(36704, room12);
		computerService.save(c138);
		c138.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV4, pythonV1, npmV4, excelV1, microsoftSSMSV1,
				gitV1, jdkV1, powerBiV1));
		computerService.update(c138);

		Computer c139 = new Computer(36926, room12);
		computerService.save(c139);
		c139.setProgramList(Arrays.asList(nodejsV5, pythonV1, npmV5, sql8wbV1, sqlShellV1, excelV1, microsoftSSMSV1,
				gitV1, jdkV1, powerBiV1));
		computerService.update(c139);

		Computer c140 = new Computer(37148, room12);
		computerService.save(c140);
		c140.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV6, pythonV1, npmV6, excelV1, microsoftSSMSV1,
				gitV1, jdkV1, powerBiV1));
		computerService.update(c140);

		Computer c141 = new Computer(37370, room12);
		computerService.save(c141);
		c141.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV7, pythonV1, npmV7, sql8wbV1, sqlShellV1, excelV1,
				microsoftSSMSV1, gitV1, jdkV1));
		computerService.update(c141);

		Computer c142 = new Computer(37592, room12);
		computerService.save(c142);
		c142.setProgramList(Arrays.asList(nodejsV3, pythonV1, excelV1, microsoftSSMSV1, powerBiV1));
		computerService.update(c142);

		Computer c143 = new Computer(37814, room12);
		computerService.save(c143);

		Computer c144 = new Computer(38036, room12);
		computerService.save(c144);

		Computer c145 = new Computer(38258, room12);
		computerService.save(c145);

		Computer c146 = new Computer(38480, room12);
		computerService.save(c146);

		Computer c147 = new Computer(38702, room12);
		computerService.save(c147);
		c147.setProgramList(Arrays.asList(vscodeV1, eclipseV1, nodejsV2, pythonV1, npmV1, excelV1, microsoftSSMSV1,
				gitV1, jdkV1, powerBiV1));
		computerService.update(c147);

		// no need to manuualy add date
		User u0 = new User("admin", "0000!!", "Root1", "Admin", "admin1@example.com");
		User u00 = new User("admin1", "0000!!", "Root", "Admin", "admin@example.com");
		User u1 = new User("andy.joe", "password123", "Andy", "Joe", "andy.joe@example.com");
		User u2 = new User("ahri.foxian", "password123", "Ahri", "Foxian", "ahri.foxian@example.com");
		User u3 = new User("aatrox.damion", "password123", "Aatrox", "Damion", "aatrox.damion@example.com");
		User u4 = new User("amumu.munsen", "password123", "Amumu", "Munsen", LocalDate.of(2023, 11, 27),
				"amumu.munsen@example.com");

		User u5 = new User("roomadmin", "0000!!", "Room", "admin", LocalDate.of(2023, 11, 27), "roomadmin@example.com");
		User u6 = new User("baron.nashor", "password123", "Baron", "Nashor", LocalDate.of(2023, 11, 27),
				"baron.nashor@example.com");
		User u7 = new User("darwin.norman", "password123", "Darwin", "Norman", LocalDate.of(2016, 11, 27),
				"darwin.norman@example.com");
		User u8 = new User("camden.leonard", "password123", "Camden", "Leonard", LocalDate.of(2023, 11, 27),
				"camden.leonard@example.com");
		User u9 = new User("quincy.jarvis", "password123", "Quincy", "Jarvis", LocalDate.of(2023, 11, 27),
				"quincy.jarvis@example.com");
		User u10 = new User("chaim.harrison", "password123", "Chaim", "Harrison", LocalDate.of(2023, 11, 27),
				"chaim.harrison@example.com");

		User u11 = new User("ryan.wilson", "password123", "Ryan", "Wilson", LocalDate.of(2023, 11, 27),
				"ryan.wilson@example.com");
		User u12 = new User("tejasva.saboo", "password123", "Tejasva", "Saboo", LocalDate.of(2021, 11, 27),
				"tejasva.saboo@example.com");
		User u13 = new User("donald.witcombe", "password123", "Donald", "Witcombe", LocalDate.of(2023, 11, 27),
				"donald.witcombe@example.com");
		User u14 = new User("flor.crencic", "password123", "Florencia", "Crencic", LocalDate.of(2023, 11, 27),
				"flor.crencic@example.com");
		User u15 = new User("james.mccarthy", "password123", "James", "McCarthy", LocalDate.of(2023, 11, 27),
				"james.mccarthy@example.com");
		User u16 = new User("dan.solomon", "password123", "Dan", "Solomon", LocalDate.of(2022, 11, 27),
				"dan.solomon@example.com");
		User u17 = new User("chris.spencer", "password123", "Chris", "Spencer", LocalDate.of(2023, 11, 27),
				"chris.spencer@example.com");
		User u18 = new User("carolina.portugal", "password123", "Carolina", "Portugal", LocalDate.of(2023, 11, 27),
				"carolina.portugal@example.com");
		User u19 = new User("alex.zlatevska", "password123", "Aleksandra", "Zlatevska", LocalDate.of(2023, 11, 27),
				"alex.zlatevska@example.com");
		User u20 = new User("joe.mclaren", "password123", "Joe", "Mclaren", LocalDate.of(2023, 10, 20),
				"joe.mclaren@example.com");

		List<User> users = Arrays.asList(u0, u00, u1, u2, u3, u4, u5, u6, u7, u8, u9, u10, u11, u12, u13, u14, u15, u16,
				u17, u18, u19, u20);

		for (User user : users) {
			userService.register(user);
		}

		// username: admin (u00)
		// password: 0000 !!
		// location, business and room admins in diff locations

		// Business admin
		BusinessAdmin businessAdmin0 = new BusinessAdmin(Business, u0, location1);
		BusinessAdmin businessAdmin = new BusinessAdmin(Business, u0, location2);
		BusinessAdmin businessAdmin1 = new BusinessAdmin(Business, u00, location1);
//		BusinessAdmin businessAdmin2 = new BusinessAdmin(Business, u00, location2);
//		BusinessAdmin businessAdmin3 = new BusinessAdmin(Business, u00, location3);
		staffService.save(businessAdmin0);
		staffService.save(businessAdmin);
		staffService.save(businessAdmin1);
//		staffService.save(businessAdmin2);
//		staffService.save(businessAdmin3);

		// Location admin
		LocationAdmin locationAdmin0 = new LocationAdmin(Location, u00, location2);
		LocationAdmin locationAdmin1 = new LocationAdmin(Location, u20, location1);
		LocationAdmin locationAdmin2 = new LocationAdmin(Location, u1, location1);
		LocationAdmin locationAdmin3 = new LocationAdmin(Location, u2, location2);
		LocationAdmin locationAdmin4 = new LocationAdmin(Location, u3, location2);
		LocationAdmin locationAdmin5 = new LocationAdmin(Location, u4, location3);

		staffService.save(locationAdmin0);
		staffService.save(locationAdmin1);
		staffService.save(locationAdmin2);
		staffService.save(locationAdmin3);
		staffService.save(locationAdmin4);
		staffService.save(locationAdmin5);

		// Room admin
		RoomAdmin roomAdmin0 = new RoomAdmin(Room, u00, location3, Arrays.asList(room10, room11));
		RoomAdmin roomAdmin1 = new RoomAdmin(Room, u5, location1, Arrays.asList(room1, room2));
		RoomAdmin roomAdmin2 = new RoomAdmin(Room, u6, location1);
		RoomAdmin roomAdmin3 = new RoomAdmin(Room, u7, location2);
		RoomAdmin roomAdmin4 = new RoomAdmin(Room, u8, location3);
		RoomAdmin roomAdmin5 = new RoomAdmin(Room, u9, location3);

		roomAdminService.save(roomAdmin0);
		roomAdminService.save(roomAdmin1);
		roomAdminService.save(roomAdmin2);
		roomAdminService.save(roomAdmin3);
		roomAdminService.save(roomAdmin4);
		roomAdminService.save(roomAdmin5);

		// Staff
		Staff staff1 = new Staff(User, u10, location1);
		Staff staff2 = new Staff(User, u12, location1);
		Staff staff3 = new Staff(User, u13, location1);
		Staff staff4 = new Staff(User, u14, location1);
		Staff staff5 = new Staff(User, u15, location1);
		Staff staff6 = new Staff(User, u16, location1);
		Staff staff7 = new Staff(User, u17, location1);
		Staff staff8 = new Staff(User, u18, location1);
		Staff staff9 = new Staff(User, u19, location1);

		staffService.save(staff1);
		staffService.save(staff2);
		staffService.save(staff3);
		staffService.save(staff4);
		staffService.save(staff5);
		staffService.save(staff6);
//		staffService.save(staff7);
//		staffService.save(staff8);
//		staffService.save(staff9);

		// Mandate
		String mandate1Desc = "We need 10 DEV-ready computers.";
		Mandate mandate1 = new Mandate(room1, roomAdmin1, mandate1Desc, "2024-03-10");
		mandateService.save(mandate1);

		String mandate2Desc = "We need 10 BI-ready computers.";
		Mandate mandate2 = new Mandate(room2, roomAdmin2, mandate2Desc, "2024-03-10");
		mandateService.save(mandate2);

		String mandate3Desc = "We need 10 DEV and BI-ready computers.";
		Mandate mandate3 = new Mandate(room3, roomAdmin3, mandate3Desc, "2024-03-10");
		mandateService.save(mandate3);
		
		//Reports
		String reportDesc1 = "No mouse and keyboard.";
		String reportDesc2 = "Can't connect to internet."; 
		String reportDesc3 = "No mouse.";
		String reportDesc4 = "No keyboard.";
		String reportDesc5 = "Cannot boot up.";
		Report r1 = new Report(c6, u00, LocalDate.of(2024,1,1), reportDesc5);
		Report r2 = new Report(c9, u00, LocalDate.of(2024,1,1), reportDesc1);
		Report r3 = new Report(c14, u00, LocalDate.of(2024,1,1), reportDesc1);
		Report r4 = new Report(c15, u00, LocalDate.of(2024,1,1), reportDesc2);
		Report r5 = new Report(c17, u0, LocalDate.of(2024,1,4), reportDesc3);
		Report r6 = new Report(c19, u0, LocalDate.of(2024,1,4), reportDesc3);
		Report r7 = new Report(c20, u0, LocalDate.of(2024,1,4), reportDesc2);
		Report r8 = new Report(c21, u0, LocalDate.of(2024,1,5), reportDesc5);
		Report r9 = new Report(c22, u0, LocalDate.of(2024,2,5), reportDesc5);
		Report r10 = new Report(c23, u2, LocalDate.of(2024,1,6), reportDesc1);
		Report r11 = new Report(c24, u13, LocalDate.of(2024,1,12), reportDesc1);
		Report r12 = new Report(c27, u15, LocalDate.of(2024,1,13), reportDesc1);
		Report r13 = new Report(c30, u1, LocalDate.of(2024,1,13), reportDesc5);
		Report r14 = new Report(c31, u00, LocalDate.of(2024,1,15), reportDesc5);
		Report r15 = new Report(c32, u3, LocalDate.of(2024,1,17), reportDesc5);
		Report r16 = new Report(c33, u2, LocalDate.of(2024,1,17), reportDesc5);
		Report r17 = new Report(c38, u10, LocalDate.of(2024,1,18), reportDesc1);
		Report r18 = new Report(c39, u9, LocalDate.of(2024,1,19), reportDesc2);
		Report r19 = new Report(c40, u3, LocalDate.of(2024,2,3), reportDesc5);
		Report r20 = new Report(c41, u9, LocalDate.of(2024,2,4), reportDesc5);
		Report r21 = new Report(c77, u00, LocalDate.of(2024,2,6), reportDesc2);
		Report r22 = new Report(c78, u3, LocalDate.of(2024,2,14), reportDesc4);
		Report r23 = new Report(c79, u4, LocalDate.of(2024,2,14), reportDesc1);
		Report r24 = new Report(c84, u00, LocalDate.of(2024,2,15), reportDesc2);
		Report r25 = new Report(c86, u7, LocalDate.of(2024,2,15), reportDesc5);
		Report r26 = new Report(c97, u0, LocalDate.of(2024,2,16), reportDesc2);
		Report r27 = new Report(c103, u2, LocalDate.of(2024,2,18), reportDesc1);
		Report r28 = new Report(c106, u0, LocalDate.of(2024,2,18), reportDesc5);
		Report r29 = new Report(c107, u0, LocalDate.of(2024,2,19), reportDesc2);
		Report r30 = new Report(c131, u2, LocalDate.of(2024,2,20), reportDesc1);
		Report r31 = new Report(c143, u8, LocalDate.of(2024,2,22), reportDesc1);
		Report r32 = new Report(c144, u0, LocalDate.of(2024,2,22), reportDesc2);
		Report r33 = new Report(c145, u11, LocalDate.of(2024,2,24), reportDesc5);
		Report r34 = new Report(c146, u5, LocalDate.of(2024,2,25), reportDesc3);
		
		List<Report> reports = Arrays.asList(r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20,r21,r22,r23,r24,r25,r26,r27,r28,r29,r30,r31,r32,r33,r34);
		
		for (Report r : reports) {
			reportService.save(r);
		}
		
		r1.setResolved(true);
		reportService.update(r1);
		r2.setResolved(true);
		reportService.update(r2);
		r3.setResolved(true);
		reportService.update(r3);
		r6.setResolved(true);
		reportService.update(r6);
		r10.setResolved(true);
		reportService.update(r10);
		r12.setResolved(true);
		reportService.update(r12);
		r13.setResolved(true);
		reportService.update(r13);
		r16.setResolved(true);
		reportService.update(r16);
		r18.setResolved(true);
		reportService.update(r18);
		r20.setResolved(true);
		reportService.update(r20);
		r26.setResolved(true);
		reportService.update(r26);
		r29.setResolved(true);
		reportService.update(r29);
		r31.setResolved(true);
		reportService.update(r31);
		r32.setResolved(true);
		reportService.update(r32);
		

	}
}