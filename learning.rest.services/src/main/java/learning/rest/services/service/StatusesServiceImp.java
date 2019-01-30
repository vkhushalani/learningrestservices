package learning.rest.services.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import learning.rest.services.model.Statuses;

@Transactional
@Component
public class StatusesServiceImp implements StatusesService {

	@PersistenceContext
	 EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Statuses> findAll() {
		Query query = em.createNamedQuery("Statuses.findAll");
		 List<Statuses> statuses = query.getResultList();

	        return statuses;
	}

	@Override
	@Transactional
	public Statuses create(Statuses status) {
//		em.getTransaction().begin();
        em.persist(status);
//        em.getTransaction().commit();
        return status;
	}

	@Override
	@Transactional
	public Statuses update(Statuses status) {
//		em.getTransaction().begin();
        em.merge(status);
//        em.getTransaction().commit();
        return status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Statuses> findByStatusId(int statusId) {
		Query query  = em.createNamedQuery("Statuses.findByStatusId")
							.setParameter("sId", statusId);
		List<Statuses> statuses = query.getResultList();

        return statuses;
	}
	
	@Override
	public Statuses findById(int statusId, String roleId) {
		Query query  = em.createNamedQuery("Statuses.findById")
				.setParameter("sId", statusId)
				.setParameter("rId", roleId);
			Statuses status = (Statuses) query.getSingleResult();

			return status;
	}

	@Override
	@Transactional
	public void deleteById(int statusId,String roleId) {
//		em.getTransaction().begin();
		Statuses status = findById(statusId,roleId);
		em.remove(status);
//		em.getTransaction().commit();
	}

	@Override
	public void deleteByObject(Statuses status) {
		// TODO Auto-generated method stub
		em.remove(status);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Statuses> findByRoleId(String roleId) {
		Query query  = em.createNamedQuery("Statuses.findByStatusId")
				.setParameter("rId", roleId);
		List<Statuses> statuses = query.getResultList();

		return statuses;
}
	

}
