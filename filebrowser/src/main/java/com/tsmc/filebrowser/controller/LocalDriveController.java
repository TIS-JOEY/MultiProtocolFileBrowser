package com.tsmc.filebrowser.controller;

import java.util.Map;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tsmc.filebrowser.entity.FileInfoEntity;
import com.tsmc.filebrowser.entity.ResponseStatusEntity;
import com.tsmc.filebrowser.model.StorageOperatorInterface;
import com.tsmc.filebrowser.util.ControllerUtil;

@RestController
public class LocalDriveController {
	
	@Autowired
	StorageOperatorInterface storageOperator;

	@GetMapping(path = "/localFiles/{resourceId}")
	public ResponseEntity<?> listItems(@RequestBody Map<String, String> requestBody) {
		
		Map responseBody = new HashMap<String, List>();
		
		try {
			List items = storageOperator.listItems(requestBody.get("path"));
			responseBody.put("result", items);
		} catch(IOException ex) {
			responseBody = ControllerUtil.getErrorResponse(ex);
			return ResponseEntity
		            .status(HttpStatus.BAD_REQUEST)
		            .body(responseBody);
		}
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(responseBody);
		
	}
	
	@PatchMapping(path = "/localFiles/{resourceId}")
	public ResponseEntity<?> renameItem(@RequestBody Map<String, String> requestBody) {
		
		Map responseBody = new HashMap<String, ResponseStatusEntity>();
		boolean operateResult = false;
		
		try {
			operateResult = storageOperator.renameItem(requestBody.get("item"), requestBody.get("newItemPath"));

		}catch(Exception ex) {
			responseBody = ControllerUtil.getErrorResponse(ex);
			return ResponseEntity
		            .status(HttpStatus.BAD_REQUEST)
		            .body(responseBody);
		}
		
		if(operateResult == true) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(responseBody);
		} else {
			responseBody = ControllerUtil.getErrorResponse(null);
			return ResponseEntity
		            .status(HttpStatus.BAD_REQUEST)
		            .body(responseBody);
		}
		
	}
	
	@PostMapping(path = "/localFiles/{resourceId}")
	public ResponseEntity<?> moveItems(@RequestBody Map<String, Object> requestBody) {
		
		Map responseBody = new HashMap<String, ResponseStatusEntity>();
		boolean operateResult = false;
		
		try {
			operateResult = storageOperator.moveItems((List<String>) requestBody.get("itemPaths"), (String) requestBody.get("newPath"));

		}catch(Exception ex) {
			responseBody = ControllerUtil.getErrorResponse(ex);
			return ResponseEntity
		            .status(HttpStatus.BAD_REQUEST)
		            .body(responseBody);
		}
		
		if(operateResult == true) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(responseBody);
		} else {
			responseBody = ControllerUtil.getErrorResponse(null);
			return ResponseEntity
		            .status(HttpStatus.BAD_REQUEST)
		            .body(responseBody);
		}
	}
}
