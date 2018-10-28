package com.mobile.ws.app.ui.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mobile.ws.app.service.AddressService;
import com.mobile.ws.app.shared.dto.AddressDto;
import com.mobile.ws.app.ui.model.response.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.ws.app.exceptions.UserServiceExceptions;
import com.mobile.ws.app.service.UserService;
import com.mobile.ws.app.shared.dto.UserDto;
import com.mobile.ws.app.ui.model.request.UserDetailsRequestModel;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/users") // http://localhost:8080/users
public class  UserController {

	@Autowired
	UserService userService;

	@Autowired
	AddressService addressService;

	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserRest getUser(@PathVariable String id) {
		UserRest returnedValue = new UserRest();

		UserDto userToGet = userService.getUserId(id);

		if (userToGet == null)
			throw new UserServiceExceptions(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		BeanUtils.copyProperties(userToGet, returnedValue);


		//Here we are using linkto userController class to make spring framework
		//inspect root mapping (/users) of UserController class and include it into link's
		//url
		//withSelfRel() is to create relashionship between the link and the getUserAddress() method
		Link addressLink = linkTo(UserController.class).withSelfRel();


		//Here we are relating our link to addressRest object
		returnedValue.add(addressLink);

		return returnedValue;

//		return "get user was called";

	}

	@PostMapping(

			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, "application/hal+json" }, consumes = {
					MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })

	public Resource<UserRest> createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {

		if (userDetails.getFirstName().isEmpty() || userDetails.getLastName().isEmpty()
				|| userDetails.getEmail().isEmpty() || userDetails.getPassword().isEmpty())
			throw new UserServiceExceptions(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		
		ModelMapper modelMapper = new ModelMapper();
		
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		

		UserDto userToCreate = userService.createUser(userDto);
		UserRest returnedValue = modelMapper.map(userToCreate, UserRest.class);

		//Here we are using linkto userController class to make spring framework
		//inspect root mapping (/users) of UserController class and include it into link's
		//url
		//withSelfRel() is to create relashionship between the link and the getUserAddress() method
		Link addressLink = linkTo(UserController.class).withSelfRel();


		//Here we are relating our link to addressRest object
		returnedValue.add(addressLink);

		return new Resource<>(returnedValue);
	}

	@PutMapping(path = { "/{id}" }, produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE, "application/hal+json" }, consumes = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public Resource<UserRest> updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) {

		
	ModelMapper modelMapper = new ModelMapper();
	UserDto userToUpdate = modelMapper.map(userDetails, UserDto.class);
				

		userToUpdate = userService.updateUser(id, userToUpdate);

		UserRest response = modelMapper.map(userToUpdate, UserRest.class);

		//Here we are using linkto userController class to make spring framework
		//inspect root mapping (/users) of UserController class and include it into link's
		//url
		//withSelfRel() is to create relashionship between the link and the getUserAddress() method
		Link addressLink = linkTo(UserController.class).slash(id).withSelfRel();

		//Here we are relating our link to addressRest object
		response.add(addressLink);

		return new Resource<>(response);
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

	@GetMapping(path = { "all" }, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE ,"application/hal+json"})
	public Resources<UserRest> getUsers(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "2") int limit) {
		
		List<UserRest> valueToReturn = new ArrayList<>();
		List<UserDto> users = userService.getUsers(page, limit);

		Link userLink = linkTo(UserController.class).slash("all").withRel("users");

		users.forEach(userDto -> {
			UserRest newRestUser = new UserRest();
			BeanUtils.copyProperties(userDto, newRestUser);
			newRestUser.add(userLink);
			valueToReturn.add(newRestUser);
		});

		return new Resources<>(valueToReturn);

	}
    //Resources<T> or Resources<T> are hateoas libraries which allows us to make our JSON response
    // wrapped by "_embedded" object in postman or on client screen
	@GetMapping(path = { "/addresses/{id}" }, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE ,"application/hal+json"})
    //General helper to easily create a wrapper for a collection of entities
    public Resources<AddressRest> getAddresses(@PathVariable String id)
	{
		List<AddressDto> addressDtos = addressService.getAddresses(id);
        List<AddressRest> addressRests = new ArrayList<>();

		//ModelMapper uses TypeTokens to allow mapping of generic parameterized types.
		//
        if(addressDtos != null && !addressDtos.isEmpty()) {
            Type listType = new TypeToken<List<AddressRest>>() {
            }.getType();
            addressRests = new ModelMapper().map(addressDtos, listType);


            for (AddressRest addressRest : addressRests) {
                addressRest.add(linkTo(UserController.class).slash("addresses").slash(addressRest.getAddressId()).withRel("addresses"));
            }
        }
		return new Resources<>(addressRests);
	}

	@GetMapping(path  =  "/addresses/{userId}/{addressId}" , produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, "application/hal+json" })
    //A simple Resource wrapping a domain object and adding links to it.
    public Resource<AddressRest> getUserAddress(@PathVariable String userId, @PathVariable String addressId)
	{
		AddressDto addressDto = addressService.getAddress(addressId);
		AddressRest addressRest = new ModelMapper().map(addressDto, AddressRest.class);

		//Here we are using linkto userController class to make spring framework
		//inspect root mapping (/users) of UserController class and include it into link's
		//url
		//withSelfRel() is to create relashionship between the link and the getUserAddress() method
		Link addressLink = linkTo(methodOn(UserController.class).getUserAddress(userId,addressId)).withSelfRel();
		Link userLink = linkTo(UserController.class).slash(userId).withRel("user");
        Link addressesLink = linkTo(methodOn(UserController.class).getAddresses(userId)).withRel("addresses");



        //Here we are relating our link to addressRest object
		addressRest.add(addressLink);
		addressRest.add(userLink);
		addressRest.add(addressesLink);

		return new Resource<>(addressRest);
	}

}

//Exple hateoas link :
//        if (!student.isPresent())
//        throw new StudentNotFoundException("id-" + id);
//
//        Resource<Student> resource = new Resource<Student>(student.get());
//
//        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllStudents());
//
//        resource.add(linkTo.withRel("all-students"));
//
//        return resource;