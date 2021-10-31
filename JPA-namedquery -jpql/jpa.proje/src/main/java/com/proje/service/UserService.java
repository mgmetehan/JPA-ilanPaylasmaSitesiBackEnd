package com.proje.service;

import java.util.List;

import com.proje.model.User;
import com.proje.model.UserInfo;

public interface UserService {
	boolean saveUser(User user);

	boolean updateUser(User user);

	boolean removeUser(User user);

	User findById(Integer id);

	User findByUsername(String username);

	User findWithUserDetailByUsername(String username);

	List<User> findUsers();

	List<User> findUsersEntities(int firstResult, int maxResult);

	int findUserCount();

	UserInfo findUserInfoByUsername(String username);
}
