package com.okten.carservice.repository;

import com.okten.carservice.entity.Car;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends MongoRepository<Car, String> {

    List<Car> findAllByPower(Double power);

    List<Car> findAllByProducer(String producer);
}
