package learning.rest.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table
@NamedQueries({
    @NamedQuery(name = "RequisitionLogRel.findAll", query = "SELECT rlr FROM RequisitionLogRel rlr"),
    @NamedQuery(name = "RequisitionLogRel.findById", query = "SELECT rlr FROM RequisitionLogRel rlr WHERE rlr.requistionID = :rId AND rlr.logID = :lId "),
    @NamedQuery(name = "RequisitionLogRel.findByRequisitionId", query = "SELECT rlr FROM RequisitionLogRel rlr WHERE rlr.requistionID = :rId "),
    @NamedQuery(name = "RequisitionLogRel.findByLogId", query = "SELECT rlr FROM RequisitionLogRel rlr WHERE rlr.logID = :lId ")
})
public class RequisitionLogRel {
	@Id
	private Long requistionID;
	@Id
	private Long logID;
	@Column
	private int sequence;
	
	public Long getRequistionID() {
		return requistionID;
	}
	public void setRequistionID(Long requistionID) {
		this.requistionID = requistionID;
	}
	public Long getLogID() {
		return logID;
	}
	public void setLogID(Long logID) {
		this.logID = logID;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int i) {
		this.sequence = i;
	}

}
