package com.example.workshopMongo.servicies;

import com.example.workshopMongo.domain.User;
import com.example.workshopMongo.dto.UserDto;
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
        if (user.isEmpty()) {
            throw new ObjectNotFoundException("Objeto não encontrado");
        }
        return user;
    }

//ALTERNATIVA DE ACESSAR O USER SEM O OPTIONAL<>
//    public User findById(String id) {
//        return userRepository.findById(id)
//                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
//    }

    public User inseet(User user){
        return userRepository.insert(user);
    }

    public void delete(String id){
        findById(id);
        userRepository.deleteById(id);
    }

    public User update(User user) {
        User newUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        updateData(newUser, user);
        return userRepository.save(newUser);
    }
    private void updateData(User newUser, User user) {
        newUser.setName(user.getName());
       // newUser.setEmail(user.getEmail());
    }

    public User fromDTO(UserDto userDto){
        return new User(userDto.getId(), userDto.getName(), userDto.getEmail());

    }
}
