package learning.rest.services.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import learning.rest.services.model.RequisitionLogRel;

@Transactional
@Component
public class RequisitionLogRelServiceImp implements RequisitionLogRelService {
	
	
	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RequisitionLogRel> findAll() {
		Query query = em.createNamedQuery("RequisitionLogRel.findAll");
		 List<RequisitionLogRel> items = query.getResultList();

	        return items;
	}

	@Override
	@Transactional
	public RequisitionLogRel create(RequisitionLogRel item) {
//		em.getTransaction().begin();
        em.persist(item);
//        em.getTransaction().commit();
        return item;
	}

	@Override
	@Transactional
	public RequisitionLogRel update(RequisitionLogRel item) {
//		em.getTransaction().begin();
        em.merge(item);
//        em.getTransaction().commit();
        return item;
	}

	@Override
	public RequisitionLogRel findById(Long requistionID, Long logID) {
		Query query = em.createNamedQuery("RequisitionLogRel.findById")
				.setParameter("rID",requistionID)
				.setParameter("lId",logID);
		RequisitionLogRel item = (RequisitionLogRel) query.getSingleResult();

		return item;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RequisitionLogRel> findByRequsitionId(Long requistionID) {
		Query query = em.createNamedQuery("RequisitionLogRel.findByRequisitionId")
				.setParameter("rId",requistionID);
		List<RequisitionLogRel> items = query.getResultList();

		return items;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RequisitionLogRel> findByLogId(Long logID) {
		Query query = em.createNamedQuery("RequisitionLogRel.findByLogId")
				.setParameter("lId",logID);
		List<RequisitionLogRel> items = query.getResultList();

		return items;
	}

	@Override
	@Transactional
	public void deleteById(Long requistionID, Long logID) {
//		em.getTransaction().begin();
		RequisitionLogRel item = findById(requistionID,logID);
		em.remove(item);
//		em.getTransaction().commit();
	}

	@Override
	public void deleteByRequsitionId(Long requistionID) {
		List<RequisitionLogRel> items = findByRequsitionId(requistionID);
		
		for(RequisitionLogRel item : items)
		{
			deleteById(item.getRequistionID(),item.getLogID());
		}

	}

	@Override
	public void deleteByLogId(Long logID) {
		List<RequisitionLogRel> items = findByLogId(logID);
		
		for(RequisitionLogRel item : items)
		{
			deleteById(item.getRequistionID(),item.getLogID());
		}

	}

	@Override
	@Transactional
	public void deleteByObject(RequisitionLogRel item) {
	
		em.remove(item);
	}

	@Override
	public int getLatestSequenceNumber(Long requistionID) {
		List<RequisitionLogRel> lList= findByRequsitionId(requistionID);
		int max = 0;
		for(RequisitionLogRel item : lList)
		{	if(item.getSequence() > max)
			{max = item.getSequence();}
		}
		return max;
	}

}
