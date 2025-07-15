package com.example.workshopMongo.servicies;

import com.example.workshopMongo.domain.User;
import com.example.workshopMongo.repositories.UserRepository;
import com.example.workshopMongo.servicies.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(String id){
        Optional<User> user = userRepository.findById(id);
        if (user == null){
            throw new ObjectNotFoundException("Objeto não encontrado");
        }
        return user;
    }
//ALTERNATIVA DE ACESSAR O USER SEM O OPTIONAL<>
//    public User findById(String id) {
//        return userRepository.findById(id)
//                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
//    }
}
