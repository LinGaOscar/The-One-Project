package com.oscar.one.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class OneEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneEurekaApplication.class, args);
	}

}
