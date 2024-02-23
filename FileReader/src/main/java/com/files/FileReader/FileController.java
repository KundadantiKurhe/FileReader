package com.files.FileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.file.CSVDemoservice.FileService;
import com.files.FileReader.response.ResponseMessage;

@RestController
@RequestMapping("files")
public class FileController {

	@Autowired
	private FileService service;

	@RequestMapping(path="feedCustomerData")
	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		if (service.hasCsvFormat(file)) {
			service.processAndSaveData(file);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("uploaded the file successfully : " + file.getOriginalFilename() ));

		}
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Please upload csv file"));
	}
}
