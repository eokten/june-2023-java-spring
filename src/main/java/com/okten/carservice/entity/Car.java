package com.okten.carservice.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document("cars")
public class Car {

    @MongoId
    private String id;

    private String model;

    private String producer;

    private Double power;
}
