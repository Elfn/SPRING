package com.mobile.ws.app.service.impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mobile.ws.app.io.entity.UserEntity;
import com.mobile.ws.app.repository.UserRepository;
import com.mobile.ws.app.service.UserService;
import com.mobile.ws.app.shared.dto.UserDto;
import com.mobile.ws.app.shared.utils.Utils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	Utils utils;

	@Autowired // Implementation of PasswordEncoder that uses the BCrypt strong hashing
				// function
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto userdto) {

		UserEntity userToCheck = userRepository.findByEmail(userdto.getEmail());

		if (userToCheck != null)
			throw new RuntimeException("User already exist!!!");

		UserDto userToReturn = new UserDto();
		UserEntity userToPersist = new UserEntity();
		BeanUtils.copyProperties(userdto, userToPersist);

		String userPublicId = utils.generateUserId(30);

		// User's password will be encrypted before stored in db
		userToPersist.setEcryptedPassword(bCryptPasswordEncoder.encode(userdto.getPassword()));
		userToPersist.setUserId(userPublicId);
		// userToPersist.setEmailVerificationStatus(false);

		UserEntity userStored = userRepository.save(userToPersist);

		BeanUtils.copyProperties(userStored, userToReturn);

		return userToReturn;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserEntity userAuth = userRepository.findByEmail(email);

		if (userAuth == null)
			throw new UsernameNotFoundException(email);

		return new User(userAuth.getEmail(), userAuth.getEcryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUser(String email) {

		UserEntity userToGet = userRepository.findByEmail(email);

		if (userToGet == null)
			throw new UsernameNotFoundException(email);

		UserDto userToReturn = new UserDto();

		BeanUtils.copyProperties(userToGet, userToReturn);

		return userToReturn;
	}

	@Override
	public UserDto getUserId(String id) {

		UserEntity userToGet = userRepository.findUserByUserId(id);

		if (userToGet == null)
			throw new UsernameNotFoundException(id);

		UserDto userToReturn = new UserDto();

		BeanUtils.copyProperties(userToGet, userToReturn);

		return userToReturn;
	}

}
