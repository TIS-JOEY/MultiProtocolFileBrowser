package fileBrowserAPI.Entities;

public class FileEntity {
	
	private String fileName;
	private String fileContent;
	
	public FileEntity(String fileNmae, String content) {
		this.fileName = fileName;
		this.fileContent = content;
	}
	
	public String getFileName() {
		return this.fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileContent() {
		return this.fileContent;
	}
	
	public void setFileConent() {
		this.fileContent = fileContent;
	}
}
