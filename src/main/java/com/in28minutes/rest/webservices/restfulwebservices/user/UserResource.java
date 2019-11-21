package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService service;
	
	@RequestMapping(path = "/users", method = RequestMethod.GET, produces = "application/json")
	public List<User> retriveAllUsers(){
		return service.findAll();
	}
	
	@RequestMapping(path = "/users/{id}", method = RequestMethod.GET, produces = "application/json")
	public Resource<User> retrieveUser(@PathVariable int id) {
		User user = service.findOne(id); 
		if(user == null) {
			throw new UserNotFoundException("User with id : " + id + " NOT FOUND"); 
		}
		
		//"all-users"  SERVER_PATH + "/users"
		//retrieveAllUsers
		Resource<User> resource = new Resource<User>(user);
		
		ControllerLinkBuilder link = linkTo(methodOn(this.getClass()).retriveAllUsers());
		ControllerLinkBuilder link2 = linkTo(methodOn(this.getClass()).retrieveUser(id));
		
		resource.add(link.withRel("all-users"));
		resource.add(link2.withRel("retrieve-user"));
		
		return resource;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id); 
		if(user == null) {
			throw new UserNotFoundException("User with id : " + id + " NOT FOUND"); 
		}
	}
	
	
}
