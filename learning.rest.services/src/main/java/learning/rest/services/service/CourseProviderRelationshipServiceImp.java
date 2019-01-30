package learning.rest.services.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import learning.rest.services.model.CourseProviderRelationship;
@Transactional
@Component
public class CourseProviderRelationshipServiceImp implements CourseProviderRelationshipService {
	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CourseProviderRelationship> findAll() {
		Query query = em.createNamedQuery("CourseProviderRelationship.findAll");
		 List<CourseProviderRelationship> items = query.getResultList();

	        return items;
	}

	@Override
	@Transactional
	public CourseProviderRelationship create(CourseProviderRelationship item) {
//		em.getTransaction().begin();
        em.persist(item);
//        em.getTransaction().commit();
        return item;
	}

	@Override
	@Transactional
	public CourseProviderRelationship update(CourseProviderRelationship item) {
//		em.getTransaction().begin();
        em.merge(item);
//        em.getTransaction().commit();
        return item;
	}

	@Override
	public CourseProviderRelationship findById(Long requisitionID, Long providerID) {
		Query query = em.createNamedQuery("CourseProviderRelationship.findById")
				.setParameter("rId",requisitionID)
				.setParameter("pId",providerID);
		CourseProviderRelationship item = (CourseProviderRelationship) query.getSingleResult();

		return item;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CourseProviderRelationship> findByRequisitionId(Long requisitionID) {
		Query query = em.createNamedQuery("CourseProviderRelationship.findByRequisitionId")
				.setParameter("rId",requisitionID);
		List<CourseProviderRelationship> items = query.getResultList();

    return items;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CourseProviderRelationship> findByProviderId(Long providerID) {
		Query query = em.createNamedQuery("CourseProviderRelationship.findByProviderId")
				.setParameter("pId",providerID);
		List<CourseProviderRelationship> items = query.getResultList();

    return items;
	}

	@Override
	@Transactional
	public void deleteById(Long courseID, Long providerID) {
//		em.getTransaction().begin();
		CourseProviderRelationship item = findById(courseID,providerID);
		em.remove(item);
//		em.getTransaction().commit();

	}

	@Override
	public void deleteByProviderId(Long providerID) {
		List<CourseProviderRelationship> items = findByProviderId(providerID);
		
		for(CourseProviderRelationship item : items)
		{
			deleteById(item.getRequisitionID(),item.getProviderID());
		}

	}

	@Override
	public void deleteByRequisitionId(Long requisitionID) {
		List<CourseProviderRelationship> items = findByRequisitionId(requisitionID);
		
		for(CourseProviderRelationship item : items)
		{
			deleteById(item.getRequisitionID(),item.getProviderID());
		}

	}

	@Override
	@Transactional
	public void deleteByObject(CourseProviderRelationship item) {
//		em.getTransaction().begin();
		em.remove(item);
//		em.getTransaction().commit();

	}

}
