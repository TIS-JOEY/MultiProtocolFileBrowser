package com.tsmc.filebrowser.test;

import java.io.File;

public class LocalDriveServiceTest {
	public static void main(String[] args) throws Exception {
		createDir("123", "/Users/joey/Documents/qqq");
	}
	public static void createDir(String applicationId, String targetPath) throws Exception {

		//logger.info(String.format("[%s] call service [%s] start...", applicationId, "createDir"));

		File target = new File(targetPath);

		// if not exists
		if (!target.exists()) {
			boolean result = target.mkdir();
			if (result == false) {
				throw new Exception("Request Failed");
			}
		}
		
		//logger.info(String.format("[%s] call service [%s] complete!", applicationId, "createDir"));

	}
}
