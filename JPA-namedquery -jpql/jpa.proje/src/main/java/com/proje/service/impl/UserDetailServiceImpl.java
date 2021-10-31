package com.proje.service.impl;

import com.proje.model.UserDetail;
import com.proje.repository.UserDetailRepository;
import com.proje.repository.impl.UserDetailRepositoryImpl;
import com.proje.service.UserDetailService;

public class UserDetailServiceImpl implements UserDetailService {

	private UserDetailRepository userDetailRepository = new UserDetailRepositoryImpl();

	@Override
	public boolean saveUserDetail(final UserDetail userDetail) {
		return this.userDetailRepository.saveUserDetail(userDetail);
	}

	@Override
	public boolean updateUserDetail(final UserDetail userDetail) {
		return this.userDetailRepository.updateUserDetail(userDetail);
	}

	@Override
	public boolean removeUserDetail(final UserDetail userDetail) {
		return this.userDetailRepository.removeUserDetail(userDetail);
	}

	@Override
	public UserDetail findById(final Integer id) {
		return this.userDetailRepository.findById(id);
	}

	@Override
	public UserDetail findByUsername(final String username) {
		return this.userDetailRepository.findByUsername(username);
	}

	@Override
	public UserDetail findWithAddressByUsername(final String username) {
		return this.userDetailRepository.findWithAddressByUsername(username);
	}

	@Override
	public UserDetail findWithAdvertisementByUsername(final String username) {
		return this.userDetailRepository.findWithAdvertisementByUsername(username);
	}

}
