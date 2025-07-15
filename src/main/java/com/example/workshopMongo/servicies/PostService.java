package com.example.workshopMongo.servicies;

import com.example.workshopMongo.domain.Post;
import com.example.workshopMongo.repositories.PostRepository;
import com.example.workshopMongo.servicies.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;


    public Optional<Post> findById(String id){
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) {
            throw new ObjectNotFoundException("Objeto não encontrado");
        }
        return post;
    }


    public List<Post> findByTitle(String text){
        return postRepository.searchTitle(text);
    }
}
