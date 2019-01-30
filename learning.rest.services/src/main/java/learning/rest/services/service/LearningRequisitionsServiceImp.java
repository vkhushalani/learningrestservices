package learning.rest.services.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import learning.rest.services.model.LearningRequisitions;

@Component
@Transactional
public class LearningRequisitionsServiceImp implements LearningRequisitionsService {
	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LearningRequisitions> findAll() {
		Query query = em.createNamedQuery("LearningRequisitions.findAll");
		 List<LearningRequisitions> requisitions = query.getResultList();

	        return requisitions;
	}

	@Override
	@Transactional
	public LearningRequisitions create(LearningRequisitions requisitions) {
//		em.getTransaction().begin();
        em.persist(requisitions);
//        em.getTransaction().commit();
        return requisitions;
	}
	
	@Override
	@Transactional
	public LearningRequisitions update(LearningRequisitions requisitions) {
//		em.getTransaction().begin();
        em.merge(requisitions);
//        em.getTransaction().commit();
        return requisitions;
	}

	@Override
	public LearningRequisitions findById(Long requistionID) {
		LearningRequisitions requisition = em.find(LearningRequisitions.class, requistionID);
        return requisition;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LearningRequisitions> findByRequestorId(Long requestorID) {
		Query query = em.createNamedQuery("LearningRequisitions.findByRequestorId")
						.setParameter("rId", requestorID);
		 List<LearningRequisitions> requisitions = query.getResultList();

	        return requisitions;
	}


	@Override
	@Transactional
	public void deleteById(Long requistionID) {
//		em.getTransaction().begin();
		LearningRequisitions requisition = em.find(LearningRequisitions.class, requistionID);
		em.remove(requisition);
//		em.getTransaction().commit();

	}

	@Override
	@Transactional
	public void deleteByObject(LearningRequisitions requisitions) {
//		em.getTransaction().begin();
		em.remove(requisitions);
//		em.getTransaction().commit();

	}
}
