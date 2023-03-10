package com.example.demo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.dto.AuthorDTO;
import com.example.demo.dto.CommentDTO;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/02/2023"), "Good night", "Lorem ipsum 01", new AuthorDTO(alex));
		Post post2 = new Post(null, sdf.parse("25/02/2023"), "Good morning", "Lorem ipsum 02", new AuthorDTO(alex));
		
		CommentDTO c1 = new CommentDTO("Nice!", sdf.parse("26/02/2023"), new AuthorDTO(bob));
		CommentDTO c2 = new CommentDTO("Not bad.", sdf.parse("27/02/2023"), new AuthorDTO(maria));
		
		post1.getComments().addAll(Arrays.asList(c1));
		post2.getComments().addAll(Arrays.asList(c2));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		alex.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(alex);
	}
}
