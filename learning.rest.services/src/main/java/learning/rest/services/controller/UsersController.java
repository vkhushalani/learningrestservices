package learning.rest.services.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import learning.rest.services.model.Users;
import learning.rest.services.service.UsersService;

@RestController
public class UsersController {
	
	@Autowired
	UsersService usersService;
	
	@GetMapping("/users")
	public ResponseEntity<List<Users>> getAllUsers() {
		 List<Users> users = usersService.findAll();
	     return ResponseEntity.ok().body(users);
	 }
	@GetMapping("/users/{id}")
	   public ResponseEntity<Users> getAddress(@PathVariable("id") long userID) {
		Users user = usersService.findById(userID);
		 
	      return ResponseEntity.ok().body(user);
	   }

}
