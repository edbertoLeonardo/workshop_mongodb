package com.example.workshopMongo.resources;


import com.example.workshopMongo.domain.Post;
import com.example.workshopMongo.domain.User;
import com.example.workshopMongo.dto.UserDto;
import com.example.workshopMongo.servicies.PostService;
import com.example.workshopMongo.servicies.UserService;
import com.example.workshopMongo.servicies.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService postService;


//ALTERANTIVA PRA NÃO USAR O OPTIONAL<>
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public ResponseEntity<UserDto> findById(@PathVariable String id){
//        Optional<User> user = userService.findById(id);
//        return  ResponseEntity.ok().body(new UserDto(user));
//    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Optional<Post> userOptional = postService.findById(id);

        Post post = userOptional.orElseThrow(() ->
                new ObjectNotFoundException("Objeto não encontrado"));

        return ResponseEntity.ok().body(post);
    }

}
