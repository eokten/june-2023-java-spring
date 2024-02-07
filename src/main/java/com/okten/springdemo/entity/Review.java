package com.okten.springdemo.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Data
@Document("reviews")
public class Review {

    @MongoId
    private ObjectId id;

    private Long productId;

    private String text;
}
