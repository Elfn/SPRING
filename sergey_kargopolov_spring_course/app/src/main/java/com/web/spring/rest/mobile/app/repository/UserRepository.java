package com.web.spring.rest.mobile.app.repository;

import com.web.spring.rest.mobile.app.io.entity.UserEntity;
import com.web.spring.rest.mobile.app.ui.model.response.UserRest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Elimane on Sep, 2018, at 04:00
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    public UserEntity findUserByEmail(String email);
}
