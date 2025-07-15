package com.example.workshopMongo.resources;


import com.example.workshopMongo.domain.User;
import com.example.workshopMongo.dto.UserDto;
import com.example.workshopMongo.servicies.UserService;
import com.example.workshopMongo.servicies.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
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

    //@PostMapping  ALTERNATIVA AO @RequestMapping
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserDto> insert(@RequestBody UserDto userDto) {
        User user = userService.fromDTO(userDto);
        user = userService.inseet(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserDto> update(@RequestBody UserDto userDto, @PathVariable String id) {
        User user = userService.fromDTO(userDto);
        user.setId(id);
        user = userService.update(user);

        return ResponseEntity.noContent().build();
    }

}
