package learning.rest.services.service;

import java.util.List;

import learning.rest.services.model.Logs;

public interface LogsService {
	public List<Logs> findAll();
	public Logs create(Logs log);
	public Logs update(Logs log);
	public Logs findById(Long logId);
	public void deleteById(Long logId);
	public void deleteByObject(Logs log);

}
