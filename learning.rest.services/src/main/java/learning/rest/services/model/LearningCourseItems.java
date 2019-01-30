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
    @NamedQuery(name = "LearningCourseItems.findAll", query = "SELECT lc FROM LearningCourseItems lc"),
    @NamedQuery(name = "LearningCourseItems.findById", query = "SELECT lc FROM LearningCourseItems lc WHERE lc.requistionID = :reqId AND lc.courseID = :cId "),
    @NamedQuery(name = "LearningCourseItems.findByRequsitionId", query = "SELECT lc FROM LearningCourseItems lc WHERE lc.requistionID = :reqId "),
    @NamedQuery(name = "LearningCourseItems.findByCourseId", query = "SELECT lc FROM LearningCourseItems lc WHERE lc.courseID = :cId "),
})
public class LearningCourseItems {
	@Id
	@Column(nullable = false)
	private Long requistionID;
	
	@Id
	@Column(nullable = false)
	private Long courseID;

	public Long getRequistionID() {
		return requistionID;
	}

	public void setRequistionID(Long requistionID) {
		this.requistionID = requistionID;
	}

	public Long getCourseID() {
		return courseID;
	}

	public void setCourseID(Long courseID) {
		this.courseID = courseID;
	}

}
