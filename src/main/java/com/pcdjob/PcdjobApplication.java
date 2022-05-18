package com.pcdjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class })
@EnableSwagger2
public class PcdjobApplication {

	public static void main(String[] args) {
		SpringApplication.run(PcdjobApplication.class, args);
	}

}
