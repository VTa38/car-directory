package com.directory.controller;

import com.directory.model.Car;
import com.directory.model.Statistics;
import com.directory.service.CarService;
import com.directory.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequestMapping("/cars")
@Controller
public class CarController {

    private CarService carService;
    private StatisticsService service;

    @Autowired
    public CarController(CarService carService, StatisticsService service) {
        this.carService = carService;
        this.service = service;
    }

    public CarController() {
    }

    // Выводит весь список машин из БД
    @GetMapping
    public ResponseEntity<Iterable<Car>> list() {

        return new ResponseEntity<>(carService.list(), HttpStatus.OK);
    }


    // Выводит машину с заданным id
    @GetMapping("/id")
    public ResponseEntity<Car> findById(@RequestParam("id") Long id) {

        return new ResponseEntity<>(carService.findById(id), HttpStatus.OK);
    }

    // Выводит машину с заданным номером
    @GetMapping("/number")
    public ResponseEntity<Car> findByNumber(@RequestParam("number") String number) {

        return new ResponseEntity<>(carService.findByNumber(number), HttpStatus.OK);
    }

    // Выводит список машин отобранный по марке
    @GetMapping("/filters/brand")
    public ResponseEntity<List<Car>> filterByBrand(@RequestParam("brand") String brand) {

        return new ResponseEntity<>(carService.filterByBrand(brand), HttpStatus.OK);
    }

    // Выводит список машин отобранный по цвету
    @GetMapping("/filters/color")
    public ResponseEntity<List<Car>> filterByColor(@RequestParam("color") String color) {

        return new ResponseEntity<>(carService.filterByColor(color), HttpStatus.OK);
    }

    // Добавляет запись в БД
    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car) {

        return new ResponseEntity<>(carService.addCar(car), HttpStatus.CREATED);
    }

    // Удаляет запись из БД
    @PostMapping("/delete")
    public ResponseEntity<Car> deleteCar(@RequestParam Long id) {

        return new ResponseEntity<>(carService.deleteCar(id), HttpStatus.OK);
    }

    // Обовляет запись в БД. Указателем должен служить только id, серийные номера тоже можно сменить.
    // Сменить марку автомобиля нельзя. Её невозможно сменить
    // Дата смнится на дату изменения.
    @PutMapping("/update")
    public ResponseEntity<Car> setCar(@RequestParam Long id,
                                      @RequestParam(name = "number", required = false, defaultValue = "0") String number,
                                      @RequestParam(name = "color", required = false, defaultValue = "0") String color) {

        return new ResponseEntity<>(carService.setCar(id, number, color), HttpStatus.OK);
    }

    // Выводит основную статистику справочника
    @GetMapping("/statistics")
    public ResponseEntity<Statistics> statistics() {
        Statistics statistics = new Statistics(service);

        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }

    // Выводит все уникальные марки автомобилей, которые есть в справочнике
    @GetMapping("/statistics/brand")
    public ResponseEntity<Set<String>> getAllBrand() {

        return new ResponseEntity<>(service.findAllBrand(), HttpStatus.OK);
    }

    // Выводит все уникальные цвета автомобилей, которые есть в справочнике
    @GetMapping("/statistics/color")
    public ResponseEntity<Set<String>> getAllColor() {

        return new ResponseEntity<>(service.findAllColor(), HttpStatus.OK);
    }
}