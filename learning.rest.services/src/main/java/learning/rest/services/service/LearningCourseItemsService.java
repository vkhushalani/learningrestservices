package learning.rest.services.service;

import java.util.List;

import learning.rest.services.model.LearningCourseItems;

public interface LearningCourseItemsService {
	public List<LearningCourseItems> findAll();
	public LearningCourseItems create(LearningCourseItems item);
	public LearningCourseItems update(LearningCourseItems item);
	public LearningCourseItems findById(Long requistionID,Long courseID);
	public List<LearningCourseItems> findByRequsitionId(Long requistionID);
	public List<LearningCourseItems> findByCourseId(Long courseID);
	public void deleteById(Long requistionID,Long courseID);
	public void deleteByRequsitionId(Long requistionID);
	public void deleteByCourseId(Long courseID);
	public void deleteByObject(LearningCourseItems item);

}
