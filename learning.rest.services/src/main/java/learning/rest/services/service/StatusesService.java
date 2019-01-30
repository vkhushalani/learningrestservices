package learning.rest.services.service;

import java.util.List;

import learning.rest.services.model.Statuses;

public interface StatusesService {
	public List<Statuses> findAll();
	public Statuses create(Statuses status);
	public Statuses update(Statuses status);
	public List<Statuses> findByStatusId(int statusId);
	public List<Statuses> findByRoleId(String roleId);
	public void deleteById(int statusId, String roleId);
	public void deleteByObject(Statuses status);
	public Statuses findById(int statusId, String roleId);
}
