package com.tsmc.filebrowser.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tsmc.filebrowser.domain.FileInfoEntity;
import com.tsmc.filebrowser.exception.BadParameterRequestException;
import com.tsmc.filebrowser.exception.RemoteStorageIOException;
import com.tsmc.filebrowser.exception.RequestFailedException;
import com.tsmc.filebrowser.exception.ResourceNotFoundException;
import com.tsmc.filebrowser.service.StorageOperatorInterface;
import com.tsmc.filebrowser.utils.LocalDriveServiceUtil;

@Service
public class LocalDriveServiceImpl implements StorageOperatorInterface {
	
	@Override
	public List<FileInfoEntity> listFiles(String dirPath) {
		
		// save the listFile's fileInfoEntity
		List<FileInfoEntity> fileInfoEntityList = new ArrayList<FileInfoEntity>();
		
		try {
			// list files and save their fileInfoEntity
			for(File file: LocalDriveServiceUtil.listItems(dirPath)) {
				fileInfoEntityList.add(LocalDriveServiceUtil.getFileInfoEntity(dirPath+"/"+file.getName()));
			}
			
		}catch(BadParameterRequestException ex) {
			// The request parameter is not a dir path
			throw ex;
		}
		catch(FileNotFoundException ex) {
			// Cannot found the resource
			throw new ResourceNotFoundException("Cannot find Resource!");
		}catch(IOException ex) {
			// trigger IOException
			throw new RemoteStorageIOException("Failed to list files!");
		}
		
		return fileInfoEntityList;
		
	}

	@Override
	public void renameFile(String oldPath, String newPath) {
		
		File oldFile = new File(oldPath);
		File newFile = new File(newPath);
		
		if(newFile.getParentFile().getAbsoluteFile()!=oldFile.getParentFile().getAbsoluteFile()) {
			// Must in the same directory
			throw new BadParameterRequestException("Must in the same directory!");
		}
		
		if(newFile.exists()) {
			// Duplicate file name
			throw new BadParameterRequestException("Duplicate name!");
		}
		
		boolean renameResult = oldFile.renameTo(newFile);
		if(renameResult == false) {
			// Failed to rename a file
			throw new RequestFailedException("Failed to rename the file");
		}

		
	}

	@Override
	public void moveFile(String sourcePath, String destPath) {
		
		File destination = new File(destPath);
		
		if(!destination.isDirectory()) {
			// Destination is not a directory
			throw new BadParameterRequestException("destPath is not a directory!");
		}
		
		File source = new File(sourcePath);
		boolean result = source.renameTo(destination);
		
		if(result == false) {
			throw new RequestFailedException("Failed to move file!");
		}
	}

	@Override
	public void deleteFile(String dirPath, String fileName) {
		
		File file = new File(dirPath + "/" + fileName);
		boolean result = file.delete();
		
		if(result == false) {
			throw new RequestFailedException("Failed to remove file!");
		}
	}

	@Override
	public void createDir(String newPath) {
		File file = new File(newPath);
		if(!file.exists()) {
			boolean result = file.mkdir();
			if(result == false) {
				throw new RequestFailedException("Failed to create a directory!");
			}
		}else {
			throw new BadParameterRequestException("Directory is already exists!");
		}
		
	}

	@Override
	public void uploadFile(String destPath, MultipartFile uploadFile) {
		// TODO Auto-generated method stub
		
		File targetFile = new File(destPath);
	    if(targetFile.exists()) {
	    	throw new BadParameterRequestException("Filename is duplicate!");
	    }
	    
		try (InputStream uploadInputStream = uploadFile.getInputStream();OutputStream outputStream = new FileOutputStream(targetFile);){		
			
			byte[] buffer = new byte[uploadInputStream.available()];
			uploadInputStream.read(buffer);
		    outputStream.write(buffer);
		}catch(IOException ex) {
			throw new RequestFailedException("Failed to upload a file!");
		}
		
		
	}

	@Override
	public FileInfoEntity downloadFile(String path) {
		
		File file = new File(path);
		if(!file.exists()) {
			throw new ResourceNotFoundException("File is not exists!");
		}
		FileInfoEntity fileInfoEntity = new FileInfoEntity();
		//init array with file length
		byte[] bytesArray = new byte[(int) file.length()]; 
		
		try(FileInputStream fileInputStream = new FileInputStream(file);) {
			fileInputStream.read(bytesArray); //read file into bytes[]
			fileInfoEntity = LocalDriveServiceUtil.getFileInfoEntity(path);
			fileInfoEntity.setContent(bytesArray);
		}catch(IOException ex) {
			throw new RemoteStorageIOException("Remote Storage IO Exception occur!");
		}
		
		return fileInfoEntity;

	}
	
}

