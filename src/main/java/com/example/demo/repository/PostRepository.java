package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.domain.Post;

public interface PostRepository extends MongoRepository<Post, String>{
	
}
