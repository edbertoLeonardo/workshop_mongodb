package com.example.workshopMongo.resources;


import com.example.workshopMongo.domain.User;
import com.example.workshopMongo.dto.UserDto;
import com.example.workshopMongo.servicies.UserService;
import com.example.workshopMongo.servicies.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(){
        List<User> listUsers = userService.findAll();
        List<UserDto> listUsersDto = listUsers.stream().map(x -> new UserDto(x)).collect(Collectors.toList());
        return  ResponseEntity.ok().body(listUsersDto);
    }
//ALTERANTIVA PRA NÃO USAR O OPTIONAL<>
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public ResponseEntity<UserDto> findById(@PathVariable String id){
//        Optional<User> user = userService.findById(id);
//        return  ResponseEntity.ok().body(new UserDto(user));
//    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> findById(@PathVariable String id) {
        Optional<User> userOptional = userService.findById(id);

        User user = userOptional.orElseThrow(() ->
                new ObjectNotFoundException("Objeto não encontrado"));

        return ResponseEntity.ok().body(new UserDto(user));
    }

}
