package com.mobile.ws.app.service;

import com.mobile.ws.app.shared.dto.AddressDto;

import java.util.List;

/**
 * Created by Elimane on Oct, 2018, at 17:16
 */
public interface AddressService {

    public List<AddressDto> getAddresses(String id);
    public AddressDto getAddress(String addressId);
}
