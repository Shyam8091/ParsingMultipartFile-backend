package com.example.demo.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	public abstract Map<String, Integer> readMultipartFileFirstWay(MultipartFile file);
	public abstract Map<String, Integer> readMultipartFileSecondWay(MultipartFile file);
}
