package com.yash_project.rest.webservices.restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash_project.rest.webservices.restfulwebservices.user.Post;
import com.yash_project.rest.webservices.restfulwebservices.user.User;

public interface PostRepository extends JpaRepository<Post,Integer>{

}
