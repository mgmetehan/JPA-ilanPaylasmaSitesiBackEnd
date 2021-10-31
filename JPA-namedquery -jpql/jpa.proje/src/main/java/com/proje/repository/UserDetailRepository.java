package com.proje.repository;

import com.proje.entityFactory.EntityFactory;
import com.proje.entityFactory.impl.EntityFactoryImpl;
import com.proje.model.UserDetail;

public interface UserDetailRepository {

	EntityFactory entityFactory = new EntityFactoryImpl();

	boolean saveUserDetail(UserDetail userDetail);

	boolean updateUserDetail(UserDetail userDetail);

	boolean removeUserDetail(UserDetail userDetail);

	UserDetail findById(Integer id);

	UserDetail findByUsername(String username);

	UserDetail findWithAddressByUsername(String username);

	UserDetail findWithAdvertisementByUsername(String username);
}
