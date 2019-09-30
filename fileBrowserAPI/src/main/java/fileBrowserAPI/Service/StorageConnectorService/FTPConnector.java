package fileBrowserAPI.Service.StorageConnectorService;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPConnector implements StorageConnectorInterface{
	
	static FTPClient ftpClient = new FTPClient();
	
	@Override
	public boolean connect(String host, int port, String username, String password) {
		// TODO Auto-generated method stub
		boolean loginStatus = false;
		try {
			ftpClient.connect(host, port);
			int replyCode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				System.out.println("Cannot Connect to FTP");
                return false;
            }
			
			loginStatus = ftpClient.login(username, password);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return loginStatus;	
		
	}
	
	
	@Override
	public boolean closeConnection() {
		// TODO Auto-generated method stub
		if(ftpClient.isConnected()) {
			try {
				ftpClient.logout();
				ftpClient.disconnect();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		return true;
		
	}
	
	public Object getOperator() {
		return ftpClient;
	}

	
}
