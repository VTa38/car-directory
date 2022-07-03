package com.directory.repository;

import com.directory.model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    Optional<Car> findByNumber(String number);

    List<Car> findByBrand(String brand);

    List<Car> findByColor(String color);

    List<Car> findAll();
}
