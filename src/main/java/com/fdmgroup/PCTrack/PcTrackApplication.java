package com.fdmgroup.PCTrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.fdmgroup.PCTrack.security.RsaKeyProperties;

/**
 * <p>
 * Welcome to PC Track Software Management System. The aim of this web application is to document computers from FDM
 * Group, along with the software installed in it. This would allow employees to find a computer where they can perform
 * their responsibilities according to their role.
 * </p>
 * 
 * <p>
 * In this application, users are given staff roles to determine their permission levels. The permission levels are shown
 * below:
 * <ul>
 * 	<li>Staff: Basic view permissions, mark room mandates as done</li>
 * 	<li>Room admin: All staff permissions, create and edit mandates for a room, create and edit computers, assign another room admin
 * to a room</li>
 * 	<li>Location admin: all room admin permissions, create rooms in a location, assign another location admin for a location
 * 	<li>Business admin: all location admin permissions, create a location, manage staff permissions</li>
 * </ul>
 * </p>
 * 
 * <p>
 * Note that most objects in this application are generic on the backend, except for some of the more complicated entities. Most
 * entities, services, and controllers are implemented with basic CRUD functionalities. Please do refer to the JavaDoc for those
 * that require more information. Spring security is integrated from a sample project shared in the previous class. Reference is
 * from:</p>
 * 
 *	<p>
 *		<a href="https://www.danvega.dev/blog/spring-security-jwt">
 *		How to Secure your REST APIs with Spring Security & JSON Web Tokens (JWTs) by Dan Vega</a>
 *		
 *	</p>
 * 
 * @author Bennett Chung, Jefferson Zheng, Jenny Pang, Kelly Xu, Matthew Chanco
 * @version 12/03/2024
 */



@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class PcTrackApplication {
 
	public static void main(String[] args) {
		SpringApplication.run(PcTrackApplication.class, args);
		System.out.println("_______________________________________");
	}

}
