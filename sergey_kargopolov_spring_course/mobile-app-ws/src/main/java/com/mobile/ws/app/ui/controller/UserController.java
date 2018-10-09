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

import com.mobile.ws.app.exceptions.UserServiceExceptions;
import com.mobile.ws.app.service.UserService;
import com.mobile.ws.app.shared.dto.UserDto;
import com.mobile.ws.app.ui.model.request.UserDetailsRequestModel;
import com.mobile.ws.app.ui.model.response.ErrorMessages;
import com.mobile.ws.app.ui.model.response.OperationStatusModel;
import com.mobile.ws.app.ui.model.response.RequestOperationStatus;
import com.mobile.ws.app.ui.model.response.UserRest;

@RestController
@RequestMapping("users") // http://localhost:8080/users
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserRest getUser(@PathVariable String id) {
		UserRest returnedValue = new UserRest();

		UserDto userToGet = userService.getUserId(id);

		if (userToGet == null)
			throw new UserServiceExceptions(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		BeanUtils.copyProperties(userToGet, returnedValue);

		return returnedValue;

//		return "get user was called";

	}

	@PostMapping(

			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, consumes = {
					MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })

	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {

		if (userDetails.getFirstName().isEmpty() || userDetails.getLastName().isEmpty()
				|| userDetails.getEmail().isEmpty() || userDetails.getPassword().isEmpty())
			throw new UserServiceExceptions(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		UserRest returnedValue = new UserRest();
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);

		UserDto userToCreate = userService.createUser(userDto);
		BeanUtils.copyProperties(userToCreate, returnedValue);

		return returnedValue;
	}

	@PutMapping(path = { "/{id}" }, produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) {

		UserRest response = new UserRest();
		UserDto userToUpdate = new UserDto();
		BeanUtils.copyProperties(userDetails, userToUpdate);

		userToUpdate = userService.updateUser(id, userToUpdate);

		BeanUtils.copyProperties(userToUpdate, response);

		return response;
	}

	@DeleteMapping(path = { "/{id}" }, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel deleteUser(@PathVariable String id) {

		userService.deleteUser(id);
		// To handle delete process status
		OperationStatusModel responseValue = new OperationStatusModel();

		responseValue.setOpertionName(RequestOperationName.DELETE.name());
		responseValue.setOperationResult(RequestOperationStatus.SUCCESS.name());

		return responseValue;
	}

}
