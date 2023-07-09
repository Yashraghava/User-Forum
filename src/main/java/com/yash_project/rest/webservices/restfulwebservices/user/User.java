package com.yash_project.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.Mapping;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity(name="user_details")
public class User {
	@Id
	@GeneratedValue
	private int id;
	
	@Size(min=2,message="Name should have atleast 2 characters")
	private String name;
	
	@Past(message="Birthdate should always be in the Past")
	private LocalDate birthDate;

	@OneToMany(mappedBy="user")
	@JsonIgnore
	private List<Post> posts;

	public User() {
		super();
	}
	public User(int id, String name, LocalDate birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	@Override
	public String toString() {
		return "User [Id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}

}
