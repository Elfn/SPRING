package com.mobile.ws.app.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.ws.app.service.UserService;
import com.mobile.ws.app.shared.dto.UserDto;
import com.mobile.ws.app.ui.model.request.UserDetailsRequestModel;
import com.mobile.ws.app.ui.model.response.UserRest;

@RestController
@RequestMapping("users") // http://localhost:8080/users
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public UserRest getUser(@PathVariable String id) {
		UserRest returnedValue = new UserRest();

		UserDto userToGet = userService.getUserId(id);
		BeanUtils.copyProperties(userToGet, returnedValue);

		return returnedValue;

//		return "get user was called";

	}

	@PostMapping(
			
			produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
			consumes= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}
	)

	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
		UserRest returnedValue = new UserRest();
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);

		UserDto userToCreate = userService.createUser(userDto);
		BeanUtils.copyProperties(userToCreate, returnedValue);

		return returnedValue;
	}

	@PutMapping
	public String updateUser() {
		return " Update user was called";
	}

	@DeleteMapping
	public String deleteUser() {
		return " Update user was called";
	}

}
