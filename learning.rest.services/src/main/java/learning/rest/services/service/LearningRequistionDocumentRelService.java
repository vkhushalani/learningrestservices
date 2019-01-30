package learning.rest.services.service;

import java.util.List;

import learning.rest.services.model.LearningRequistionDocumentRel;

public interface LearningRequistionDocumentRelService {
	public List<LearningRequistionDocumentRel> findAll();
	public LearningRequistionDocumentRel create(LearningRequistionDocumentRel item);
	public LearningRequistionDocumentRel update(LearningRequistionDocumentRel item);
	public LearningRequistionDocumentRel findById(Long requistionID,Long docId);
	public LearningRequistionDocumentRel findByIdSpecific(Long requistionID,Long docId,Long learnerID);
	public List<LearningRequistionDocumentRel> findByRequisitionId(Long requistionID);
	public List<LearningRequistionDocumentRel> findByDocId(Long docId);
	public List<LearningRequistionDocumentRel> findByLearnerId(Long learnerID);
	public void deleteById(Long requistionID,Long docId);
	public void deleteByRequisitionId(Long requistionID);
	public void deleteByDocId(Long docId);
	public void deleteByObject(LearningRequistionDocumentRel item);

}
