package com.tsmc.filebrowser.domain;

public class FileNode {
	
	private String name;
	
	private String path;
	
	private String parentPath;
	
	private String size;
	
	private Boolean isDir;
	
	private String createdTime;
	
	private String modifiedTime;
	
	public FileNode() {
		
	}
	
	public FileNode(String name, String path, Boolean isDir, FileNode parentNode, String createdTime,
			String modifiedTime, String parentPath, String size) {
		super();
		this.name = name;
		this.path = path;
		this.isDir = isDir;
		this.createdTime = createdTime;
		this.modifiedTime = modifiedTime;
		this.parentPath = parentPath;
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Boolean getIsDir() {
		return isDir;
	}

	public void setIsDir(Boolean isDir) {
		this.isDir = isDir;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getParentPath() {
		return parentPath;
	}

	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
	
}
