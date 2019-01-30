package learning.rest.services.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import learning.rest.services.model.Users;
@Transactional
@Component
public class UsersServiceImp implements UsersService {
	@PersistenceContext
	 EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> findAll() {
		Query query = em.createNamedQuery("Users.findAll");
		 List<Users> users = query.getResultList();

	        return users;
	}

	@Override
	@Transactional
	public Users create(Users user) {
//		em.getTransaction().begin();
        em.persist(user);
//        em.getTransaction().commit();
        return user;
	}
	
	@Override
	@Transactional
	public Users update(Users user) {
//		em.getTransaction().begin();
        em.merge(user);
//        em.getTransaction().commit();
        return user;
	}

	@Override
	public Users findById(Long id) {
		Users user = em.find(Users.class, id);
        return user;
	}

	@Override
	public Users findByPUserId(String pId) {
		Query query = em.createNamedQuery("Users.findByPUserId")
						.setParameter("pId", pId);
		 Users user = (Users) query.getSingleResult();
		 return user;
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
//		em.getTransaction().begin();
		Users user = em.find(Users.class, id);
		em.remove(user);
//		em.getTransaction().commit();

	}

	@Override
	@Transactional
	public void deleteByObject(Users user) {
//		em.getTransaction().begin();
		em.remove(user);
//		em.getTransaction().commit();

	}

}
