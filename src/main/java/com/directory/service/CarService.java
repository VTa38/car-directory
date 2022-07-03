package com.directory.service;

import com.directory.model.Car;
import com.directory.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public record CarService(CarRepository carRepository) {

    public List<Car> list() {
        return carRepository.findAll();
    }

    public Optional<Car> findById(Long id) {
        return carRepository.findById(id);
    }

    public Optional<Car> findByNumber(String number) {
        return carRepository.findByNumber(number);
    }

    public List<Car> findByBrand(String brand) {
        return carRepository.findByBrand(brand);
    }

    public List<Car> findByColor(String color) {
        return carRepository.findByColor(color);
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public Optional<Car> deleteById(Long id) {
        carRepository.deleteById(id);
        return carRepository.findById(id);
    }
}
