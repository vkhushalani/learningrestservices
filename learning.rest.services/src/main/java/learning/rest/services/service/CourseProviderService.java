package learning.rest.services.service;

import java.util.List;

import learning.rest.services.model.CourseProvider;

public interface CourseProviderService {
	public List<CourseProvider> findAll();
	public CourseProvider create(CourseProvider provider);
	public CourseProvider update(CourseProvider provider);
	public CourseProvider findById(Long providerID);
	public void deleteById(Long providerID);
	public void deleteByObject(CourseProvider provider);
}
