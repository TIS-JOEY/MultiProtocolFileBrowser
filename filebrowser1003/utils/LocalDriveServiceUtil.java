package com.tsmc.filebrowser.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.PosixFileAttributes;
import java.util.ArrayList;
import java.util.List;

import com.tsmc.filebrowser.domain.FileInfoEntity;
import com.tsmc.filebrowser.exception.BadParameterRequestException;
import com.tsmc.filebrowser.exception.RemoteStorageIOException;

public class LocalDriveServiceUtil {
	
	public static FileInfoEntity getFileInfoEntity(String itemPath) throws IOException {
		
		FileInfoEntity fileInfoEntity = null;
		File file = new File(itemPath);
		Path path = Paths.get(itemPath);
		BasicFileAttributes basicAttr = Files.readAttributes(path, BasicFileAttributes.class);
		PosixFileAttributes posixAttr = Files.readAttributes(path, PosixFileAttributes.class);
		fileInfoEntity.setDate(basicAttr.lastModifiedTime().toString());
		fileInfoEntity.setDir(file.getParentFile().getName());
		fileInfoEntity.setName(file.getName());
		fileInfoEntity.setRights(posixAttr.permissions().toString());
		fileInfoEntity.setSize(String.valueOf(basicAttr.size()));
		
		return fileInfoEntity;
		
	}
	
	public static List<String> getItemListNames(File[] files) {
		List<String> itemListNames = new ArrayList<String>();
		for(File file: files) {
			itemListNames.add(file.getName());
		}
		
		return itemListNames;
		
	}
	
	public static File[] listItems(String dirPath) throws BadParameterRequestException{
		
		File targetDirectory = new File(dirPath);
		
		// is not a dir path
		if(!targetDirectory.isDirectory()) {
			throw new BadParameterRequestException("Is not a directory!");
		}
		
		return targetDirectory.listFiles();
	}
}
