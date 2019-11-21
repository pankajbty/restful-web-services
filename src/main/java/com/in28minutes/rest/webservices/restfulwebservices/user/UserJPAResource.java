package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
public class UserJPAResource {
	
	@Autowired
	private UserDaoService service;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@RequestMapping(path = "/jpa/users", method = RequestMethod.GET, produces = "application/json")
	public List<User> retriveAllUsers(){
//		return service.findAll();
		return userRepository.findAll();
	}
	
	@RequestMapping(path = "/jpa/users/{id}", method = RequestMethod.GET, produces = "application/json")
	public Resource<User> retrieveUser(@PathVariable int id) {
//		User user = service.findOne(id);
//		if(user == null) {
//			throw new UserNotFoundException("User with id : " + id + " NOT FOUND"); 
//		}
		
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("User with id : " + id + " NOT FOUND"); 
		}
		
		//"all-users"  SERVER_PATH + "/users"
		//retrieveAllUsers
//		Resource<User> resource = new Resource<User>(user);
		Resource<User> resource = new Resource<User>(user.get());
		
		ControllerLinkBuilder link = linkTo(methodOn(this.getClass()).retriveAllUsers());
		ControllerLinkBuilder link2 = linkTo(methodOn(this.getClass()).retrieveUser(id));
		
		resource.add(link.withRel("all-users"));
		resource.add(link2.withRel("retrieve-user"));
		
		return resource;
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
//		User savedUser = service.save(user);
		User savedUser = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
//		User user = service.deleteById(id);
		userRepository.deleteById(id);
//		if(user == null) {
//			throw new UserNotFoundException("User with id : " + id + " NOT FOUND"); 
//		}
	}
	
	@RequestMapping(path = "/jpa/users/{id}/posts", method = RequestMethod.GET, produces = "application/json")
	public List<Post> retriveAllUsers(@PathVariable int id){
//		return service.findAll();
		Optional<User> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id :- " + id);
		}
		return userOptional.get().getPosts();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
//		User savedUser = service.save(user);
		Optional<User> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id :- " + id);
		}
		
		User user = userOptional.get();
		post.setUser(user);
		postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(post.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
}
