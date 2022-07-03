package com.directory.service;

import com.directory.model.Car;
import com.directory.model.Statistics;
import com.directory.repository.CarRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public record StatisticsService(CarRepository carRepository) {
    public Statistics getStatistics() {
        return new Statistics(
                getCarCount(),
                getPopularBrand(),
                getPopularColor(),
                getFirstUpdateDate(),
                getLastUpdateDate()
        );
    }

    public Set<String> getAllBrand() {
        return carRepository
                .findAll()
                .stream()
                .map(Car::getBrand)
                .collect(Collectors.toSet());
    }

    public Set<String> getAllColor() {
        return carRepository
                .findAll()
                .stream()
                .map(Car::getColor)
                .collect(Collectors.toSet());
    }

    private int getCarCount() {
        return carRepository
                .findAll()
                .size();
    }

    private Date getFirstUpdateDate() {
        return carRepository
                .findAll()
                .stream()
                .max(Comparator.comparing(Car::getTimestamp))
                .map(Car::getTimestamp)
                .orElse(new Date());
    }

    private String getPopularColor() {
        return carRepository
                .findAll()
                .stream()
                .collect(Collectors.groupingBy(Car::getColor, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(Strings.EMPTY);
    }

    private String getPopularBrand() {
        return carRepository
                .findAll()
                .stream()
                .collect(Collectors.groupingBy(Car::getBrand, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(Strings.EMPTY);
    }

    private Date getLastUpdateDate() {
        return carRepository
                .findAll()
                .stream()
                .min(Comparator.comparing(Car::getTimestamp))
                .map(Car::getTimestamp)
                .orElse(new Date());
    }
}
