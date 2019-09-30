package fileBrowserAPI.Entities;

import java.util.List;

public class FileDirectoryEntity {
	
	private List fileList;
	
	public FileDirectoryEntity(List fileList) {
		this.fileList = fileList;
	}
	
	public List getFileList() {
		return this.fileList;
	}
	
	public void setFileList(List fileList) {
		this.fileList = fileList;
	}
}
