package com.example.workshopMongo.config;

import com.example.workshopMongo.domain.User;
import com.example.workshopMongo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();

        User user = new User(null, "Maria Silva", "maria@gmail.com");
        User userDois = new User(null, "Alex Souza", "alex@gmail.com");
        User userTres = new User(null, "Bob Pereira", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(user, userDois, userTres));
    }
}
