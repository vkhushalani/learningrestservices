package learning.rest.services.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Column;

@Entity
@Table
@NamedQueries({
    @NamedQuery(name = "Logs.findAll", query = "SELECT w FROM Logs w")
})
public class Logs {
	   
	   @Id
	   @GeneratedValue(strategy = GenerationType.AUTO)
	   private Long logsID;
	   
	   @Column
	   private Long fromPerson;
	   
	   @Transient
	   private String fromPersonText;
	   
	   @Column
	   private Long toPerson;
	   
	   @Transient
	   private String toPersonText;
	   
	   @Column
	   private int fromStatus;
	   
	   @Transient
	   private String fromStatusText;
	   
	   @Column
	   private int toStatus;
	   
	   @Transient
	   private String toStatusText;
	   
	   @Column
	   private Long timestamp;
	   
	   @Column
	   private String comments;

	public Long getLogID() {
		return logsID;
	}

	public void setLogID(Long logsID) {
		this.logsID = logsID;
	}

	public Long getFromPerson() {
		return fromPerson;
	}

	public void setFromPerson(Long fromPerson) {
		this.fromPerson = fromPerson;
	}

	public Long getToPerson() {
		return toPerson;
	}

	public void setToPerson(Long toPerson) {
		this.toPerson = toPerson;
	}

	public int getFromStatus() {
		return fromStatus;
	}

	public void setFromStatus(int fromStatus) {
		this.fromStatus = fromStatus;
	}

	public int getToStatus() {
		return toStatus;
	}

	public void setToStatus(int toStatus) {
		this.toStatus = toStatus;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getFromPersonText() {
		return fromPersonText;
	}

	public void setFromPersonText(String fromPersonText) {
		this.fromPersonText = fromPersonText;
	}

	public String getToPersonText() {
		return toPersonText;
	}

	public void setToPersonText(String toPersonText) {
		this.toPersonText = toPersonText;
	}

	public String getFromStatusText() {
		return fromStatusText;
	}

	public void setFromStatusText(String fromStatusText) {
		this.fromStatusText = fromStatusText;
	}

	public String getToStatusText() {
		return toStatusText;
	}

	public void setToStatusText(String toStatusText) {
		this.toStatusText = toStatusText;
	}
	

}
