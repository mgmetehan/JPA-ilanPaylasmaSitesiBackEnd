package com.proje.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import com.proje.model.UserDetail;
import com.proje.repository.UserDetailRepository;

public class UserDetailRepositoryImpl implements UserDetailRepository {

	private EntityManager entityManager = entityFactory.getEntityManager();

	private EntityTransaction entityTransaction = this.entityManager.getTransaction();

	@Override
	public boolean saveUserDetail(final UserDetail userDetail) {

		try {
			this.entityTransaction.begin();

			this.entityManager.persist(userDetail);

			this.entityTransaction.commit();
		} catch (RuntimeException e) {
			System.err.println("UserDetial kaydelirken hata.Hata: " + e);

			try {
				this.entityTransaction.rollback();
			} catch (RollbackException e2) {
				System.err.println("Rollback yapýlýrken hata.Hata: " + e2);
			}
			return false;
		}
		return true;
	}

	@Override
	public boolean updateUserDetail(final UserDetail userDetail) {

		try {
			this.entityTransaction.begin();

			this.entityManager.merge(userDetail);

			this.entityManager.flush();

			this.entityTransaction.commit();
		} catch (RuntimeException e) {
			System.err.println("UserDetial güncellenirken hata.Hata: " + e);

			try {
				this.entityTransaction.rollback();
			} catch (RollbackException e2) {
				System.err.println("Rollback yapýlýrken hata.Hata: " + e2);
			}
			return false;
		}
		return true;
	}

	@Override
	public boolean removeUserDetail(final UserDetail userDetail) {

		try {
			if (this.entityManager.contains(userDetail)) {
				this.entityManager.remove(userDetail);
			} else {
				UserDetail removeUserDetail = this.findById(userDetail.getUserDetailId());
				this.entityManager.remove(removeUserDetail);
			}
		} catch (RuntimeException e) {
			System.err.println("UserDetial silinemedi hata.Hata: " + e);

			try {
				this.entityTransaction.rollback();
			} catch (RollbackException e2) {
				System.err.println("Rollback yapýlýrken hata.Hata: " + e2);
			}
			return false;
		}
		return true;
	}

	@Override
	public UserDetail findById(final Integer userDetailId) {
		UserDetail userDetail = null;

		try {
			userDetail = this.entityManager.find(UserDetail.class, userDetailId);
		} catch (NoResultException e) {
			System.err.println("UserDetail bulunamadý Hata: " + e);
		}
		return userDetail;
	}

	@Override
	public UserDetail findByUsername(final String username) {
		UserDetail userDetail = null;

		try {
			TypedQuery<UserDetail> typeQuery = this.entityManager.createNamedQuery("UserDetail.findByUserName",
					UserDetail.class);
			typeQuery.setParameter("username", username);

			userDetail = typeQuery.getSingleResult();
		} catch (NoResultException e) {
			System.err.println("UserDetail bulunamadý Hata: " + e);
		}
		return userDetail;
	}
	@Override
	public UserDetail findWithAddressByUsername(final String username) {

		UserDetail userDetail = null;

		try {
			TypedQuery<UserDetail> typeQuery = this.entityManager
					.createNamedQuery("UserDetail.findWithAddressByUserName", UserDetail.class);
			typeQuery.setParameter("username", username);

			userDetail = typeQuery.getSingleResult();
		} catch (NoResultException e) {
			System.err.println("UserDetail bulunamadý Hata: " + e);
		}
		return userDetail;

	}
	@Override
	public UserDetail findWithAdvertisementByUsername(final String username) {

		UserDetail userDetail = null;

		try {
			TypedQuery<UserDetail> typeQuery = this.entityManager
					.createNamedQuery("UserDetail.findWithAdvertisementByUserName", UserDetail.class);
			typeQuery.setParameter("username", username);

			userDetail = typeQuery.getSingleResult();
		} catch (NoResultException e) {
			System.err.println("UserDetail bulunamadý Hata: " + e);
		}
		return userDetail;

	}

}
