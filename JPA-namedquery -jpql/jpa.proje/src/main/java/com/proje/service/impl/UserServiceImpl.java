package com.proje.service.impl;

import java.util.List;

import com.proje.model.User;
import com.proje.model.UserInfo;
import com.proje.repository.UserRepository;
import com.proje.repository.impl.UserRepositoryImpl;
import com.proje.service.UserService;

public class UserServiceImpl implements UserService {

	private UserRepository userRepository = new UserRepositoryImpl();

	@Override
	public boolean saveUser(final User user) {
		return this.userRepository.saveUser(user);
	}

	@Override
	public boolean updateUser(final User user) {
		return this.userRepository.updateUser(user);
	}

	@Override
	public boolean removeUser(final User user) {
		return this.userRepository.removeUser(user);
	}

	@Override
	public User findById(final Integer id) {
		return this.userRepository.findById(id);
	}

	@Override
	public User findByUsername(final String username) {
		return this.userRepository.findByUsername(username);
	}

	@Override
	public User findWithUserDetailByUsername(final String username) {
		return this.userRepository.findWithUserDetailByUsername(username);
	}

	@Override
	public List<User> findUsers() {
		return this.userRepository.findUsers();
	}

	@Override
	public List<User> findUsersEntities(final int firstResult, final int maxResult) {
		return this.userRepository.findUsersEntities(firstResult, maxResult);
	}

	@Override
	public int findUserCount() {
		return this.userRepository.findUserCount();
	}

	@Override
	public UserInfo findUserInfoByUsername(final String username) {
		return this.userRepository.findUserInfoByUsername(username);
	}

}
