package com.web.spring.rest.mobile.app.ui.controller;

import com.web.spring.rest.mobile.app.service.UserService;
import com.web.spring.rest.mobile.app.shared.dto.UserDTO;
import com.web.spring.rest.mobile.app.ui.model.request.UserDetailsRequestModel;
import com.web.spring.rest.mobile.app.ui.model.response.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Elimane on Sep, 2018, at 23:38
 */
@RestController
@RequestMapping("users")//http://localhost:8080/users
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public String getUser() {
        return "get user was called";
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {

        //BeanUtils Copy the property values of the given source bean into the target bean.

        UserRest returnValue = new UserRest();
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDetails, userDTO);


        UserDTO createdUser = userService.createUser(userDTO);
        BeanUtils.copyProperties(createdUser, returnValue);

        return returnValue;
    }

    @PutMapping
    public String updateUser() {
        return "update user was called";
    }

    @DeleteMapping
    public String deleteUser() {
        return "delete user was called";
    }
}
