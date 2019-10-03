package com.tsmc.filebrowser.service;
import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.tsmc.filebrowser.domain.FileInfoEntity;
public interface StorageOperatorInterface {
	
	public List<FileInfoEntity> listFiles(String dirPath);
	public void renameFile(String oldPath, String newPath);
	public void moveFile(String sourcePath, String destPath);
	public void deleteFile(String dirPath, String fileName);
	public void createDir(String newPath);
	public void uploadFile(String destPath, MultipartFile uploadFile);
	public FileInfoEntity downloadFile(String path);
	
}
