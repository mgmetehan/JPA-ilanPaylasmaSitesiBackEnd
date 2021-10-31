package com.proje.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import com.proje.model.User;
import com.proje.model.UserDetail;
import com.proje.model.UserInfo;
import com.proje.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {

	private EntityManager entityManager = entityFactory.getEntityManager();

	private EntityTransaction entityTransaction = this.entityManager.getTransaction();

	@Override
	public boolean saveUser(final User user) {

		try {
			this.entityTransaction.begin();

			this.entityManager.persist(user);

			this.entityTransaction.commit();
		} catch (RuntimeException e) {
			System.err.println("User kaydelirken hata.Hata: " + e);

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
	public boolean updateUser(final User user) {

		try {
			this.entityTransaction.begin();

			this.entityManager.merge(user);

			this.entityManager.flush();

			this.entityTransaction.commit();
		} catch (RuntimeException e) {
			System.err.println("User güncellenirken hata.Hata: " + e);

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
	public boolean removeUser(final User user) {

		try {
			if (this.entityManager.contains(user)) {
				this.entityManager.remove(user);
			} else {
				User deleteUser = this.findById(user.getUserId());
				this.entityManager.remove(deleteUser);
			}
		} catch (RuntimeException e) {
			System.err.println("User silinemedi hata.Hata: " + e);

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
	public User findById(final Integer userId) {

		User users = null;

		try {
			users = this.entityManager.find(User.class, userId);
		} catch (NoResultException e) {
			System.err.println("UserDetail bulunamadý Hata: " + e);
		}
		return users;

	}

	@Override
	public User findByUsername(final String username) {
		User users = null;

		try {
			TypedQuery<User> typeQuery = this.entityManager.createNamedQuery("User.findByUsername", User.class);
			typeQuery.setParameter("username", username);

			users = typeQuery.getSingleResult();
		} catch (NoResultException e) {
			System.err.println("User bulunamadý Hata: " + e);
		}
		return users;
	}

	@Override
	public User findWithUserDetailByUsername(final String username) {
		User users = null;

		try {
			TypedQuery<User> typeQuery = this.entityManager.createNamedQuery("User.findWithUserDetailByUsername",
					User.class);
			typeQuery.setParameter("username", username);

			users = typeQuery.getSingleResult();
		} catch (NoResultException e) {
			System.err.println(users + "kullanýcý adlý kullanýcý bulunamadý Hata: " + e);
		}
		return users;
	}

	@Override
	public List<User> findUsers() {

		List<User> users = null;

		try {
			TypedQuery<User> typeQuery = this.entityManager.createNamedQuery("User.findUsers", User.class);

			users = typeQuery.getResultList();
		} catch (NoResultException e) {
			System.err.println("Kullanýcý listesi bulunamadý Hata: " + e);
		}
		return users;
	}

	@Override
	public List<User> findUsersEntities(int firstResult, int maxResult) {

		List<User> users = null;

		try {
			TypedQuery<User> typeQuery = this.entityManager.createNamedQuery("User.findUsers", User.class);
			typeQuery.setFirstResult(firstResult);
			typeQuery.setMaxResults(maxResult);

			users = typeQuery.getResultList();
		} catch (NoResultException e) {
			System.err.println("Kullanýcý listesi bulunamadý Hata: " + e);
		}
		return users;

	}

	@Override
	public int findUserCount() {
		int count = 0;
		try {
			Query query = this.entityManager.createNamedQuery("User.count");

			count = (int) query.getSingleResult();
		} catch (NoResultException e) {
			System.err.println("Kullanýcý sayýsý bulunamadý.Hata: " + e);
		}
		return count;
	}

	@Override
	public UserInfo findUserInfoByUsername(final String username) {
		UserInfo userInfo = null;

		try {
			TypedQuery<UserInfo> typeQuery = this.entityManager.createNamedQuery("User.findUserInfoByUsermane",
					UserInfo.class);
			typeQuery.setParameter("username", username);

			userInfo = typeQuery.getSingleResult();
		} catch (NoResultException e) {
			System.err.println("kullanýcý bilgileri bulunamadý Hata: " + e);
		}
		return userInfo;
	}

}
