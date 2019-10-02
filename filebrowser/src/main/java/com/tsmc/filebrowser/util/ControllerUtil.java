package com.tsmc.filebrowser.util;

import java.util.Map;

import com.tsmc.filebrowser.entity.ResponseStatusEntity;

public class ControllerUtil {
	
	private static Map<String, ResponseStatusEntity> responseBody;

	public static Map<String, ResponseStatusEntity> getErrorResponse(Exception ex) {
		responseBody = null;
		ResponseStatusEntity responseStatusEntity = new ResponseStatusEntity();
		responseStatusEntity.setSuccess(false);
		responseStatusEntity.setError(ex.getMessage());
		responseBody.put("result", responseStatusEntity);
		return responseBody;
	}
}
