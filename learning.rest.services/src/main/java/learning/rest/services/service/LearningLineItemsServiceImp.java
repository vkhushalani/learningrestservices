package learning.rest.services.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import learning.rest.services.model.LearningLineItems;

@Transactional
@Component
public class LearningLineItemsServiceImp implements LearningLineItemsService {
	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LearningLineItems> findAll() {
		Query query = em.createNamedQuery("LearningLineItems.findAll");
		 List<LearningLineItems> items = query.getResultList();

	        return items;
	}

	@Override
	@Transactional
	public LearningLineItems create(LearningLineItems item) {
//		em.getTransaction().begin();
        em.persist(item);
//        em.getTransaction().commit();
        return item;
	}
	@Override
	@Transactional
	public LearningLineItems update(LearningLineItems item) {
//		em.getTransaction().begin();
        em.merge(item);
//        em.getTransaction().commit();
        return item;
	}

	@Override
	public LearningLineItems findById(Long requistionID, Long learnerID) {
		Query query = em.createNamedQuery("LearningLineItems.findById")
						.setParameter("reqId",requistionID)
						.setParameter("lId",learnerID);
		 LearningLineItems item = (LearningLineItems) query.getSingleResult();

	        return item;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LearningLineItems> findByRequsitionId(Long requistionID) {
		Query query = em.createNamedQuery("LearningLineItems.findByRequsitionId")
						.setParameter("reqId",requistionID);
		 List<LearningLineItems> items = query.getResultList();

	        return items;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LearningLineItems> findByLearnerId(Long learnerID) {
		Query query = em.createNamedQuery("LearningLineItems.findByLearnerId")
				.setParameter("lId",learnerID);
		List<LearningLineItems> items = query.getResultList();

    return items;
	}

	@Override
	@Transactional
	public void deleteById(Long requistionID, Long learnerID) {
//		em.getTransaction().begin();
		LearningLineItems item = findById(requistionID,learnerID);
		em.remove(item);
//		em.getTransaction().commit();
		

	}

	@Override
	public void deleteByRequsitionId(Long requistionID) {
		List<LearningLineItems> items = findByRequsitionId(requistionID);
		
		for(LearningLineItems item : items)
		{
			deleteById(item.getRequistionID(),item.getLearnerID());
		}

	}

	@Override
	public void deleteByLearnerId(Long learnerID) {
			List<LearningLineItems> items = findByLearnerId(learnerID);
		
		for(LearningLineItems item : items)
		{
			deleteById(item.getRequistionID(),item.getLearnerID());
		}

	}

	@Override
	@Transactional
	public void deleteByObject(LearningLineItems item) {
//		em.getTransaction().begin();
		em.remove(item);
//		em.getTransaction().commit();

	}

}
