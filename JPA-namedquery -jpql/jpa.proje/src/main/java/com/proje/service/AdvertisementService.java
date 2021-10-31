package com.proje.service;

import java.util.List;

import com.proje.model.Advertisement;

public interface AdvertisementService {
	boolean saveAdvertisement(Advertisement advertisement);

	boolean updateAdvertisement(Advertisement advertisement);

	boolean removeAdvertisement(Advertisement advertisement);

	Advertisement findById(Integer id);

	List<Advertisement> findByUsername(String username);

	List<Advertisement> findAdvertisements();

	List<Advertisement> findAdvertisementEntities(int firstResult, int maxResult);
}
