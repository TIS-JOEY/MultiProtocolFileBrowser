package com.tsmc.filebrowser.entity;

public class ResponseStatusEntity {

	private boolean success;
	private String error;
	
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
}
