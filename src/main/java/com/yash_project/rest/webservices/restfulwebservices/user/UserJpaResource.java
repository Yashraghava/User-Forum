package com.yash_project.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.yash_project.rest.webservices.restfulwebservices.repository.PostRepository;
import com.yash_project.rest.webservices.restfulwebservices.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {
	
	private UserDaoService service;
	
	private UserRepository repository;
	private PostRepository postRepository;
	
	public UserJpaResource(UserDaoService service,UserRepository repository,PostRepository postRepository) {
		this.service = service;
		this.repository = repository;
		this.postRepository =postRepository;
	}
	
	@RequestMapping(method=RequestMethod.GET,path="/jpa/users")
	public List<User> retrieveAllUsers(){
		return repository.findAll();
	}
	
	@RequestMapping(method=RequestMethod.GET,path="/jpa/users/{id}")
	public User retrieveUser(@PathVariable int id){
		Optional<User> user = repository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("Id :"+id);
		}
		return user.get();
	}

	@RequestMapping(method=RequestMethod.GET,path="/jpa/users_ho/{id}")
	public EntityModel<User> retrieveUserHATEOAS(@PathVariable int id){
		Optional<User> user = repository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("Id :"+id);
		}
		EntityModel<User> entityModel = EntityModel.of(user.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
	}


	@RequestMapping(method=RequestMethod.DELETE,path="/jpa/users/{id}")
	public void deleteUser(@PathVariable int id){
		repository.deleteById(id);
	}
	
	@RequestMapping(method=RequestMethod.GET,path="/jpa/users/{id}/posts")
	public List<Post> retrievePostsForUser(@PathVariable int id){
		Optional<User> user = repository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("Id :"+id);
		}
		List<Post> posts = user.get().getPosts();
		return posts;
	}

	@RequestMapping(method=RequestMethod.POST,path="/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user){
		User savedUser = repository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	
	}
	

	@RequestMapping(method=RequestMethod.POST,path="/jpa/users/{id}/posts")
	public ResponseEntity<Post> createPostsForUser(@Valid @RequestBody Post post,@PathVariable int id){
		Optional<User> user = repository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("Id :"+id);
		}
		post.setUser(user.get());
		Post savedPost = postRepository.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(savedPost.getId()).toUri();
		return ResponseEntity.created(location).build();		
	}
	
}
