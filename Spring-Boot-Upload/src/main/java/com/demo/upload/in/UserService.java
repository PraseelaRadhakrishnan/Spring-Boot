package com.demo.upload.in;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserService {

	public User getJson(String user, List<MultipartFile> file) {

		User userJson = new User();
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			userJson = objectMapper.readValue(user, User.class);
		} catch (IOException err) {
			System.out.printf("Error", err.toString());
		}
		
		int fileCount = file.size();
		userJson.setCount(fileCount);
		
		return userJson;

	}

}
