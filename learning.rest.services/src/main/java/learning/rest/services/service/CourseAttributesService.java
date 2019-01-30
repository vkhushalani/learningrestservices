package learning.rest.services.service;

import java.util.List;

import learning.rest.services.model.CourseAttributes;

public interface CourseAttributesService {

	public List<CourseAttributes> findAll();
	public CourseAttributes update(CourseAttributes course);
	public CourseAttributes create(CourseAttributes course);
	public CourseAttributes findById(Long courseID);
	public void deleteById(Long courseID);
	public void deleteByObject(CourseAttributes course);
}
