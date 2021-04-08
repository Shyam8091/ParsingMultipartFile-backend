package com.example.demo.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	Scanner scanner;
	String fileHeader;
	HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
	String[] fileHeaderArray;
	int indexOfFirstName = 0;
	int indexOfLastName = 0;
	int indexOfAge = 0;
	int indexOfMobileNumber = 0;

	@Override
	public Map<String, Integer> readMultipartFileFirstWay(MultipartFile file) {
		try {
			scanner = new Scanner(file.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

		fileHeader = scanner.nextLine();
		fileHeaderArray = fileHeader.split(",");
		for (int i = 0; i < fileHeaderArray.length; i++) {
			if (fileHeaderArray[i].equalsIgnoreCase("firstName")) {
				indexOfFirstName = i;
			} else if (fileHeaderArray[i].equalsIgnoreCase("lastName")) {
				indexOfLastName = i;
			} else if (fileHeaderArray[i].equalsIgnoreCase("age")) {
				indexOfAge = i;
			} else if (fileHeaderArray[i].equalsIgnoreCase("mobileNumber")) {
				indexOfMobileNumber = i;
			}
		}

		while (scanner.hasNext()) {
			String rowData = scanner.nextLine();
			String[] rowDataArray = rowData.split(",");
			System.out.println("FirstName : " + rowDataArray[indexOfFirstName] + " Age : " + rowDataArray[indexOfAge]);
			hashMap.put(rowDataArray[indexOfFirstName], Integer.parseInt(rowDataArray[indexOfAge]));
		}
		scanner.close();
		return hashMap;
	}

}
