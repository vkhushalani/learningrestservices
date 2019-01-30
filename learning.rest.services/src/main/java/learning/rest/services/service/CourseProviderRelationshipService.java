package learning.rest.services.service;

import java.util.List;

import learning.rest.services.model.CourseProviderRelationship;

public interface CourseProviderRelationshipService {

	public List<CourseProviderRelationship> findAll();
	public CourseProviderRelationship create(CourseProviderRelationship item);
	public CourseProviderRelationship update(CourseProviderRelationship item);
	public CourseProviderRelationship findById(Long courseID,Long providerID);
	public List<CourseProviderRelationship> findByRequisitionId(Long requisitionID);
	public List<CourseProviderRelationship> findByProviderId(Long providerID);
	public void deleteById(Long courseID,Long providerID);
	public void deleteByProviderId(Long providerID);
	public void deleteByRequisitionId(Long requisitionID);
	public void deleteByObject(CourseProviderRelationship item);
}
