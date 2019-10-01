package fileBrowserAPI.Controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fileBrowserAPI.Entities.RemoteResourceModel;
import fileBrowserAPI.Service.StorageOperatorService.FTPService;

@RestController
public class FTPController {
	
	@Autowired
	private FTPService ftpService;
	
	@RequestMapping(value = "/fileBrowser/ftpFiles/authentication", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody Map<String, String> requestBody) {
		boolean result = false;
		try {
			result = this.ftpService.login(requestBody.get("host"),
					 	Integer.parseInt(requestBody.get("port")),
					 	requestBody.get("username"),
					 	requestBody.get("password"));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if (result == true) {
			return new ResponseEntity<String>("Successfully LOGIN",
					new HttpHeaders(), HttpStatus.OK);
			
		} else {
			return ResponseEntity
		            .status(HttpStatus.FORBIDDEN)
		            .body("Cannot Connect");
		}
		
	}
	
	@RequestMapping(value = "/fileBrowser/ftpFiles/{resourceId}", method = RequestMethod.POST)
	public ResponseEntity<?> uploadResource(@PathVariable String resourceId, @RequestParam("file") MultipartFile uploadFile, @RequestParam("remoteResourcePath") String remoteResourcePath) {
		
		boolean result = false;
		if (uploadFile.isEmpty()) {
			return new ResponseEntity<String>("please select a file!", HttpStatus.OK);
		}
		
		try {
			result = ftpService.uploadFileResource(remoteResourcePath, uploadFile.getInputStream());
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if (result == true) {
			return new ResponseEntity<String>("Successfully uploaded - " + uploadFile.getOriginalFilename(),
					new HttpHeaders(), HttpStatus.OK);
			
		} else {
			return ResponseEntity
		            .status(HttpStatus.FORBIDDEN)
		            .body("Upload Failed");
		}
		
		
		
	}
	
	// https://stackoverflow.com/questions/50026141/download-file-with-rest-api
	@RequestMapping(value = "/fileBrowser/ftpFiles/{resourceId}/content", method = RequestMethod.GET)
	public ResponseEntity<byte[]> downloadResource(@PathVariable String resourceId, @RequestParam("localResourcePath") String localResourcePath, @RequestParam("remoteResourcePath") String remoteResourcePath) {
		byte[] content;
		try {
			content = ftpService.downloadFileResource(remoteResourcePath, localResourcePath);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(content!=null) {
			return ResponseEntity.ok()
	                .contentLength(content.length)
	                .body(content);
		} else {
			return ResponseEntity
		            .status(HttpStatus.BAD_REQUEST)
		            .body(content);
		}
		
	}
	
}
