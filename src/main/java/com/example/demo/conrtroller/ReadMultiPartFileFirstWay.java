package com.example.demo.conrtroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.FileService;

@RestController
@RequestMapping("/")
public class ReadMultiPartFileFirstWay {

	@Autowired
	FileService fileService;

	@GetMapping("/myFile")
	public ResponseEntity<Map<String, Integer>> readMultipartFile(@RequestParam(required = false) MultipartFile file) {
		if (!(file instanceof MultipartFile)) {
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("File Not Found ", 400);
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(fileService.readMultipartFileFirstWay(file), HttpStatus.OK);
	}

}
