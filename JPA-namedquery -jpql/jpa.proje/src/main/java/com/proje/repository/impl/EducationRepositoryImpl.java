package com.proje.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import com.proje.model.Education;
import com.proje.repository.EducationRepository;

public class EducationRepositoryImpl implements EducationRepository {

	private EntityManager entityManager = entityFactory.getEntityManager();

	private EntityTransaction entityTransaction = this.entityManager.getTransaction();

	@Override
	public boolean saveEducation(final Education education) {

		try {
			this.entityTransaction.begin();

			this.entityManager.persist(education);

			this.entityTransaction.commit();
		} catch (RuntimeException e) {
			System.err.println("Hata: " + e);

			try {
				this.entityTransaction.rollback();
			} catch (RollbackException e2) {
				System.err.print("Hata: " + e2);
			}
			return false;
		}

		return true;
	}

	@Override
	public boolean updateEducation(final Education education) {

		try {
			this.entityTransaction.begin();

			this.entityManager.merge(education);

			this.entityManager.flush();

			this.entityTransaction.commit();
		} catch (RuntimeException e) {
			System.err.println("Hata: " + e);

			try {
				this.entityTransaction.rollback();
			} catch (RollbackException e2) {
				System.err.print("Hata: " + e2);
			}
			return false;
		}

		return true;
	}

	@Override
	public boolean removeEducation(final Education education) {

		try {
			if (this.entityManager.contains(education)) {
				this.entityManager.remove(education);
			} else {
				Education removeEducation = this.findById(education.getEducationId());
				this.entityManager.remove(removeEducation);
			}
		} catch (RuntimeException e) {
			System.err.println("Hata: " + e);

			try {
				this.entityTransaction.rollback();
			} catch (RollbackException e2) {
				System.err.print("Hata: " + e2);
			}
			return false;
		}

		return true;
	}

	@Override
	public Education findById(Integer educationId) {

		Education education = null;

		try {
			education = this.entityManager.find(Education.class, educationId);
		} catch (NoResultException e) {
			System.err.println("Hata: " + e);
		}
		return education;
	}

	@Override
	public List<Education> findEducations() {

		List<Education> educations = null;

		try {
			TypedQuery<Education> typedQuery = this.entityManager.createNamedQuery("Education.findEducations",
					Education.class);
			educations = typedQuery.getResultList();
		} catch (NoResultException e) {
			System.err.println("Hata: " + e);
		}
		return educations;
	}

	@Override
	public Education findWithAdvertisementById(Integer educationId) {
		Education education = null;

		try {
			TypedQuery<Education> typeQuery = this.entityManager.createNamedQuery("Education.findWithAdvertisementById",
					Education.class);
			typeQuery.setParameter("educationId", educationId);
			education = typeQuery.getSingleResult();
		} catch (Exception e) {
			System.err.println("Hata: " + e);
		}
		return education;
	}

}
