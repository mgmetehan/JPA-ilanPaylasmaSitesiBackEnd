package com.proje.service.impl;

import java.util.List;

import com.proje.model.Education;
import com.proje.repository.EducationRepository;
import com.proje.repository.impl.EducationRepositoryImpl;
import com.proje.service.EducationService;

public class EducationServiceImpl implements EducationService {
	private EducationRepository educationRepository = new EducationRepositoryImpl();

	@Override
	public boolean saveEducation(final Education education) {
		return this.educationRepository.saveEducation(education);
	}

	@Override
	public boolean updateEducation(final Education education) {
		return this.educationRepository.updateEducation(education);
	}

	@Override
	public boolean removeEducation(final Education education) {
		return this.educationRepository.removeEducation(education);
	}

	@Override
	public Education findById(final Integer id) {
		return this.educationRepository.findById(id);
	}

	@Override
	public List<Education> findEducations() {
		return this.educationRepository.findEducations();
	}

	@Override
	public Education findWithAdvertisementById(final Integer id) {
		return this.educationRepository.findWithAdvertisementById(id);
	}

}
