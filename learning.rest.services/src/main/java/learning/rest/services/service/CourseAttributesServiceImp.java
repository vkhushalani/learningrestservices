package learning.rest.services.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import learning.rest.services.model.CourseAttributes;

@Transactional
@Component
public class CourseAttributesServiceImp implements CourseAttributesService {
	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CourseAttributes> findAll() {
		Query query = em.createNamedQuery("CourseAttributes.findAll");
		 List<CourseAttributes> courses = query.getResultList();

	        return courses;
	}
	
	
	@Override
	@Transactional
	public CourseAttributes create(CourseAttributes course) { 
//		em.getTransaction().begin();
        em.persist(course);
//        em.getTransaction().commit();
        return course;
	}
	
	@Override
	@Transactional
	public CourseAttributes update(CourseAttributes course) { 
//		em.getTransaction().begin();
        em.merge(course);
//        em.getTransaction().commit();
        return course;
	}

	@Override
	public CourseAttributes findById(Long courseID) {
		CourseAttributes course = em.find(CourseAttributes.class, courseID);
        return course;
	}

	@Override
	@Transactional
	public void deleteById(Long courseID) {
//		em.getTransaction().begin();
		CourseAttributes course = em.find(CourseAttributes.class, courseID);
		em.remove(course);
//		em.getTransaction().commit();
	}

	@Override
	@Transactional
	public void deleteByObject(CourseAttributes course) {
//		em.getTransaction().begin();
		em.remove(course);
//		em.getTransaction().commit();

	}

}
