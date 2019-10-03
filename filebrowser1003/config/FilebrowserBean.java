package com.tsmc.filebrowser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tsmc.filebrowser.service.impl.LocalDriveServiceImpl;

@Configuration
public class FilebrowserBean {
	
	@Bean
    public LocalDriveServiceImpl localDriveBean() {
        return new LocalDriveServiceImpl();
    }
}
