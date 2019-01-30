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
    @NamedQuery(name = "Statuses.findAll", query = "SELECT s FROM Statuses s"),
    @NamedQuery(name = "Statuses.findByStatusId", query = "SELECT s FROM Statuses s WHERE s.statusID = :sId "),
    @NamedQuery(name = "Statuses.findById", query = "SELECT s FROM Statuses s WHERE s.statusID = :sId and s.applicableRoleID = :rId")
})
public class Statuses {
	
		@Id
		private int statusID;
		
		@Id
		private String applicableRoleID;
		
		@Column
		private String statusShortText;
		
		@Column
		private String statusLongText;
		
		@Column
		private String nextStates;
		
		

		public int getStatusID() {
			return statusID;
		}

		public void setStatusID(int statusID) {
			this.statusID = statusID;
		}

		public String getStatusSmallText() {
			return statusShortText;
		}

		public void setStatusSmallText(String statusShortText) {
			this.statusShortText = statusShortText;
		}

		public String getStatusText() {
			return statusLongText;
		}

		public void setStatusText(String statusLongText) {
			this.statusLongText = statusLongText;
		}

		public String getNextStates() {
			return nextStates;
		}

		public void setNextStates(String nextStates) {
			this.nextStates = nextStates;
		}

		public String getApplicableRole() {
			return applicableRoleID;
		}

		public void setApplicableRole(String applicableRoleID) {
			this.applicableRoleID = applicableRoleID;
		}

	

}
