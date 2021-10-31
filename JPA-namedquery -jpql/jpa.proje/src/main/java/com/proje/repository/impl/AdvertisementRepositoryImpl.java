package com.proje.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import com.proje.model.Advertisement;
import com.proje.repository.AdvertisementRepository;

public class AdvertisementRepositoryImpl implements AdvertisementRepository {

	private EntityManager entityManager = entityFactory.getEntityManager();

	private EntityTransaction entityTransaction = this.entityManager.getTransaction();

	@Override
	public boolean saveAdvertisement(final Advertisement advertisement) {
		// Biz advertisement de�erini set ile de�i�tirmiyece�iz o y�zden final yap�p
		// daha h�zl� �al��mas�n� sa�lad�k

		try {
			this.entityTransaction.begin();

			this.entityManager.persist(advertisement);

			this.entityTransaction.commit();
		} catch (RuntimeException e) {
			System.out.println("Hata: " + e);

			try {
				this.entityTransaction.rollback();
				// E�er i�lemler s�ras�nda bir hata olursa t�m i�lemleri geri �ekmek i�in
				// rollback yapar�z
				// �rnek/bir kullan�c�dan para ��kt� ama kar�� tarafa ula��rken hata oldu
				// rollback yapar�z
			} catch (RollbackException e2) {
				System.out.println("Hata: " + e2);
			}
			return false;
		}

		return true;
	}

	@Override
	public boolean updateAdvertisement(final Advertisement advertisement) {

		try {
			this.entityTransaction.begin();

			this.entityManager.merge(advertisement);

			this.entityManager.flush();

			this.entityTransaction.commit();

		} catch (RuntimeException e) {

			System.out.println("Hata: " + e);

			try {
				this.entityTransaction.rollback();

			} catch (RollbackException e2) {
				System.out.println("Hata: " + e2);
			}
			return false;
		}

		return true;
	}

	@Override
	public boolean removeAdvertisement(final Advertisement advertisement) {
		// biz verileri ekledi�imizde entityManager'in haf�zas�nda bu bilgiler tutuluyor
		// ama eclipsi felan kapat�nca bu bilgiler gidiyor

		try {
			if (this.entityManager.contains(advertisement)) {
				// e�er haf�zada tutuluyorsa true de�er gelir
				this.entityManager.remove(advertisement);
			} else {
				Advertisement deleteAdvertisement = this.findById(advertisement.getAdvertisementId());
			}
		} catch (RuntimeException e) {
			System.out.println("Hata: " + e);

			try {
				this.entityTransaction.rollback();
				// E�er i�lemler s�ras�nda bir hata olursa t�m i�lemleri geri �ekmek i�in
				// rollback yapar�z
				// �rnek/bir kullan�c�dan para ��kt� ama kar�� tarafa ula��rken hata oldu
				// rollback yapar�z
			} catch (RollbackException e2) {
				System.out.println("Hata: " + e2);
			}
			return false;
		}

		return true;
	}

	@Override
	public Advertisement findById(final Integer id) {
		// Biz id de�erini set ile de�i�tirmiyece�iz o y�zden final yap�p
		// daha h�zl� �al��mas�n� sa�lad�k

		Advertisement advertisement = null;

		try {
			TypedQuery<Advertisement> typedQuery = this.entityManager.createNamedQuery("Advertisement.findById",
					Advertisement.class);

			typedQuery.setParameter("advertisementId", id);

			advertisement = typedQuery.getSingleResult();

		} catch (NoResultException e) {
			System.out.println("Hata: " + e);
		}
		return advertisement;
	}

	@Override
	public List<Advertisement> findByUsername(final String username) {
		List<Advertisement> advertisement = null;

		try {
			TypedQuery<Advertisement> typedQuery = this.entityManager.createNamedQuery("Advertisement.findByUsername",
					Advertisement.class);

			typedQuery.setParameter("username", username);

			advertisement = typedQuery.getResultList();

		} catch (NoResultException e) {
			System.out.println("Hata: " + e);
		}
		return advertisement;
	}

	@Override
	public List<Advertisement> findAdvertisements() {
		List<Advertisement> advertisement = null;

		try {
			TypedQuery<Advertisement> typedQuery = this.entityManager
					.createNamedQuery("Advertisement.findAdvertisements", Advertisement.class);

			advertisement = typedQuery.getResultList();

		} catch (NoResultException e) {
			System.out.println("Hata: " + e);
		}
		return advertisement;
	}

	@Override
	public List<Advertisement> findAdvertisementEntities(int firstResult, int maxResult) {
		List<Advertisement> advertisement = null;

		try {
			TypedQuery<Advertisement> typedQuery = this.entityManager
					.createNamedQuery("Advertisement.findAdvertisements", Advertisement.class);

			typedQuery.setFirstResult(firstResult);
			typedQuery.setMaxResults(maxResult);

			advertisement = typedQuery.getResultList();

		} catch (NoResultException e) {
			System.out.println("Hata: " + e);
		}
		return advertisement;
	}

}
