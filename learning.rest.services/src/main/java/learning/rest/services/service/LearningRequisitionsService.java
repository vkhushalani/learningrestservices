package learning.rest.services.service;

import java.util.List;

import learning.rest.services.model.LearningRequisitions;

public interface LearningRequisitionsService {

	public List<LearningRequisitions> findAll();
	public LearningRequisitions create(LearningRequisitions requisitions);
	public LearningRequisitions update(LearningRequisitions requisitions);
	public LearningRequisitions findById(Long requistionID);
	public List<LearningRequisitions> findByRequestorId(Long requestorID);
	public void deleteById(Long requistionID);
	public void deleteByObject(LearningRequisitions requisitions);
}
