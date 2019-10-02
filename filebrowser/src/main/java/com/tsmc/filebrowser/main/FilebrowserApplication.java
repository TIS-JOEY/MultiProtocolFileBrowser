package com.tsmc.filebrowser.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.tsmc.filebrowser.controller.LocalDriveController;

@SpringBootApplication
@ComponentScan(basePackageClasses = LocalDriveController.class)
public class FilebrowserApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilebrowserApplication.class, args);
	}

}
