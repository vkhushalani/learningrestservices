package learning.rest.services.model;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table
@NamedQueries({
    @NamedQuery(name = "Roles.findAll", query = "SELECT r FROM Roles r")
})
public class Roles {
	@Id
	private Long roleID;
	@Column
	private String roleShortName;
	@Column
	private String roleLongName;
	
	public Long getRoleID() {
		return roleID;
	}
	public void setRoleID(Long roleID) {
		this.roleID = roleID;
	}
	public String getRoleShortName() {
		return roleShortName;
	}
	public void setRoleShortName(String roleShortName) {
		this.roleShortName = roleShortName;
	}
	public String getRoleLongName() {
		return roleLongName;
	}
	public void setRoleLongName(String roleLongName) {
		this.roleLongName = roleLongName;
	}

}
