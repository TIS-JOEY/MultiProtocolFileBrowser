package com.tsmc.filebrowser.domain;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class RequestInfo {
	
	private String sourcePath;
	
	private String targetPath;
	
	private String targetName;
	
	private String[] sourcePaths;
	
	private String[] targetPaths;
	
	private MultipartFile[] multipartFiles;
	
	public RequestInfo() {
		
	}

	public RequestInfo(String sourcePath, String targetPath, String targetName, String[] sourcePaths,
			String[] targetPaths, MultipartFile[] multipartFiles) {
		super();
		this.sourcePath = sourcePath;
		this.targetPath = targetPath;
		this.targetName = targetName;
		this.sourcePaths = sourcePaths;
		this.targetPaths = targetPaths;
		this.multipartFiles = multipartFiles;
	}

	public String getSourcePath() {
		return sourcePath;
	}

	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}

	public String getTargetPath() {
		return targetPath;
	}

	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String[] getSourcePaths() {
		return sourcePaths;
	}

	public void setSourcePaths(String[] sourcePaths) {
		this.sourcePaths = sourcePaths;
	}

	public String[] getTargetPaths() {
		return targetPaths;
	}

	public void setTargetPaths(String[] targetPaths) {
		this.targetPaths = targetPaths;
	}

	public MultipartFile[] getMultipartFiles() {
		return multipartFiles;
	}

	public void setMultipartFiles(MultipartFile[] multipartFiles) {
		this.multipartFiles = multipartFiles;
	}

	
	
	
}
