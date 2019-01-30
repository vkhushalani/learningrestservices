package learning.rest.services.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import learning.rest.services.model.LearningCourseItems;

@Transactional
@Component
public class LearningCourseItemsServiceImp implements LearningCourseItemsService {
	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LearningCourseItems> findAll() {
		Query query = em.createNamedQuery("LearningCourseItems.findAll");
		 List<LearningCourseItems> items = query.getResultList();

	        return items;
	}

	@Override
	@Transactional
	public LearningCourseItems create(LearningCourseItems item) {
//		em.getTransaction().begin();
        em.persist(item);
//        em.getTransaction().commit();
        return item;
	}
	
	@Override
	@Transactional
	public LearningCourseItems update(LearningCourseItems item) {
//		em.getTransaction().begin();
        em.merge(item);
//        em.getTransaction().commit();
        return item;
	}

	@Override
	public LearningCourseItems findById(Long requistionID, Long courseID) {
		Query query = em.createNamedQuery("LearningCourseItems.findById")
				.setParameter("reqId",requistionID)
				.setParameter("cId",courseID);
		LearningCourseItems item = (LearningCourseItems) query.getSingleResult();

		return item;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LearningCourseItems> findByRequsitionId(Long requistionID) {
		Query query = em.createNamedQuery("LearningCourseItems.findByRequsitionId")
				.setParameter("reqId",requistionID);
		List<LearningCourseItems> items = query.getResultList();

    return items;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LearningCourseItems> findByCourseId(Long courseID) {
		Query query = em.createNamedQuery("LearningCourseItems.findByCourseId")
				.setParameter("cId",courseID);
		List<LearningCourseItems> items = query.getResultList();

    return items;
	}

	@Override
	@Transactional
	public void deleteById(Long requistionID, Long courseID) {
//		em.getTransaction().begin();
		LearningCourseItems item = findById(requistionID,courseID);
		em.remove(item);
//		em.getTransaction().commit();

	}

	@Override
	public void deleteByRequsitionId(Long requistionID) {
		List<LearningCourseItems> items = findByRequsitionId(requistionID);
		
		for(LearningCourseItems item : items)
		{
			deleteById(item.getRequistionID(),item.getCourseID());
		}

	}

	@Override
	public void deleteByCourseId(Long courseID) {
		List<LearningCourseItems> items = findByCourseId(courseID);
		
		for(LearningCourseItems item : items)
		{
			deleteById(item.getRequistionID(),item.getCourseID());
		}

	}

	@Override
	@Transactional
	public void deleteByObject(LearningCourseItems item) {
//		em.getTransaction().begin();
		em.remove(item);
//		em.getTransaction().commit();

	}

}
