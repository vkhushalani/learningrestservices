package learning.rest.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table
@NamedQueries({
    @NamedQuery(name = "CourseProvider.findAll", query = "SELECT cp FROM CourseProvider cp"),
})
public class CourseProvider {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long providerID;
	
	@Column
	private String providerCompanyName;
	
	@Column
	private String providerStreet;
	
	@Column
	private String providerPostalCode;
	
	@Column
	private String providerCity ;
	
	@Column
	private String providerEmail;
	
	@Column
	private String providerWebsite;

	public Long getProviderID() {
		return providerID;
	}

	public void setProviderID(Long providerID) {
		this.providerID = providerID;
	}

	public String getProviderCompanyName() {
		return providerCompanyName;
	}

	public void setProviderCompanyName(String providerCompanyName) {
		this.providerCompanyName = providerCompanyName;
	}

	public String getProviderStreet() {
		return providerStreet;
	}

	public void setProviderStreet(String providerStreet) {
		this.providerStreet = providerStreet;
	}

	public String getProviderPostalCode() {
		return providerPostalCode;
	}

	public void setProviderPostalCode(String providerPostalCode) {
		this.providerPostalCode = providerPostalCode;
	}

	public String getProviderCity() {
		return providerCity;
	}

	public void setProviderCity(String providerCity) {
		this.providerCity = providerCity;
	}

	public String getProviderEmail() {
		return providerEmail;
	}

	public void setProviderEmail(String providerEmail) {
		this.providerEmail = providerEmail;
	}

	public String getProviderWebsite() {
		return providerWebsite;
	}

	public void setProviderWebsite(String providerWebsite) {
		this.providerWebsite = providerWebsite;
	}
	

}
