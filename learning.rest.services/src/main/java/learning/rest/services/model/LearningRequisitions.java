package learning.rest.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
@NamedQueries({
    @NamedQuery(name = "LearningRequisitions.findAll", query = "SELECT lR FROM LearningRequisitions lR"),
    @NamedQuery(name = "LearningRequisitions.findByRequestorId", query = "SELECT lR FROM LearningRequisitions lR where lR.requestorID = :rId"),
})
public class LearningRequisitions {
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long requistionID;
		
		@Column(nullable = false)
		private Long requestorID;
		
		@Column(nullable = false)
		private Long requistionDate;
		
		@Column
		private int StatusId;
		
		@Transient
		private String Status;
		@Column
		private String requisitonComments;
		@Column
		private String requsitionCheck;
		
		public Long getRequistionID() {
			return requistionID;
		}

		public Long getRequestorID() {
			return requestorID;
		}

		public void setRequestorID(Long requestorID) {
			this.requestorID = requestorID;
		}

		public Long getRequistionDate() {
			return requistionDate;
		}

		public void setRequistionDate(Long requistionDate) {
			this.requistionDate = requistionDate;
		}

		public String getStatus() {
			return Status;
		}

		public void setStatus(String status) {
			Status = status;
		}

		public String getRequisitonComments() {
			return requisitonComments;
		}

		public void setRequisitonComments(String requisitonComments) {
			this.requisitonComments = requisitonComments;
		}

		public String getRequsitionCheck() {
			return requsitionCheck;
		}

		public void setRequsitionCheck(String requsitionCheck) {
			this.requsitionCheck = requsitionCheck;
		}

		public int getStatusId() {
			return StatusId;
		}

		public void setStatusId(int statusId) {
			StatusId = statusId;
		}


}
