package learning.rest.services.service;

import java.util.List;

import learning.rest.services.model.RequisitionLogRel;

public interface RequisitionLogRelService {
	public List<RequisitionLogRel> findAll();
	public RequisitionLogRel create(RequisitionLogRel item);
	public RequisitionLogRel update(RequisitionLogRel item);
	public RequisitionLogRel findById(Long requistionID,Long logID);
	public List<RequisitionLogRel> findByRequsitionId(Long requistionID);
	public List<RequisitionLogRel> findByLogId(Long logID);
	public void deleteById(Long requistionID,Long logID);
	public void deleteByRequsitionId(Long requistionID);
	public void deleteByLogId(Long logID);
	public void deleteByObject(RequisitionLogRel item);
	public int getLatestSequenceNumber(Long requistionID);
}
