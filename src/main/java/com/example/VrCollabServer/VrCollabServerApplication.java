package com.example.VrCollabServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example")
public class VrCollabServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VrCollabServerApplication.class, args);
	}

}
