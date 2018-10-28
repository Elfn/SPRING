package com.mobile.ws.app.repository;

import com.mobile.ws.app.io.entity.AddressEntity;
import com.mobile.ws.app.io.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Elimane on Oct, 2018, at 17:10
 */
public interface AddressRepository extends CrudRepository<AddressEntity, Long> {

    public List<AddressEntity> findAllByUserDetails(UserEntity userEntity);
    public  AddressEntity findByAddressId(String id);

}
