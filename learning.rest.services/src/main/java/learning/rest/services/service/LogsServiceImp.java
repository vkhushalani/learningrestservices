package learning.rest.services.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import learning.rest.services.model.Logs;

@Transactional
@Component
public class LogsServiceImp implements LogsService {

	@PersistenceContext
	 EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Logs> findAll() {
		Query query = em.createNamedQuery("Logs.findAll");
		 List<Logs> logs = query.getResultList();

	        return logs;
	}

	@Override
	@Transactional
	public Logs create(Logs log) {
//		em.getTransaction().begin();
        em.persist(log);
//        em.getTransaction().commit();
        return log;
	}

	@Override
	@Transactional
	public Logs update(Logs log) {
//		em.getTransaction().begin();
        em.merge(log);
//        em.getTransaction().commit();
        return log;
	}

	@Override
	public Logs findById(Long logId) {
		Logs log = em.find(Logs.class, logId);
        return log;
	}

	@Override
	@Transactional
	public void deleteById(Long logId) {
//		em.getTransaction().begin();
		Logs log = em.find(Logs.class, logId);
		em.remove(log);
//		em.getTransaction().commit();

	}

	@Override
	public void deleteByObject(Logs log) {
//		em.getTransaction().begin();
		em.remove(log);
//		em.getTransaction().commit();

	}

}
