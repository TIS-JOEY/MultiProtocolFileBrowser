package fileBrowserAPI.Controller;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fileBrowserAPI.Service.StorageOperatorService.FTPService;

@RestController
public class FTPController {
	
	@Autowired
	private FTPService ftpService;
	
	@RequestMapping("/FTP/login")
	public boolean login(String host, int port, String username, String password) {
		return ftpService.login(host, port, username, password);
	}
	
	@RequestMapping("/FTP/{remoteDirectoryPath}")
	public boolean uploadFile(@PathVariable String remoteDirectoryPath, String remoteFileName, String localFilePath) {
		return ftpService.uploadFile(remoteDirectoryPath + "/" + remoteFileName, localFilePath);
	}
	
	@RequestMapping("/FTP/{remoteDirectoryPath}")
	public boolean downloadFile(@PathVariable String remoteDirectoryPath, String remoteFileName, String localFilePath) {
		return ftpService.downloadFile(remoteDirectoryPath + "/" + remoteFileName, localFilePath);
	}
	
	@RequestMapping("/FTP/{remoteDirectoryPath}")
	public boolean uploadDirectory(@PathVariable String remoteDirectoryPath, String localDirectoryPath) {
		return ftpService.uploadDirectory(remoteDirectoryPath, localDirectoryPath);
	}
	
	@RequestMapping("/FTP/{remoteDirectoryPath}")
	public boolean downloadDirectory(@PathVariable String remoteDirectoryPath, String localDirectoryPath) {
		return ftpService.downloadDirectory(remoteDirectoryPath, localDirectoryPath);
	}
	
	@RequestMapping("/FTP/{remoteDirectoryPath}")
	public boolean renameFile(String oldRemoteFilePath, String newRemoteFilePath) {
		return ftpService.renameFile(oldRemoteFilePath, newRemoteFilePath);
	}
	
	@RequestMapping("/FTP/{remoteDirectoryPath}")
	public boolean renameDirectory(String oldRemoteDirectoryPath, String newRemoteDirectoryPath) {
		return ftpService.renameDirectory(oldRemoteDirectoryPath, newRemoteDirectoryPath);
	}
	
	@RequestMapping("/FTP/{remoteDirectoryPath}")
	public boolean moveFile() {
		return false;
	}
	
	@RequestMapping("/FTP/{remoteDirectoryPath}")
	public FTPFile[] listFiles(@PathVariable String remoteDirectoryPath) {
		return ftpService.listFiles(remoteDirectoryPath);
	}
	
	@RequestMapping("/FTP/{remoteDirectoryPath}")
	public boolean makeDirectory(String remoteDirectoryPath) {
		return ftpService.makeDirectory(remoteDirectoryPath);
	}
	
	@RequestMapping("/FTP/{remoteDirectoryPath}")
	public boolean removeDirectory(String remoteDirectoryPath) {
		return ftpService.removeDirectory(remoteDirectoryPath);
	}
	
	@RequestMapping("/FTP/{remoteDirectoryPath}")
	public boolean removeFile(String remoteFilePath) {
		return ftpService.removeFile(remoteFilePath);
	}

	
	
}
