package learning.rest.services.service;

import java.util.List;

import learning.rest.services.model.Roles;

public interface RolesService {
	public List<Roles> findAll();
	public Roles create(Roles role);
	public Roles update(Roles role);
	public Roles findById(Long roleId);
	public void deleteById(Long roleId);
	public void deleteByObject(Roles role);

}
