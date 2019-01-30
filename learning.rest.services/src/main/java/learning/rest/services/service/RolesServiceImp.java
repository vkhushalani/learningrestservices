package learning.rest.services.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import learning.rest.services.model.Roles;
@Transactional
@Component
public class RolesServiceImp implements RolesService {

	@PersistenceContext
	 EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Roles> findAll() {
		Query query = em.createNamedQuery("Roles.findAll");
		 List<Roles> roles = query.getResultList();

	        return roles;
	}


	@Override
	@Transactional
	public Roles create(Roles role) {
	
		em.persist(role);
		return role;
	}


	@Override
	@Transactional
	public Roles update(Roles role) {
	    em.merge(role);
		return role;
	}

	@Override
	public Roles findById(Long roleId) {
		Roles role = em.find(Roles.class, roleId);
        return role;
	}


	@Override
	@Transactional
	public void deleteById(Long roleId) {
		Roles role = em.find(Roles.class, roleId);
		em.remove(role);

	}


	@Override
	@Transactional
	public void deleteByObject(Roles role) {
		em.remove(role);

	}

}
