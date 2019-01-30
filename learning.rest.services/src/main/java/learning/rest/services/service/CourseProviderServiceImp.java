package learning.rest.services.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import learning.rest.services.model.CourseProvider;

@Transactional
@Component
public class CourseProviderServiceImp implements CourseProviderService {
	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CourseProvider> findAll() {
		Query query = em.createNamedQuery("CourseProvider.findAll");
		 List<CourseProvider> addresses = query.getResultList();

	        return addresses;
	}

	@Override
	@Transactional
	public CourseProvider create(CourseProvider provider) {
        em.persist(provider);
        return provider;
	}
	
	@Override
	@Transactional
	public CourseProvider update(CourseProvider provider) {
        em.merge(provider);
        return provider;
	}
	@Override
	public CourseProvider findById(Long providerID) {
		CourseProvider provider = em.find(CourseProvider.class, providerID);
        return provider;
	}

	@Override
	@Transactional
	public void deleteById(Long providerID) {
//		em.getTransaction().begin();
		CourseProvider provider = em.find(CourseProvider.class, providerID);
		em.remove(provider);
//		em.getTransaction().commit();

	}

	@Override
	@Transactional
	public void deleteByObject(CourseProvider provider) {
//		em.getTransaction().begin();
		em.remove(provider);
//		em.getTransaction().commit();

	}

}
