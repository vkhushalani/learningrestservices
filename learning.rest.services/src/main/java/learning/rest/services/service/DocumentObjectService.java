package learning.rest.services.service;

import java.util.List;

import learning.rest.services.model.DocumentObject;

public interface DocumentObjectService {
	public List<DocumentObject> findAll();
	public DocumentObject update(DocumentObject doc);
	public DocumentObject create(DocumentObject doc);
	public DocumentObject findById(Long docId);
	public DocumentObject findByRepDocId (String repDocId);
	public void deleteById(Long docId);
	public void deleteByObject(DocumentObject doc);
}
