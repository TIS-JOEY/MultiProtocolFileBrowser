package com.tsmc.filebrowser.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"LocalDriveController"})
public class FilebrowserApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(FilebrowserApplication.class, args);
	}

}
