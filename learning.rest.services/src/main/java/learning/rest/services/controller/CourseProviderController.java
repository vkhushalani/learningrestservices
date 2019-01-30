package learning.rest.services.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import learning.rest.services.model.CourseProvider;
import learning.rest.services.service.CourseProviderService;

import com.sap.security.um.user.PersistenceException;
import com.sap.security.um.user.UnsupportedUserAttributeException;


@RestController
public class CourseProviderController  {
	
		
	 Logger logger = LoggerFactory.getLogger(CourseProviderController.class);
	 @Autowired
	 CourseProviderService CourseProviderService;
	
	
	
	@GetMapping("/providers")
	public ResponseEntity<List<CourseProvider>> getAllProviders() throws PersistenceException, UnsupportedUserAttributeException {
		
		 List<CourseProvider> providers = CourseProviderService.findAll();
	     return ResponseEntity.ok().body(providers);
	 }
	@GetMapping("/providers/{id}")
	   public ResponseEntity<CourseProvider> getAddress(@PathVariable("id") long provideID) {
		CourseProvider address = CourseProviderService.findById(provideID);
		 
	      return ResponseEntity.ok().body(address);
	   }
	

}
