package com.proje.service.impl;

import java.util.List;

import com.proje.model.Advertisement;
import com.proje.repository.AdvertisementRepository;
import com.proje.repository.impl.AdvertisementRepositoryImpl;
import com.proje.service.AdvertisementService;

public class AdvertisementServiceImpl implements AdvertisementService {

	private AdvertisementRepository advertisementRepository = new AdvertisementRepositoryImpl();

	@Override
	public boolean saveAdvertisement(final Advertisement advertisement) {

		return this.advertisementRepository.saveAdvertisement(advertisement);
	}

	@Override
	public boolean updateAdvertisement(final Advertisement advertisement) {
		return this.advertisementRepository.updateAdvertisement(advertisement);
	}

	@Override
	public boolean removeAdvertisement(final Advertisement advertisement) {
		return this.advertisementRepository.removeAdvertisement(advertisement);
	}

	@Override
	public Advertisement findById(final Integer id) {
		return this.advertisementRepository.findById(id);
	}

	@Override
	public List<Advertisement> findByUsername(final String username) {
		return this.findByUsername(username);
	}

	@Override
	public List<Advertisement> findAdvertisements() {
		return this.findAdvertisements();
	}

	@Override
	public List<Advertisement> findAdvertisementEntities(final int firstResult, final int maxResult) {
		return this.findAdvertisementEntities(firstResult, maxResult);
	}
}
