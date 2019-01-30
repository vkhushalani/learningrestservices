package learning.rest.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
@NamedQueries({
    @NamedQuery(name = "LearningLineItems.findAll", query = "SELECT ll FROM LearningLineItems ll"),
    @NamedQuery(name = "LearningLineItems.findById", query = "SELECT ll FROM LearningLineItems ll WHERE ll.requistionID = :reqId AND ll.learnerID = :lId "),
    @NamedQuery(name = "LearningLineItems.findByRequsitionId", query = "SELECT ll FROM LearningLineItems ll WHERE ll.requistionID = :reqId "),
    @NamedQuery(name = "LearningLineItems.findByLearnerId", query = "SELECT ll FROM LearningLineItems ll WHERE ll.learnerID = :lId "),
})
public class LearningLineItems {
	@Id
	@Column(nullable = false)
	private Long requistionID;
	
	@Id
	@Column(nullable = false)
	private Long learnerID;
	
	@Column
	private int approvalStatusId;
	
	@Transient
	private String approvalStatus;
	
	@Transient
	private String learnerFname;
	@Transient
	private String learnerLname;
	@Transient
	private String learnerEmail;

	public Long getLearnerID() {
		return learnerID;
	}

	public void setLearnerID(Long learnerID) {
		this.learnerID = learnerID;
	}

	public Long getRequistionID() {
		return requistionID;
	}

	public void setRequistionID(Long requistionID) {
		this.requistionID = requistionID;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getLearnerFname() {
		return learnerFname;
	}

	public void setLearnerFname(String learnerFname) {
		this.learnerFname = learnerFname;
	}

	public String getLearnerLname() {
		return learnerLname;
	}

	public void setLearnerLname(String learnerLname) {
		this.learnerLname = learnerLname;
	}

	public String getLearnerEmail() {
		return learnerEmail;
	}

	public void setLearnerEmail(String learnerEmail) {
		this.learnerEmail = learnerEmail;
	}

	public int getApprovalStatusId() {
		return approvalStatusId;
	}

	public void setApprovalStatusId(int approvalStatusId) {
		this.approvalStatusId = approvalStatusId;
	}

}
