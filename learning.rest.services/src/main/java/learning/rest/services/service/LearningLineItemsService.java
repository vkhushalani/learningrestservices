package learning.rest.services.service;

import java.util.List;

import learning.rest.services.model.LearningLineItems;

public interface LearningLineItemsService {
	public List<LearningLineItems> findAll();
	public LearningLineItems create(LearningLineItems item);
	public LearningLineItems update(LearningLineItems item);
	public LearningLineItems findById(Long requistionID,Long learnerID);
	public List<LearningLineItems> findByRequsitionId(Long requistionID);
	public List<LearningLineItems> findByLearnerId(Long learnerID);
	public void deleteById(Long requistionID,Long learnerID);
	public void deleteByRequsitionId(Long requistionID);
	public void deleteByLearnerId(Long learnerID);
	public void deleteByObject(LearningLineItems item);

}
