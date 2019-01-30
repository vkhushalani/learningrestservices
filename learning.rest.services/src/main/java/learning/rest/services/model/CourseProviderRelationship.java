package learning.rest.services.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table
@NamedQueries({
    @NamedQuery(name = "CourseProviderRelationship.findAll", query = "SELECT cpr FROM CourseProviderRelationship cpr"),
    @NamedQuery(name = "CourseProviderRelationship.findById", query = "SELECT cpr FROM CourseProviderRelationship cpr WHERE cpr.providerID = :aId AND cpr.requisitionID = :rId "),
    @NamedQuery(name = "CourseProviderRelationship.findByRequisitionId", query = "SELECT cpr FROM CourseProviderRelationship cpr WHERE cpr.requisitionID = :rId "),
    @NamedQuery(name = "CourseProviderRelationship.findByProviderId", query = "SELECT cpr FROM CourseProviderRelationship cpr WHERE cpr.providerID = :pId "),
})
public class CourseProviderRelationship {
	@Id
	private Long providerID;

	private Long requisitionID;

	public Long getProviderID() {
		return providerID;
	}

	public void setProviderID(Long providerID) {
		this.providerID = providerID;
	}

	public Long getRequisitionID() {
		return requisitionID;
	}

	public void setRequisitionID(Long requisitionID) {
		this.requisitionID = requisitionID;
	}

}
