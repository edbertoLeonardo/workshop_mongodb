package com.example.workshopMongo.config;

import com.example.workshopMongo.domain.Post;
import com.example.workshopMongo.domain.User;
import com.example.workshopMongo.dto.AuthorDTO;
import com.example.workshopMongo.dto.CommentDTO;
import com.example.workshopMongo.repositories.PostRepository;
import com.example.workshopMongo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;


    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        userRepository.deleteAll();
        postRepository.deleteAll();

        User user = new User(null, "Maria Silva", "maria@gmail.com");
        User userDois = new User(null, "Alex Souza", "alex@gmail.com");
        User userTres = new User(null, "Bob Pereira", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(user, userDois, userTres));

        Post post = new Post(null, sdf.parse("10/07/2025"), "Partiu viagem", "Vou viajar para São Paulo!!", new AuthorDTO(user));
        Post post2 = new Post(null, sdf.parse("11/07/2025"), "Bom dia", "Que dia Feliz :) ", new AuthorDTO(user));

        CommentDTO comment = new CommentDTO("Boa viagem.", sdf.parse("12/07/2025"), new AuthorDTO(userDois));
        CommentDTO comment2 = new CommentDTO("Aproveite.", sdf.parse("12/07/2025"), new AuthorDTO(userTres));
        CommentDTO comment3 = new CommentDTO("Tenha um ótimo dia.", sdf.parse("13/07/2025"), new AuthorDTO(userDois));

        post.getCommentsList().addAll(Arrays.asList(comment, comment2));
        post2.getCommentsList().addAll(Arrays.asList(comment3));

        postRepository.saveAll(Arrays.asList(post, post2));

        user.getPostsList().addAll(Arrays.asList(post, post2));
        userRepository.save(user);



    }
}
