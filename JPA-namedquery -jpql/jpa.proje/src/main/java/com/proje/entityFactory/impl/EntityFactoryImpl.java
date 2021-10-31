package com.proje.entityFactory.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.proje.entityFactory.EntityFactory;

public class EntityFactoryImpl implements EntityFactory {

	private EntityManager entityManager = null;

	@Override
	public EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit");
		
		this.entityManager = factory.createEntityManager();
		
		return entityManager;
	}

}
