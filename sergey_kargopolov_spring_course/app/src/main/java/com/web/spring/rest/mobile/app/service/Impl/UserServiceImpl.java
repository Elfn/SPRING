package com.web.spring.rest.mobile.app.service.Impl;

import com.web.spring.rest.mobile.app.io.entity.UserEntity;
import com.web.spring.rest.mobile.app.repository.UserRepository;
import com.web.spring.rest.mobile.app.service.UserService;
import com.web.spring.rest.mobile.app.shared.dto.UserDTO;
import com.web.spring.rest.mobile.app.shared.util.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by Elimane on Sep, 2018, at 04:01
 */

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDTO, userEntity);

        //Checking if user already exist
        if (userRepository.findUserByEmail(userDTO.getEmail()) != null)
            throw new RuntimeException("Record already exist!!!");


        //we cant add this data on postman
        //We have to put it there  else we'll get 500 error
        //data which are auto-generated
        String userId = utils.generateUserId(30);
        userEntity.setEncryptedPassword(passwordEncoder.encode(userDTO.getPassword()));
        userEntity.setUserId(userId);

        UserEntity userStored = userRepository.save(userEntity);
        UserDTO userToReturn = new UserDTO();
        BeanUtils.copyProperties(userStored, userToReturn);

        return userToReturn;
    }

    @Override
    public UserDTO getUser(String email) {


        if (userRepository.findUserByEmail(email) == null) throw new RuntimeException("User not found!!!");

        UserDTO userDTO = new UserDTO();
        UserEntity userEntity = userRepository.findUserByEmail(email);

        BeanUtils.copyProperties(userEntity, userDTO);

        return userDTO;
    }

    //Allow to access user informations for authentication
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findUserByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException(email);

        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }
}
