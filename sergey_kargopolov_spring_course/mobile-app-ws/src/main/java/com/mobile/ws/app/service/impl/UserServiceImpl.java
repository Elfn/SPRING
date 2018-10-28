package com.mobile.ws.app.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mobile.ws.app.shared.dto.AddressDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mobile.ws.app.exceptions.UserServiceExceptions;
import com.mobile.ws.app.io.entity.UserEntity;
import com.mobile.ws.app.repository.UserRepository;
import com.mobile.ws.app.service.UserService;
import com.mobile.ws.app.shared.dto.UserDto;
import com.mobile.ws.app.shared.utils.Utils;
import com.mobile.ws.app.ui.model.response.ErrorMessages;

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


        ModelMapper modelMapper = new ModelMapper();

        if (userRepository.findByEmail(userdto.getEmail()) != null)
            throw new UserServiceExceptions(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());


        for (int i = 0; i < userdto.getAddresses().size(); i++) {
            AddressDto address = userdto.getAddresses().get(i);
            address.setUserDetails(userdto);
            address.setAddressId(utils.generateAddressId(30));
            userdto.getAddresses().set(i, address);
        }

        UserEntity userToPersist = modelMapper.map(userdto, UserEntity.class);

        String userPublicId = utils.generateUserId(30);

        // User's password will be encrypted before stored in db
        userToPersist.setEcryptedPassword(bCryptPasswordEncoder.encode(userdto.getPassword()));
        userToPersist.setUserId(userPublicId);

        // userToPersist.setEmailVerificationStatus(false);

        UserEntity userStored = userRepository.save(userToPersist);

        UserDto userToReturn = modelMapper.map(userStored, UserDto.class);

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
            throw new UserServiceExceptions(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        UserDto userToReturn = new UserDto();

        BeanUtils.copyProperties(userToGet, userToReturn);

        return userToReturn;
    }

    @Override
    public UserDto getUserId(String id) {

        UserEntity userToGet = userRepository.findUserByUserId(id);

        if (userToGet == null)
            throw new UserServiceExceptions(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        UserDto userToReturn = new UserDto();

        BeanUtils.copyProperties(userToGet, userToReturn);

        return userToReturn;
    }

    @Override
    public UserDto updateUser(String id, UserDto user) {

        UserDto userToReturn = new UserDto();

        UserEntity userToUpdate = userRepository.findUserByUserId(id);

        if (userToUpdate == null)
            throw new UserServiceExceptions(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(user.getPassword());

        UserEntity userUpdated = userRepository.save(userToUpdate);

        BeanUtils.copyProperties(userUpdated, userToReturn);

        return userToReturn;
    }

    @Override
    public void deleteUser(String id) {

        UserDto user = getUserId(id);
        UserEntity userToDelete = new UserEntity();
        BeanUtils.copyProperties(user, userToDelete);

        userRepository.delete(userToDelete);

    }

    @Override
    public List<UserDto> getUsers(int page, int limit) {

        List<UserDto> returnedValue = new ArrayList<>();

        //To always make page number begin at 1 not 0
        page = (page > 0) ? page - 1 : null;

        // Abstract interface for pagination information
        Pageable pageableRequest = PageRequest.of(page, limit);

        // A page is a sublist of a list of objects. It allows gain information
        // about the position of it in the containing entire list
        Page<UserEntity> pages = userRepository.findAll(pageableRequest);

        List<UserEntity> users = pages.getContent();

        users.forEach(userEntity -> {
            UserDto newDto = new UserDto();
            BeanUtils.copyProperties(userEntity, newDto);
            returnedValue.add(newDto);
        });

        return returnedValue;
    }

}
