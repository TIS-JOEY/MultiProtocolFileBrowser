package fileBrowserAPI.Service.StorageOperatorService;

import java.io.IOException;
import java.util.List;

import org.apache.commons.net.ftp.FTPFile;

import fileBrowserAPI.Entities.FileDirectoryEntity;
import fileBrowserAPI.Entities.FileEntity;

public interface StorageOperatorInterface {
	public boolean login(String host, int port, String username, String password);
	public boolean uploadFile(String remoteDirectoryPath, String localFilePath);
	public boolean downloadFile(String remoteFilePath, String localFilePath);
	public boolean uploadDirectory(String remoteDirectoryPath, String localDirectoryPath);
	public boolean downloadDirectory(String remoteDirectoryPath, String localDirectoryPath);
	public boolean renameFile(String oldRemoteFilePath, String newRemoteFilePath);
	public boolean renameDirectory(String oldRemoteDirectoryPath, String newRemoteDirectoryPath);
	public boolean moveFile();
	public FTPFile[] listFiles(String remoteDirectoryPath);
	public FTPFile[] listDirectories(String remoteDirectoryPath);
	public boolean makeDirectory(String remoteDirectoryPath);
	public boolean changeDirectory(String remoteDirectoryPath);
	public boolean removeDirectory(String remoteDirectoryPath);
	public boolean removeFile(String remoteFilePath);
	public boolean checkDirectoryExists(String remoteDirectoryPath);
	public boolean checkFileExists(String remoteFilePath);
	
	
}
