package com.fdmgroup.PCTrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.fdmgroup.PCTrack.security.RsaKeyProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class PcTrackApplication {

	public static void main(String[] args) {
		SpringApplication.run(PcTrackApplication.class, args);
	}

}
