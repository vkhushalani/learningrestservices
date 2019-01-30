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
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByPUserId", query = "SELECT u FROM Users u WHERE u.pUserID = :pId"),
})
public class Users {
	
	   @Id
	   @GeneratedValue(strategy = GenerationType.AUTO)
	   private Long userID;
		
	   @Column(nullable = false)
	   private String pUserID;
		
	   @Column
	   private String fname;
	   
	   @Column(nullable = false)
	   private String lname;
	   
	   @Column
	   private String sex;
	   
	   @Column
	   private Long dob;
	   
	   @Column
	   private String phone;
	   
	   @Column(nullable = false)
	   private String email;
	   
	   @Column
	   private String roleID;

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getpUserID() {
		return pUserID;
	}

	public void setpUserID(String pUserID) {
		this.pUserID = pUserID;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Long getDob() {
		return dob;
	}

	public void setDob(Long dob) {
		this.dob = dob;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	
	

}
