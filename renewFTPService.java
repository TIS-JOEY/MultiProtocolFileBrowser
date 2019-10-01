package fileBrowserAPI.Service.StorageOperatorService;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import fileBrowserAPI.Service.StorageConnectorService.FTPConnector;

@Service
public class FTPService implements StorageOperatorInterface{
	
	private final Logger logger = LoggerFactory.getLogger(FTPService.class);
	FTPConnector ftpConnector;
	FTPClient ftpClient;
	
	
	@Override 
	public boolean login(String host, int port, String username, String password) throws IOException{
		boolean result = false;
		ftpConnector = new FTPConnector();
		result = ftpConnector.connect(host, port, username, password);
		
		if(result == true) {
			ftpClient = (FTPClient) ftpConnector.getOperator();
		}
		return result;
		
	}
	
	// https://stackoverflow.com/questions/45077931/how-to-upload-images-to-ftp-server-using-spring-multipartfile-without-storing-im
	@Override
	public boolean uploadFileResource(String remoteDirPath, InputStream localFileInputStream) throws IOException{
		// TODO Auto-generated method stub
		boolean result = false;
		result = FTPUtil.uploadSingleFile(ftpClient, remoteDirPath, localFileInputStream);
		return result;
	}
	
	// https://stackoverflow.com/questions/35680932/download-a-file-from-spring-boot-rest-service
	@Override
	public byte[] downloadFileResource(String remoteResourcePath, String localResourcePath) throws IOException {
		// TODO Auto-generated method stub
		
		byte[] result = null;
		result = FTPUtil.downloadSingleFile(ftpClient, remoteResourcePath, localResourcePath);
		return result;
	}
	
	
	
	
}
