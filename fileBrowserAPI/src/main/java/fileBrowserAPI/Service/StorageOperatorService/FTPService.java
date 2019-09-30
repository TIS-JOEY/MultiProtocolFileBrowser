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
import org.springframework.stereotype.Service;

import fileBrowserAPI.Entities.FileDirectoryEntity;
import fileBrowserAPI.Entities.FileEntity;
import fileBrowserAPI.Service.StorageConnectorService.FTPConnector;

@Service
public class FTPService implements StorageOperatorInterface{
	
	FTPConnector ftpConnector;
	FTPClient ftpClient;
	
	@Override 
	public boolean login(String host, int port, String username, String password) {
		boolean result;
		ftpConnector = new FTPConnector();
		result = ftpConnector.connect(host, port, username, password);
		
		if(result == true) {
			ftpClient = (FTPClient) ftpConnector.getOperator();
		}
		return result;
		
	}
	@Override
	public boolean uploadFile(String remoteDirectoryPath, String localFilePath) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			result = FTPUtil.uploadSingleFile(ftpClient, remoteDirectoryPath, localFilePath);
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		return result;
        
	}
	@Override
	public boolean downloadFile(String remoteFilePath, String localFilePath) {
		// TODO Auto-generated method stub
		
		boolean result = false;
		try {
			result = FTPUtil.downloadSingleFile(ftpClient, remoteFilePath, localFilePath);
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		return result;
	}
	@Override
	public boolean uploadDirectory(String remoteDirectoryPath, String localDirectoryPath) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			result = FTPUtil.uploadDirectory(ftpClient, remoteDirectoryPath, "", localDirectoryPath);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
	@Override
	public boolean downloadDirectory(String remoteDirectoryPath, String localDirectoryPath) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			result = FTPUtil.downloadDirectory(ftpClient, remoteDirectoryPath, "", localDirectoryPath);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return result;
		
	}
	@Override
	public boolean renameFile(String oldRemoteFilePath, String newRemoteFilePath) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			result = ftpClient.rename(oldRemoteFilePath, newRemoteFilePath);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
	@Override
	public boolean renameDirectory(String oldRemoteDirectoryPath, String newRemoteDirectoryPath) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			result = ftpClient.rename(oldRemoteDirectoryPath, newRemoteDirectoryPath);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
	@Override
	public boolean moveFile() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public FTPFile[] listFiles(String remoteFilePath) {
		// TODO Auto-generated method stub
		FTPFile[] result = null;
		try {
			result = ftpClient.listFiles();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
	@Override
	public FTPFile[] listDirectories(String remoteDirectoryPath) {
		// TODO Auto-generated method stub
		FTPFile[] result = null;
		try {
			result = ftpClient.listDirectories();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
	@Override
	public boolean makeDirectory(String remoteDirectoryPath) {
		boolean result = false;
		try {
			result = ftpClient.makeDirectory(remoteDirectoryPath);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
	@Override
	public boolean changeDirectory(String remoteDirectoryPath) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			result = ftpClient.changeWorkingDirectory(remoteDirectoryPath);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
	@Override
	public boolean removeDirectory(String remoteDirectoryPath) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			result = FTPUtil.removeDirectory(ftpClient, remoteDirectoryPath, "");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
	@Override
	public boolean removeFile(String remoteFilePath) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			result = ftpClient.deleteFile(remoteFilePath);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
	
	
	@Override
	public boolean checkDirectoryExists(String remoteDirectoryPath) {
        int returnCode = 0;
        boolean result = true;
        try {
        	ftpClient.changeWorkingDirectory(remoteDirectoryPath);
            returnCode = ftpClient.getReplyCode();
            if (returnCode == 550) {
                result = false;
            }
        } catch(IOException ex) {
        	ex.printStackTrace();
        }
        
        return result;
    }
 
    /**
     * Determines whether a file exists or not
     * @param filePath
     * @return true if exists, false otherwise
     * @throws IOException thrown if any I/O error occurred.
     */
	@Override
    public boolean checkFileExists(String remoteFilePath) {
    	int returnCode = 0;
    	boolean result = true;
    	
        try (InputStream inputStream = ftpClient.retrieveFileStream(remoteFilePath);) {
            returnCode = ftpClient.getReplyCode();
            if (inputStream == null || returnCode == 550) {
                result = false;
            }
        } catch(IOException ex) {
        	ex.printStackTrace();
        }
        
        return result;
        
        
    }
	
	
	
}
