package com.directory.service;

import com.directory.model.Car;
import com.directory.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StatisticsService {

    private CarRepository carRepository;

    public StatisticsService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public int findCarCount() {
        int i = 0;
        Iterable<Car> all = carRepository.findAll();
        for (Car car : all) {
            i++;
        }
        return i;
    }

    public Date findAlgerDateOfEntry() {
        Iterable<Car> all = carRepository.findAll();
        Date alderDate = null;
        int i = 0;
        for (Car car : all) {
            Date currantDate = car.getDate();
            if (i == 0) {
                alderDate = currantDate;
                i++;
            }
            if (car.getDate().before(alderDate)) {
                alderDate = currantDate;
            }
        }
        return alderDate;
    }

    public String findPopularColor() {
        Map<String, Integer> allColor = new HashMap<>();
        Iterable<Car> all = carRepository.findAll();
        for (Car car : all) {
            String currentColor = car.getColor();
            if (allColor.containsKey(currentColor)) {
                allColor.put(currentColor, allColor.get(currentColor) + 1);
            } else {
                allColor.put(currentColor, 1);
            }
        }
        Integer popular = Collections.max(allColor.values());

        for (Map.Entry<String, Integer> entry : allColor.entrySet()) {
            if (entry.getValue().equals(popular)) {
                return entry.getKey();
            }
        }
        return "0";
    }

    public Set<String> findAllColor() {
        Set<String> allColor = new HashSet<>();
        Iterable<Car> all = carRepository.findAll();
        for (Car car : all) {
            allColor.add(car.getColor());
        }
        return allColor;
    }

    public String findPopularBrand() {
        Map<String, Integer> allBrand = new HashMap<>();
        Iterable<Car> all = carRepository.findAll();
        for (Car car : all) {
            String currentBrand = car.getBrand();
            if (allBrand.containsKey(currentBrand)) {
                allBrand.put(currentBrand, allBrand.get(currentBrand) + 1);
            } else {
                allBrand.put(currentBrand, 1);
            }
        }
        Integer popular = Collections.max(allBrand.values());

        for (Map.Entry<String, Integer> entry : allBrand.entrySet()) {
            if (entry.getValue().equals(popular)) {
                return entry.getKey();
            }
        }
        return "0";
    }

    public Set<String> findAllBrand() {
        Set<String> allBrand = new HashSet<>();
        Iterable<Car> all = carRepository.findAll();
        for (Car car : all) {
            allBrand.add(car.getBrand());
        }
        return allBrand;
    }


    public Date findLastDateOfRefresh() {
        Iterable<Car> all = carRepository.findAll();
        Date lastDate = null;
        int i = 0;
        for (Car car : all) {
            Date currantDate = car.getDate();
            if (i == 0) {
                lastDate = currantDate;
                i++;
            }
            if (car.getDate().after(lastDate)) {
                lastDate = currantDate;
            }
        }
        return lastDate;
    }


}
