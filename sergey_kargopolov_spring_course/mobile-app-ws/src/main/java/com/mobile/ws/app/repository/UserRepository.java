package com.mobile.ws.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mobile.ws.app.io.entity.UserEntity;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long>{
	
	UserEntity findByEmail(String email);
	UserEntity findUserByUserId(String id);
	void delete(UserEntity entity);
	List<UserEntity> findAll();

}
