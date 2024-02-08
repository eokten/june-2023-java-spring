package com.okten.carservice.service;

import com.okten.carservice.entity.Car;
import com.okten.carservice.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    private final MailService mailService;

    @Value("${mail.sendTo}")
    private String sendTo;

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Optional<Car> findById(String id) {
        return carRepository.findById(id);
    }

    public List<Car> findAllByPower(Double power) {
        return carRepository.findAllByPower(power);
    }

    public List<Car> findAllByProducer(String producer) {
        return carRepository.findAllByProducer(producer);
    }

    public List<Car> findAllByExample(Car example) {
        return carRepository.findAll(Example.of(example));
    }

    public void updateCarImage(String carId, byte[] imageBytes) {
        carRepository
                .findById(carId)
                .ifPresent(car -> {
                    mailService.sendMail(
                            sendTo,
                            "Car image was updated",
                            "Car %s image was update".formatted(car.getModel()),
                            imageBytes);
                });
    }

    public Car createCar(Car source) {
        Car savedCar = carRepository.save(source);
        mailService.sendMail(
                sendTo,
                "New car created",
                "Car %s was created".formatted(savedCar.getModel()));
        return savedCar;
    }

    public Car createCar(Car source, byte[] imageBytes) {
        Car savedCar = carRepository.save(source);
        mailService.sendMail(
                sendTo,
                "New car created",
                "Car %s was created".formatted(savedCar.getModel()));
        return savedCar;
    }

    public void deleteCar(String carId) {
        carRepository
                .findById(carId)
                .ifPresent(car -> {
                    carRepository.deleteById(car.getId());
                    mailService.sendMail(
                            sendTo,
                            "Car was deleted",
                            "Car %s was just deleted".formatted(car.getModel()));
                });
    }
}
