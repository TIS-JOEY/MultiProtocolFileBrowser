package com.tsmc.filebrowser.entity;

import java.util.List;

public class RequestInfoEntity {
	
	private String action;
	
	private String path;
	
	private String item;
	
	private String newItemPath;
	
	private List<String> items;
	
	private String content;
	
	private String newPath;
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getNewItemPath() {
		return newItemPath;
	}

	public void setNewItemPath(String newItemPath) {
		this.newItemPath = newItemPath;
	}

	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNewPath() {
		return newPath;
	}

	public void setNewPath(String newPath) {
		this.newPath = newPath;
	}

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

	public String getPermsCode() {
		return permsCode;
	}

	public void setPermsCode(String permsCode) {
		this.permsCode = permsCode;
	}

	public boolean isRecursive() {
		return recursive;
	}

	public void setRecursive(boolean recursive) {
		this.recursive = recursive;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getCompressedFilename() {
		return compressedFilename;
	}

	public void setCompressedFilename(String compressedFilename) {
		this.compressedFilename = compressedFilename;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public String getToFileName() {
		return toFileName;
	}

	public void setToFileName(String toFileName) {
		this.toFileName = toFileName;
	}

	private String perms;
	
	private String permsCode;
	
	private boolean recursive;
	
	private String destination;
	
	private String compressedFilename;
	
	private String folderName;
	
	private String toFileName;
}
