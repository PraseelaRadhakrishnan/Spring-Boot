package com.demo.controller;

import com.demo.model.User;
import com.demo.model.UserDto;
import com.demo.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Get list of User in the System", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })

	// @Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> listUser() {
		return userService.findAll();
	}

	@ApiOperation(value = "Get specific User in the System ")

	// @Secured("ROLE_USER")
	@PreAuthorize("hasRole('USER')")
	//// @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public User getOne(@PathVariable(value = "id") Long id) {
		return userService.findById(id);
	}

	@ApiOperation(value = "Get specific User in the System ")

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public User saveUser(@RequestBody UserDto user) {
		return userService.save(user);
	}

}
