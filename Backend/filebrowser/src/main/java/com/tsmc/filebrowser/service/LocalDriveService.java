package com.tsmc.filebrowser.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tsmc.filebrowser.domain.FileNode;
import com.tsmc.filebrowser.utils.LocalDriveServiceUtil;

@Service
public class LocalDriveService {

	private static final Logger logger = LoggerFactory.getLogger(LocalDriveService.class);

	public List<FileNode> listChildFileNodes(String applicationId, String targetPath) throws IOException {

		logger.info(String.format("[%s] call service [%s] start...", applicationId, "listChildFileNodes"));
		
		// save the listFile's fileNode
		List<FileNode> fileNodeList = new ArrayList<FileNode>();
		
		File targetDirectory = new File(targetPath);
		
		File[] listFiles = targetDirectory.listFiles();
		
		for (int i = 0; i < listFiles.length; i++) {
			fileNodeList.add(LocalDriveServiceUtil.getFileNode(listFiles[i].getAbsolutePath()));
		}

		logger.info(String.format("[%s] call service [%s] complete!", applicationId, "listChildFileNodes"));
		return fileNodeList;

	}

	public void renameFileNode(String applicationId, String sourcePath, String targetPath) throws Exception {

		logger.info(String.format("[%s] call service [%s] start...", applicationId, "renameFileNode"));

		File source = new File(sourcePath);
		File target = new File(targetPath);

		boolean result = source.renameTo(target);
		if (result == false) {
			// Failed to rename a file
			throw new Exception("Request Failed");
		}
		
		logger.info(String.format("[%s] call service [%s] complete", applicationId, "renameFileNode"));

	}

	public void moveFileNode(String applicationId, String sourcePath, String targetPath) throws Exception {

		logger.info(String.format("[%s] call service [%s] start...", applicationId, "moveFileNode"));

		File source = new File(sourcePath);
		File target = new File(targetPath);

		boolean result = source.renameTo(target);

		if (result == false) {
			throw new Exception("Request Failed");
		}
		
		logger.info(String.format("[%s] call service [%s] complete", applicationId, "moveFileNode"));
	}

	public void deleteFileNode(String applicationId, String targetPath) throws Exception {

		logger.info(String.format("[%s] call service [%s] start...", applicationId, "deleteFileNode"));

		File target = new File(targetPath);
		
		if(target.isDirectory()) {
			LocalDriveServiceUtil.deleteDir(targetPath);
			
		}else {
			boolean result = target.delete();
			if(result == false) {
				throw new Exception("Request Failed");
			}
		}
		
		logger.info(String.format("[%s] call service [%s] complete!", applicationId, "deleteFileNode"));

	}

	public void createDir(String applicationId, String targetPath) throws Exception {

		logger.info(String.format("[%s] call service [%s] start...", applicationId, "createDir"));

		File target = new File(targetPath);

		// if not exists
		if (!target.exists()) {
			boolean result = target.mkdir();
			if (result == false) {
				throw new Exception("Request Failed");
			}
		}
		
		logger.info(String.format("[%s] call service [%s] complete!", applicationId, "createDir"));

	}

	public ByteArrayResource downloadFileNode(String applicationId, String targetPath) throws IOException {

		logger.info(String.format("[%s] call service [%s] start...", applicationId, "downloadFileNode"));

		File target = new File(targetPath);
		ByteArrayResource byteArrayResource;

		// Init array with file length
		byte[] bytesArray = new byte[(int) target.length()];

		try (FileInputStream fileInputStream = new FileInputStream(target)) {
			fileInputStream.read(bytesArray); // read file into bytes[]
			byteArrayResource = new ByteArrayResource(bytesArray);
		} catch (IOException ex) {
			throw ex;
		}
		
		logger.info(String.format("[%s] call service [%s] complete!", applicationId, "downloadFileNode"));
		return byteArrayResource;

	}
	public void copyFileNode(String applicationId, String sourcePath, String targetPath) throws IOException {
		
		logger.info(String.format("[%s] call service [%s] start...", applicationId, "copyFileNode"));

		File source = new File(sourcePath);
		File target = new File(targetPath);
		
		try {
			LocalDriveServiceUtil.copyFildeNode(source, target);
		}catch(IOException ex) {
			logger.error(String.format("[%s] call service [%s] failed", applicationId, "copyFileNode"), ex);
			throw ex;
		}
		
		logger.info(String.format("[%s] call service [%s] complete!", applicationId, "copyFileNode"));
		
		
	}

	public void uploadSingleFile(String applicationId, String targetPath, MultipartFile uploadFile) throws IOException {

		logger.info(String.format("[%s] call service [%s] start...", applicationId, "uploadFileNode"));

		File target = new File(targetPath);

		try (InputStream uploadInputStream = uploadFile.getInputStream();
				OutputStream outputStream = new FileOutputStream(target);) {

			byte[] byteArray = uploadFile.getBytes();
			outputStream.write(byteArray);

		} catch (IOException ex) {

			logger.error(String.format("[%s] call service [%s] failed", applicationId, "uploadFileNode"), ex);
			throw ex;
		}
		
		logger.info(String.format("[%s] call service [%s] complete!", applicationId, "uploadFileNode"));

	}
}
