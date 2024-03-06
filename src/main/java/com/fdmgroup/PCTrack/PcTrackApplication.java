package com.fdmgroup.PCTrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.fdmgroup.PCTrack.security.RsaKeyProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class PcTrackApplication {
 
	public static void main(String[] args) {
		SpringApplication.run(PcTrackApplication.class, args);
		System.out.println("_______________________________________");
	}

}
