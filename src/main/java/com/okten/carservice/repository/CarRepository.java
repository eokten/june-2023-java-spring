package com.okten.carservice.repository;

import com.okten.carservice.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findAllByPower(Double power);

    List<Car> findAllByProducer(String producer);
}
