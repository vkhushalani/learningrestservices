package learning.rest.services.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import learning.rest.services.model.LearningRequistionDocumentRel;

@Transactional
@Component
public class LearningRequistionDocumentRelServiceImp implements LearningRequistionDocumentRelService {
	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LearningRequistionDocumentRel> findAll() {
		Query query = em.createNamedQuery("LearningRequistionDocumentRel.findAll");
		 List<LearningRequistionDocumentRel> items = query.getResultList();

	        return items;
	}

	@Override
	@Transactional
	public LearningRequistionDocumentRel create(LearningRequistionDocumentRel item) {
//		em.getTransaction().begin();
        em.persist(item);
//        em.getTransaction().commit();
        return item;
	}

	@Override
	@Transactional
	public LearningRequistionDocumentRel update(LearningRequistionDocumentRel item) {
//		em.getTransaction().begin();
        em.merge(item);
//        em.getTransaction().commit();
        return item;
	}

	@Override
	public LearningRequistionDocumentRel findById(Long requistionID, Long docId) {
		Query query = em.createNamedQuery("LearningRequistionDocumentRel.findById")
				.setParameter("rId",requistionID)
				.setParameter("dId",docId);
		LearningRequistionDocumentRel item = (LearningRequistionDocumentRel) query.getSingleResult();

		return item;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LearningRequistionDocumentRel> findByRequisitionId(Long requistionID) {
		Query query = em.createNamedQuery("LearningRequistionDocumentRel.findByRequisitionId")
				.setParameter("rId",requistionID);
		List<LearningRequistionDocumentRel> items = query.getResultList();

    return items;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LearningRequistionDocumentRel> findByDocId(Long docId) {
		Query query = em.createNamedQuery("LearningRequistionDocumentRel.findByDocId")
				.setParameter("dId",docId);
		List<LearningRequistionDocumentRel> items = query.getResultList();

    return items;
	}

	@Override
	@Transactional
	public void deleteById(Long requistionID, Long docId) {
//		em.getTransaction().begin();
		LearningRequistionDocumentRel item = findById(requistionID,docId);
		em.remove(item);
//		em.getTransaction().commit();
	}

	@Override
	public void deleteByRequisitionId(Long requistionID) {
		List<LearningRequistionDocumentRel> items = findByRequisitionId(requistionID);
		
		for(LearningRequistionDocumentRel item : items)
		{
			deleteById(item.getRequistionID(),item.getDocId());
		}

	}

	@Override
	
	public void deleteByDocId(Long docId) {
		List<LearningRequistionDocumentRel> items = findByDocId(docId);
		
		for(LearningRequistionDocumentRel item : items)
		{
			deleteById(item.getRequistionID(),item.getDocId());
		}
	}

	@Override
	@Transactional
	public void deleteByObject(LearningRequistionDocumentRel item) {
//		em.getTransaction().begin();
		em.remove(item);
//		em.getTransaction().commit();

	}

	@Override
	public LearningRequistionDocumentRel findByIdSpecific(Long requistionID, Long docId, Long learnerID) {
		Query query = em.createNamedQuery("LearningRequistionDocumentRel.findByIdSpecific")
				.setParameter("rId",requistionID)
				.setParameter("dId",docId)
				.setParameter("lId", learnerID);
		LearningRequistionDocumentRel item = (LearningRequistionDocumentRel) query.getSingleResult();

		return item;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LearningRequistionDocumentRel> findByLearnerId(Long learnerID) {
		Query query = em.createNamedQuery("LearningRequistionDocumentRel.findByLearnerId")
				.setParameter("lId",learnerID);
		List<LearningRequistionDocumentRel> items = query.getResultList();
    return items;
	}

}
