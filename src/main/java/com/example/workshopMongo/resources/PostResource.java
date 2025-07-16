package com.example.workshopMongo.resources;


import com.example.workshopMongo.domain.Post;
import com.example.workshopMongo.resources.util.URL;
import com.example.workshopMongo.servicies.PostService;
import com.example.workshopMongo.servicies.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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



    @RequestMapping(value = "/titlesearch", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findByTile(@RequestParam(value = "text", defaultValue = "") String text){
        text = URL.decodoParam(text);
        List<Post> list = postService.findByTitle(text);
        return  ResponseEntity.ok().body(list);
    }


    @RequestMapping(value = "/fullsearch", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> fullSearch(@RequestParam(value = "text", defaultValue = "") String text,
                                                 @RequestParam(value = "minDate", defaultValue = "") String minDate,
                                                 @RequestParam(value = "maxDate", defaultValue = "") String maxDate){
        text = URL.decodoParam(text);
        Date min = URL.convertDate(minDate, new Date(0L));
        Date max = URL.convertDate(maxDate, new Date());
        List<Post> list = postService.fullSearch(text, min, max);
        return  ResponseEntity.ok().body(list);
    }

}
