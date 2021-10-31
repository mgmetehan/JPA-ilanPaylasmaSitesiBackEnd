package com.proje.repository;

import java.util.List;

import com.proje.entityFactory.EntityFactory;
import com.proje.entityFactory.impl.EntityFactoryImpl;
import com.proje.model.Advertisement;

public interface AdvertisementRepository {

	EntityFactory entityFactory = new EntityFactoryImpl();

	boolean saveAdvertisement(Advertisement advertisement);

	boolean updateAdvertisement(Advertisement advertisement);

	boolean removeAdvertisement(Advertisement advertisement);

	Advertisement findById(Integer id);

	List<Advertisement> findByUsername(String username);

	List<Advertisement> findAdvertisements();

	List<Advertisement> findAdvertisementEntities(int firstResult, int maxResult);

}
