package com.okten.carservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.okten.carservice.dto.SearchCriteria;
import com.okten.carservice.entity.Car;
import com.okten.carservice.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getCars() {
        return ResponseEntity.ok(carService.findAll());
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCar(@PathVariable("id") Long id) {
        return ResponseEntity.of(carService.findById(id));
    }

    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car createdCar = carService.createCar(car);
        URI uriOfCreatedCar = UriComponentsBuilder.fromPath("/cars/{id}").build(createdCar.getId());
        return ResponseEntity
                .created(uriOfCreatedCar)
                .body(createdCar);
    }

    @SneakyThrows
    @PutMapping("/cars/{id}/image")
    public ResponseEntity<Void> updateCarImage(@PathVariable("id") Long carId, @RequestParam("file") MultipartFile attachment) {
        carService.updateCarImage(carId, attachment.getBytes());
        return ResponseEntity.accepted().build();
    }

    @SneakyThrows
    @PostMapping("/v2/cars")
    public ResponseEntity<Car> createCarV2(@RequestParam("payload") String payload, @RequestParam("file") MultipartFile attachment) {
        Car car = new ObjectMapper().readValue(payload, Car.class);
        Car createdCar;

        if (attachment.isEmpty()) {
            createdCar = carService.createCar(car);
        } else {
            createdCar = carService.createCar(car, attachment.getBytes());
        }

        return ResponseEntity.ok(createdCar);
    }

    @SneakyThrows
    @PostMapping("/v3/cars")
    public ResponseEntity<Car> createCarV2(@RequestBody Car car, @RequestParam("file") MultipartFile attachment) {
        Car createdCar;

        if (attachment.isEmpty()) {
            createdCar = carService.createCar(car);
        } else {
            createdCar = carService.createCar(car, attachment.getBytes());
        }

        return ResponseEntity.ok(createdCar);
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable("id") Long id) {
        carService.deleteCar(id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/cars/power/{value}")
    public ResponseEntity<List<Car>> getCarsByPower(@PathVariable("value") Double power) {
        return ResponseEntity.ok(carService.findAllByPower(power));
    }

    @GetMapping("/cars/producer/{value}")
    public ResponseEntity<List<Car>> getCarsByProducer(@PathVariable("value") String producer) {
        return ResponseEntity.ok(carService.findAllByProducer(producer));
    }

    @PostMapping("/cars/search")
    public ResponseEntity<List<Car>> searchCars(@RequestBody SearchCriteria searchCriteria) {
        Car probe = new Car();
        probe.setPower(searchCriteria.getPower());
        probe.setProducer(searchCriteria.getProducer());
        return ResponseEntity.ok(carService.findAllByExample(probe));
    }
}
