package com.tsmc.filebrowser.domain;

public class ResponseStatusEntity {

	private boolean success;
	private String error = null;
	
	public ResponseStatusEntity(boolean success, String error) {
		this.success = success;
		this.error = error;
	}
	
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
