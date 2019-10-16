package com.tsmc.filebrowser.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tsmc.filebrowser.domain.FileNode;
import com.tsmc.filebrowser.domain.RequestInfo;
import com.tsmc.filebrowser.domain.ResultBody;
import com.tsmc.filebrowser.service.LocalDriveService;

@CrossOrigin(maxAge = 3600)
@RestController
public class LocalDriveController {

	@Autowired
	LocalDriveService localDriveService;

	@GetMapping(path = "/hello")
	public String sayHi() {
		return "hi";
	}

	@GetMapping(path = "/localDrive/{application-id}/fileNodes/childs")
	public ResponseEntity<Map<String, List<FileNode>>> listChildFileNodes(@PathVariable("application-id") String applicationId,
			@RequestParam(value = "targetPath", required = true) String targetPath) throws IOException {

		Map<String, List<FileNode>> responseBody = new HashMap<String, List<FileNode>>();

		List<FileNode> childFileNodeList = localDriveService.listChildFileNodes(applicationId, targetPath.toString());
		responseBody.put("result", childFileNodeList);

		return ResponseEntity.status(HttpStatus.OK).body(responseBody);

	}

	@PutMapping(path = "/localDrive/{application-id}/fileNodes/name")
	public ResponseEntity<ResultBody> renameFileNodes(@PathVariable("application-id") String applicationId,
			@RequestBody RequestInfo requestBody) throws Exception {
		
		localDriveService.renameFileNodes(applicationId, requestBody.getSourcePaths(), requestBody.getTargetName());
		return ResponseEntity.status(HttpStatus.OK).body(ResultBody.success());

	}

	@PutMapping(path = "/localDrive/{application-id}/fileNodes/location")
	public ResponseEntity<ResultBody> moveFileNodes(@PathVariable("application-id") String applicationId,
			@RequestBody RequestInfo requestBody) throws Exception {
		
		String targetPath = requestBody.getTargetPath();
		for (String sourcePath : requestBody.getSourcePaths()) {
			localDriveService.moveFileNode(applicationId, sourcePath, targetPath);
		}

		return ResponseEntity.status(HttpStatus.OK).body(ResultBody.success());
	}


	@DeleteMapping(path = "/localDrive/{application-id}/fileNodes")
	public List<ResponseEntity<ResultBody>> deleteFileNodes(@PathVariable("application-id") String applicationId,
			@RequestBody RequestInfo requestBody) throws Exception {
		List<ResponseEntity<ResultBody>> responses = new ArrayList<ResponseEntity<ResultBody>>();

		for (String targetPath : requestBody.getTargetPaths()) {
			responses.add(_deleteFileNode(applicationId, targetPath));
		}

		return responses;
	}

	public ResponseEntity<ResultBody> _deleteFileNode(String applicationId, String targetPath) throws Exception {

		localDriveService.deleteFileNode(applicationId, targetPath);

		return ResponseEntity.status(HttpStatus.OK).body(ResultBody.success());
	}

	@PostMapping(path = "/localDrive/{application-id}/fileNodes/dir")
	public ResponseEntity<ResultBody> createDir(@PathVariable("application-id") String applicationId,
			@RequestBody RequestInfo requestBody) throws Exception {
		System.out.println(requestBody);
		localDriveService.createDir(applicationId, requestBody.getTargetPath());

		return ResponseEntity.status(HttpStatus.OK).body(ResultBody.success());
	}

	@PostMapping(path = "/localDrive/{application-id}/fileNodes/dir/content")
	public List<ResponseEntity<ResultBody>> uploadDir(@PathVariable("application-id") String applicationId,
			@RequestBody RequestInfo requestBody) throws Exception {

		List<ResponseEntity<ResultBody>> responses = new ArrayList<ResponseEntity<ResultBody>>();

		localDriveService.createDir(applicationId, requestBody.getTargetPath());

		String[] targetPaths = requestBody.getTargetPaths();
		MultipartFile[] multipartFiles = requestBody.getMultipartFiles();

		for (int i = 0; i < targetPaths.length; i++) {
			responses.add(_uploadFile(applicationId, targetPaths[i], multipartFiles[i]));
		}

		return responses;

	}

	@PostMapping(path = "/localDrive/{application-id}/fileNodes/content")
	public List<ResponseEntity<ResultBody>> uploadFiles(@PathVariable("application-id") String applicationId,
			@RequestBody RequestInfo requestBody) throws IOException {

		List<ResponseEntity<ResultBody>> responses = new ArrayList<ResponseEntity<ResultBody>>();

		String[] targetPaths = requestBody.getTargetPaths();
		MultipartFile[] multipartFiles = requestBody.getMultipartFiles();

		for (int i = 0; i < targetPaths.length; i++) {
			responses.add(_uploadFile(applicationId, targetPaths[i], multipartFiles[i]));
		}

		return responses;
	}

	public ResponseEntity<ResultBody> _uploadFile(String applicationId, String targetPath, MultipartFile multipartFile)
			throws IOException {

		localDriveService.uploadSingleFile(applicationId, targetPath, multipartFile);

		return ResponseEntity.status(HttpStatus.OK).body(ResultBody.success());
	}

	@GetMapping(path = "/localDrive/{application-id}/fileNodes/content")
	public List<ResponseEntity<ByteArrayResource>> downloadFileNodes(
			@PathVariable("application-id") String applicationId,
			@RequestParam(value = "targetPaths", required = true) List<String> targetPaths,
			@RequestParam(value = "targetNames", required = true) List<String> targetNames) throws IOException {
		List<ResponseEntity<ByteArrayResource>> responses = new ArrayList<ResponseEntity<ByteArrayResource>>();

		for (int i = 0; i < targetPaths.size(); i++) {
			responses.add(_downloadFileNode(applicationId, targetPaths.get(i), targetNames.get(i)));
		}

		return responses;
	}

	public ResponseEntity<ByteArrayResource> _downloadFileNode(String applicationId, String targetPath,
			String targetName) throws IOException {

		ByteArrayResource byteArrayResource = localDriveService.downloadFileNode(applicationId, targetPath);

		return ResponseEntity.status(HttpStatus.OK)
				.header("Content-Disposition", "attachment; filename=\"" + targetName).body(byteArrayResource);
	}

	@PostMapping(path = "/localDrive/{application-id}/fileNodes/copy")
	public ResponseEntity<ResultBody> copyFileNodes(@PathVariable("application-id") String applicationId,
			@RequestBody RequestInfo requestBody) throws IOException {

		
		String[] sourcePaths = requestBody.getSourcePaths();
		String targetPath = requestBody.getTargetPath();

		localDriveService.copyFileNodes(applicationId, sourcePaths, targetPath);

		return ResponseEntity.status(HttpStatus.OK).body(ResultBody.success());

	}

}
