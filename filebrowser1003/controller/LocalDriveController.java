package com.tsmc.filebrowser.controller;

import java.util.Map;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tsmc.filebrowser.domain.FileInfoEntity;
import com.tsmc.filebrowser.domain.ResponseStatusEntity;
import com.tsmc.filebrowser.service.StorageOperatorInterface;
import com.tsmc.filebrowser.utils.LocalDriveServiceUtil;

@RestController
public class LocalDriveController {
	
	@Autowired
	StorageOperatorInterface storageOperatorInterface;
	
	@GetMapping(path = "/hello")
	public String sayHi() {
		return "hi";
	}
	
	@GetMapping(path = "/localDrive/{application-id}/files/{path}")
	public ResponseEntity<?> listItems(@PathVariable String applicationId, @PathVariable String dirPath) {
		
		Map responseBody = new HashMap<String, List>();
		
		try {
			List items = storageOperatorInterface.listFiles(dirPath);
			responseBody.put("result", items);
		} catch(RuntimeException ex) {
			throw ex;
		}
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(responseBody);
		
	}
	
	@PutMapping(path = "/localDrive/{application-id}/files/rename")
	public ResponseEntity<?> renameItem(@RequestBody Map<String, String> requestBody) {
		
		Map responseBody = new HashMap<String, ResponseStatusEntity>();
		
		try {
			storageOperatorInterface.renameFile(requestBody.get("oldPathName"), requestBody.get("newName"));

		}catch(RuntimeException ex) {
			throw ex;
		}
		
		responseBody.put("result", new ResponseStatusEntity(true, null));
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(responseBody);
		
	}
	
	@PutMapping(path = "/localDrive/{application-id}/files/move")
	public ResponseEntity<?> moveItems(@RequestBody Map<String, String> requestBody) {
		
		Map responseBody = new HashMap<String, ResponseStatusEntity>();
		
		try {
			storageOperatorInterface.moveFile(requestBody.get("sourcePath"), requestBody.get("targetPath"));

		}catch(RuntimeException ex) {
			throw ex;
		}
		
		responseBody.put("result", new ResponseStatusEntity(true, null));
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(responseBody);
	}
	
	@DeleteMapping(path = "/localDrive/{application-id}/files/{path}/{filename}")
	public ResponseEntity<?> deleteFile(@PathVariable String applicationId, @PathVariable String path, @PathVariable String fileName){
		
		Map responseBody = new HashMap<String, ResponseStatusEntity>();
		
		try {
			storageOperatorInterface.deleteFile(path, fileName);

		}catch(RuntimeException ex) {
			throw ex;
		}
		
		responseBody.put("result", new ResponseStatusEntity(true, null));
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(responseBody);
	}
	
	@PostMapping(path = "/localDrive/{application-id}/files/{path}/create")
	public ResponseEntity<?> createDir(@PathVariable String applicationId, @PathVariable String path, @PathVariable String fileName){
		
		Map responseBody = new HashMap<String, ResponseStatusEntity>();
		
		try {
			storageOperatorInterface.createDir(path);

		}catch(RuntimeException ex) {
			throw ex;
		}
		
		responseBody.put("result", new ResponseStatusEntity(true, null));
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(responseBody);
	}
	
	@PostMapping(path = "/localDrive/{application-id}/files/{path}/{fileName}")
	public ResponseEntity<?> uploadFile(@PathVariable String applicationId, @PathVariable String path, @PathVariable String fileName, @RequestBody Map<String, MultipartFile> requestBody){
		
		Map responseBody = new HashMap<String, ResponseStatusEntity>();
		
		try {
			storageOperatorInterface.uploadFile(path, requestBody.get("file"));

		}catch(RuntimeException ex) {
			throw ex;
		}
		
		responseBody.put("result", new ResponseStatusEntity(true, null));
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(responseBody);
	}
	
	@GetMapping(path = "/localDrive/{application-id}/files/{path}/{fileName}")
	public ResponseEntity<?> downloadFile(@PathVariable String applicationId, @PathVariable String path, @PathVariable String fileName){
		
		Map responseBody = new HashMap<String, ResponseStatusEntity>();
		FileInfoEntity fileInfoEntity = null;
		try {
			fileInfoEntity = storageOperatorInterface.downloadFile(path);
			responseBody.put("result", fileInfoEntity);
		}catch(RuntimeException ex) {
			throw ex;
		}
		
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(responseBody);
	}
	
	
}
