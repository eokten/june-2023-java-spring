package com.okten.carservice.controller;

import com.okten.carservice.dto.SearchCriteria;
import com.okten.carservice.entity.Car;
import com.okten.carservice.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarRepository carRepository;

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getCars() {
        List<Car> cars = carRepository.findAll();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCar(@PathVariable("id") Long id) {
        Optional<Car> car = carRepository.findById(id);
        return ResponseEntity.of(car);
    }

    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car createdCar = carRepository.save(car);
//        return ResponseEntity.ok(createdCar) == new ResponseEntity<>(createdCar, HttpStatus.OK)
        URI uriOfCreatedCar = UriComponentsBuilder.fromPath("/cars/{id}").build(createdCar.getId());
        return ResponseEntity
                .created(uriOfCreatedCar)
                .body(createdCar);
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable("id") Long id) {
        carRepository.deleteById(id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/cars/power/{value}")
    public ResponseEntity<List<Car>> getCarsByPower(@PathVariable("value") Double power) {
        List<Car> cars = carRepository.findAllByPower(power);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/cars/producer/{value}")
    public ResponseEntity<List<Car>> getCarsByProducer(@PathVariable("value") String producer) {
        List<Car> cars = carRepository.findAllByProducer(producer);
        return ResponseEntity.ok(cars);
    }

    @PostMapping("/cars/search")
    public ResponseEntity<List<Car>> searchCars(@RequestBody SearchCriteria searchCriteria) {
        Car probe = new Car();
        probe.setPower(searchCriteria.getPower());
        probe.setProducer(searchCriteria.getProducer());
        List<Car> result = carRepository.findAll(Example.of(probe));
        return ResponseEntity.ok(result);
    }
}
