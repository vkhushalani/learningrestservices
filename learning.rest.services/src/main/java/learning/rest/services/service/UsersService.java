package learning.rest.services.service;

import java.util.List;

import learning.rest.services.model.Users;

public interface UsersService {
	public List<Users> findAll();
	public Users create(Users user);
	public Users update(Users user);
	public Users findById(Long id);
	public Users findByPUserId(String pId);
	public void deleteById(Long id);
	public void deleteByObject(Users user);
}
