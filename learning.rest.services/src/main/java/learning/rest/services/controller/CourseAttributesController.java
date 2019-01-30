package learning.rest.services.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import learning.rest.services.model.CourseAttributes;
import learning.rest.services.service.CourseAttributesService;

@RestController
public class CourseAttributesController {
	 
	 @Autowired
	 CourseAttributesService courseAttributeService;
	 
	 @GetMapping("/courses")
	 public ResponseEntity <List<CourseAttributes>> getAllCourses() {
		 
		 List<CourseAttributes> courses = courseAttributeService.findAll();
	     return ResponseEntity.ok().body(courses);
	 }
	 @GetMapping("/courses/{id}")
	   public ResponseEntity<CourseAttributes> getCourse(@PathVariable("id") long courseID) {
		 CourseAttributes course = courseAttributeService.findById(courseID);
	      return ResponseEntity.ok().body(course);
	   }
	 
	

}
