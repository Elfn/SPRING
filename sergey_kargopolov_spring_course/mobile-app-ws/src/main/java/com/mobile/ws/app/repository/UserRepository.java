package com.mobile.ws.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mobile.ws.app.io.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long>{
	
	UserEntity findByEmail(String email);
	UserEntity findUserByUserId(String id);
	void delete(UserEntity entity);

}
