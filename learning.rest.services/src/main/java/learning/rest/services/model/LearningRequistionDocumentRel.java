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
    @NamedQuery(name = "LearningRequistionDocumentRel.findAll", query = "SELECT rd FROM LearningRequistionDocumentRel rd"),
    @NamedQuery(name = "LearningRequistionDocumentRel.findById", query = "SELECT rd FROM LearningRequistionDocumentRel rd WHERE rd.requistionID = :rId AND rd.docId = :dId "),
    @NamedQuery(name = "LearningRequistionDocumentRel.findByRequisitionId", query = "SELECT rd FROM LearningRequistionDocumentRel rd WHERE rd.requistionID = :rId "),
    @NamedQuery(name = "LearningRequistionDocumentRel.findByDocId", query = "SELECT rd FROM LearningRequistionDocumentRel rd WHERE rd.docId = :dId "),
    @NamedQuery(name = "LearningRequistionDocumentRel.findByIdSpecific", query = "SELECT rd FROM LearningRequistionDocumentRel rd WHERE rd.docId = :dId AND rd.requistionID = :rId AND rd.learnerID= :lId "),
    @NamedQuery(name = "LearningRequistionDocumentRel.findByLearnerId", query = "SELECT rd FROM LearningRequistionDocumentRel rd WHERE rd.learnerID = :lId ")
})
public class LearningRequistionDocumentRel {
	
	@Id
	@Column
	private Long requistionID;
	
	@Id
	@Column
	private Long docId;
	
	@Column
	private Long learnerID;

	
	public Long getRequistionID() {
		return requistionID;
	}
	public void setRequistionID(Long requistionID) {
		this.requistionID = requistionID;
	}
	public Long getDocId() {
		return docId;
	}
	public void setDocId(Long docId) {
		this.docId = docId;
	}
	public Long getLearnerID() {
		return learnerID;
	}
	public void setLearnerID(Long learnerID) {
		this.learnerID = learnerID;
	}
	

}
