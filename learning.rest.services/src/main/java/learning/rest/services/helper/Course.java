package learning.rest.services.helper;

import java.util.List;

import learning.rest.services.model.CourseAttributes;
import learning.rest.services.model.CourseProvider;
import learning.rest.services.model.LearningLineItems;
import learning.rest.services.model.LearningRequisitions;
import learning.rest.services.model.Users;
import learning.rest.services.model.Logs;

public class Course {
	
	private CourseAttributes course;
	private CourseProvider provider;
	private LearningRequisitions requisitions;
	private LearningLineItems learningItem;
	private Users requestor;
	private List<LearningLineItems> lItems;
	private String myCourse;
	private List<Logs> logs;
	
	public CourseProvider getProvider() {
		return provider;
	}
	public void setProvider(CourseProvider provider) {
		this.provider = provider;
	}
	public CourseAttributes getCourse() {
		return course;
	}
	public void setCourse(CourseAttributes course) {
		this.course = course;
	}
	public LearningRequisitions getRequisitions() {
		return requisitions;
	}
	public void setRequisitions(LearningRequisitions requisitions) {
		this.requisitions = requisitions;
	}
	public LearningLineItems getLearningItem() {
		return learningItem;
	}
	public void setLearningItem(LearningLineItems learningItem) {
		this.learningItem = learningItem;
	}
	public Users getRequestor() {
		return requestor;
	}
	public void setRequestor(Users requestor) {
		this.requestor = requestor;
	}
	public List<LearningLineItems> getlItems() {
		return lItems;
	}
	public void setlItems(List<LearningLineItems> lItems) {
		this.lItems = lItems;
	}
	public String getMyCourse() {
		return myCourse;
	}
	public void setMyCourse(String myCourse) {
		this.myCourse = myCourse;
	}
	public List<Logs> getLogs() {
		return logs;
	}
	public void setLogs(List<Logs> logs) {
		this.logs = logs;
	}

}
