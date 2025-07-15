package com.example.workshopMongo.repositories;

import com.example.workshopMongo.domain.Post;
import com.example.workshopMongo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
