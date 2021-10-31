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
		// Biz advertisement deðerini set ile deðiþtirmiyeceðiz o yüzden final yapýp
		// daha hýzlý çalýþmasýný saðladýk

		try {
			this.entityTransaction.begin();

			this.entityManager.persist(advertisement);

			this.entityTransaction.commit();
		} catch (RuntimeException e) {
			System.out.println("Hata: " + e);

			try {
				this.entityTransaction.rollback();
				// Eðer iþlemler sýrasýnda bir hata olursa tüm iþlemleri geri çekmek için
				// rollback yaparýz
				// örnek/bir kullanýcýdan para çýktý ama karþý tarafa ulaþýrken hata oldu
				// rollback yaparýz
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
		// biz verileri eklediðimizde entityManager'in hafýzasýnda bu bilgiler tutuluyor
		// ama eclipsi felan kapatýnca bu bilgiler gidiyor

		try {
			if (this.entityManager.contains(advertisement)) {
				// eðer hafýzada tutuluyorsa true deðer gelir
				this.entityManager.remove(advertisement);
			} else {
				Advertisement deleteAdvertisement = this.findById(advertisement.getAdvertisementId());
			}
		} catch (RuntimeException e) {
			System.out.println("Hata: " + e);

			try {
				this.entityTransaction.rollback();
				// Eðer iþlemler sýrasýnda bir hata olursa tüm iþlemleri geri çekmek için
				// rollback yaparýz
				// örnek/bir kullanýcýdan para çýktý ama karþý tarafa ulaþýrken hata oldu
				// rollback yaparýz
			} catch (RollbackException e2) {
				System.out.println("Hata: " + e2);
			}
			return false;
		}

		return true;
	}

	@Override
	public Advertisement findById(final Integer id) {
		// Biz id deðerini set ile deðiþtirmiyeceðiz o yüzden final yapýp
		// daha hýzlý çalýþmasýný saðladýk

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
