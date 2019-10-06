package com.tsmc.filebrowser.domain;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class RequestInfo {
	
	private String sourcePath;
	
	private String targetPath;
	
	private String targetName;
	
	private List<String> sourcePaths;
	
	private List<String> targetPaths;
	
	private List<MultipartFile> multipartFiles;
	
	public RequestInfo() {
		
	}

	public RequestInfo(String sourcePath, String targetPath, String targetName, List<String> sourcePaths,
			List<String> targetPaths, List<MultipartFile> multipartFiles) {
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
	
	public List<String> getSourcePaths() {
		return sourcePaths;
	}

	public void setSourcePaths(List<String> sourcePaths) {
		this.sourcePaths = sourcePaths;
	}

	public List<String> getTargetPaths() {
		return targetPaths;
	}

	public void setTargetPaths(List<String> targetPaths) {
		this.targetPaths = targetPaths;
	}

	public List<MultipartFile> getMultipartFiles() {
		return multipartFiles;
	}

	public void setMultipartFiles(List<MultipartFile> multipartFiles) {
		this.multipartFiles = multipartFiles;
	}
	
	
	
}
