package learning.rest.services.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import learning.rest.services.model.DocumentObject;

@Transactional
@Component
public class DocumentObjectServiceImp implements DocumentObjectService {
	
	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DocumentObject> findAll() {
		Query query = em.createNamedQuery("DocumentObject.findAll");
		 List<DocumentObject> doc = query.getResultList();

	        return doc;
	}

	@Override
	@Transactional
	public DocumentObject update(DocumentObject doc) {
		em.merge(doc);
		return doc;
	}

	@Override
	@Transactional
	public DocumentObject create(DocumentObject doc) {
//		em.getTransaction().begin();
        em.persist(doc);
//        em.getTransaction().commit();
        return doc;
	}
	

	@Override
	public DocumentObject findById(Long docId) {
		DocumentObject doc = em.find(DocumentObject.class, docId);
        return doc;
	}

	@Override
	public DocumentObject findByRepDocId(String repDocId) {
		Query query = em.createNamedQuery("DocumentObject.findByRepDocId")
						.setParameter("rdId", repDocId);
		 DocumentObject doc = (DocumentObject) query.getSingleResult();

	        return doc;
	}
	

	@Override
	@Transactional
	public void deleteById(Long docId) {
//		em.getTransaction().begin();
		DocumentObject doc = em.find(DocumentObject.class, docId);
		em.remove(doc);
//		em.getTransaction().commit();

	}

	@Override
	@Transactional
	public void deleteByObject(DocumentObject doc) {
		// TODO Auto-generated method stub
		em.remove(doc);
	}

}
