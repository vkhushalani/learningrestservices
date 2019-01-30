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
    @NamedQuery(name = "CourseAttributes.findAll", query = "SELECT ca FROM CourseAttributes ca"),
})
public class CourseAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long courseID;
	
	@Column
	private String courseTitle;
	
	@Column
	private Long courseStartDate;
	
	@Column
	private Long courseEndDate;
	
	@Column
	private String courseDuration;
	
	@Column
	private String courseDurationUnit;

	@Column
	private String courseLocation;
	
	@Column
	private String courseCosts;
	
	@Column
	private String courseCostCenter;

	public Long getCourseID() {
		return courseID;
	}

	public void setCourseID(Long courseID) {
		this.courseID = courseID;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public Long getCourseStartDate() {
		return courseStartDate;
	}

	public void setCourseStartDate(Long courseStartDate) {
		this.courseStartDate = courseStartDate;
	}

	public Long getCourseEndDate() {
		return courseEndDate;
	}

	public void setCourseEndDate(Long courseEndDate) {
		this.courseEndDate = courseEndDate;
	}

	public String getCourseDuration() {
		return courseDuration;
	}

	public void setCourseDuration(String courseDuration) {
		this.courseDuration = courseDuration;
	}

	public String getCourseDurationUnit() {
		return courseDurationUnit;
	}

	public void setCourseDurationUnit(String courseDurationUnit) {
		this.courseDurationUnit = courseDurationUnit;
	}

	public String getCourseLocation() {
		return courseLocation;
	}

	public void setCourseLocation(String courseLocation) {
		this.courseLocation = courseLocation;
	}

	public String getCourseCosts() {
		return courseCosts;
	}

	public void setCourseCosts(String courseCosts) {
		this.courseCosts = courseCosts;
	}

	public String getCourseCostCenter() {
		return courseCostCenter;
	}

	public void setCourseCostCenter(String courseCostCenter) {
		this.courseCostCenter = courseCostCenter;
	}
	


}
