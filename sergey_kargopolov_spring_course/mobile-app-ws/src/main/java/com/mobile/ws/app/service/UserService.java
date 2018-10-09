package com.mobile.ws.app.service;

import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.mobile.ws.app.shared.dto.UserDto;

public interface UserService extends UserDetailsService{
	
	UserDto createUser(UserDto userdto);
	UserDto getUser(String email);
	UserDto getUserId(String id);
	UserDto updateUser(String id, UserDto user);
	List<UserDto> getUsers(int page, int limit);
	void deleteUser(String id);

}
