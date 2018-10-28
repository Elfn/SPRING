package com.mobile.ws.app.service.impl;

import com.mobile.ws.app.exceptions.UserServiceExceptions;
import com.mobile.ws.app.io.entity.AddressEntity;
import com.mobile.ws.app.io.entity.UserEntity;
import com.mobile.ws.app.repository.AddressRepository;
import com.mobile.ws.app.repository.UserRepository;
import com.mobile.ws.app.service.AddressService;
import com.mobile.ws.app.shared.dto.AddressDto;
import com.mobile.ws.app.ui.model.response.ErrorMessages;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elimane on Oct, 2018, at 17:17
 */

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<AddressDto> getAddresses(String userId) {

        UserEntity userToFind = userRepository.findUserByUserId(userId);

        if(userToFind == null) throw new UserServiceExceptions(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        List<AddressEntity> addressList = addressRepository.findAllByUserDetails(userToFind);
        List<AddressDto> addressDtoList = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        addressList.stream()
                .filter(addressEntity -> addressEntity != null)
                .forEach(addressEntity -> {

            AddressDto addressDto = modelMapper.map(addressEntity, AddressDto.class);
            addressDtoList.add(addressDto);

        });

        return addressDtoList;
    }

    @Override
    public AddressDto getAddress(String addressId) {

        AddressEntity addressEntity = addressRepository.findByAddressId(addressId);

        if(addressEntity == null) throw new UserServiceExceptions(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());


        AddressDto addressToReturn = new ModelMapper().map(addressEntity, AddressDto.class);

        return addressToReturn;
    }
}
