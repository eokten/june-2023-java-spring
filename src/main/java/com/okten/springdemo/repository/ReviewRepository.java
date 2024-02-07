package com.okten.springdemo.repository;

import com.okten.springdemo.entity.Review;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {

//    @Query("{ productId: ?0 }")
    List<Review> findAllByProductId(Long productId);
}
