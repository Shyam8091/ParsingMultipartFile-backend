package com.example.demo.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

	/**
	 * this method read the CSV file using Scanner and find the index of each
	 * desired header column I will recommend the readMultipartFileSecondWay method
	 * as that uses Collection
	 */
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

	@Override
	public Map<String, Integer> readMultipartFileSecondWay(MultipartFile file) {
		try {
			scanner = new Scanner(file.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

		/**
		 * If we have any special character in header we can replace it using regex
		 * String uploadedFileRowData = scanner.nextLine().replaceAll("\\.", "_");
		 */

		String uploadedFileRowData = scanner.nextLine();
		String[] uploadedFileRowDataArray = uploadedFileRowData.split(",");
		ArrayList<String> arrayListOfHeader = new ArrayList<String>(Arrays.asList(uploadedFileRowDataArray));
		while (scanner.hasNext()) {
			StringBuilder rowData = new StringBuilder(scanner.nextLine());
			String[] rowDataArray = rowData.toString().split(",");
			System.out.println("FirstName : " + rowDataArray[arrayListOfHeader.indexOf("FirstName")] + " Age : "
					+ rowDataArray[arrayListOfHeader.indexOf("Age")]);
			hashMap.put(rowDataArray[arrayListOfHeader.indexOf("FirstName")],
					Integer.parseInt(rowDataArray[arrayListOfHeader.indexOf("Age")]));
		}
		scanner.close();
		return hashMap;
	}

}
