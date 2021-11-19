package com.demo.upload.in;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.upload.util.ConvertorHelper;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping(value = "/upload")
	public User upload(@RequestParam("user") String user, @RequestPart("filename") List<MultipartFile> file) {

		System.out.println("UserController.upload()" + user);
		User userJson = new User();
		userJson.setFirstName("Dummy");

		if (user != null && (user.trim().length() != 0)) {
			userJson = userService.getJson(user, file);
		}

		if ((file != null) && (!file.isEmpty())) {
			for (MultipartFile multipartFile : file) {
				System.out.println("UserController.upload(0) " + multipartFile.getSize());
				System.out.println("UserController.upload(1) " + ConvertorHelper.bytesIntoHumanReadable(multipartFile.getSize()));
				System.out.println("UserController.upload(2) " + multipartFile.getName());
				System.out.println("UserController.upload(3) " + multipartFile.getOriginalFilename());
			}
		}
		return userJson;
	}

	@PostMapping(value = "/upload2", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public void upload2(@RequestPart("filename") List<MultipartFile> file) {

		if ((file != null) && (!file.isEmpty())) {
			for (MultipartFile multipartFile : file) {
				multipartFile.getSize();
				multipartFile.getName();
				System.out.println("UserController.upload(1) " + multipartFile.getSize());
				System.out.println("UserController.upload(2) " + multipartFile.getName());
				System.out.println("UserController.upload(3) " + multipartFile.getOriginalFilename());
			}
		}
	}
}
