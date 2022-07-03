package com.directory.controller;

import com.directory.exception.BadRequestException;
import com.directory.exception.NotFoundException;
import com.directory.model.Car;
import com.directory.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.directory.util.ApplicationUtils.isEmpty;
import static com.directory.util.Constants.BAD_REQUEST_MESSAGE;
import static com.directory.util.Constants.NOT_FOUND_MESSAGE;
import static org.springframework.http.HttpStatus.*;

@RequestMapping("/cars")
@RestController
public record CarController(CarService carService) {

    // Выводит весь список машин из БД
    @GetMapping
    public ResponseEntity<List<Car>> list() {
        return new ResponseEntity<>(carService.list(), OK);
    }


    // Выводит машину с заданным id
    @GetMapping("/{id}")
    public ResponseEntity<Car> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(carService.findById(id).
                orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE, NOT_FOUND)), OK);
    }

    // Выводит машину с заданным номером
    @GetMapping("/number/{number}")
    public ResponseEntity<Car> findByNumber(@PathVariable("number") String number) {
        return new ResponseEntity<>(carService.findByNumber(number).
                orElseThrow(() -> new NotFoundException(NOT_FOUND_MESSAGE, NOT_FOUND)), OK);
    }

    // Выводит список машин отобранный по марке
    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Car>> filterByBrand(@PathVariable("brand") String brand) {
        return new ResponseEntity<>(carService.findByBrand(brand), OK);
    }

    // Выводит список машин отобранный по цвету
    @GetMapping("/color/{color}")
    public ResponseEntity<List<Car>> filterByColor(@PathVariable("color") String color) {
        return new ResponseEntity<>(carService.findByColor(color), OK);
    }

    // Добавляет запись в БД
    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        Optional<Car> optionalCar = carService.findByNumber(car.getNumber());
        if (optionalCar.isPresent())
            throw new BadRequestException(BAD_REQUEST_MESSAGE, BAD_REQUEST);
        return new ResponseEntity<>(carService.save(car), CREATED);
    }

    // Удаляет запись из БД
    @DeleteMapping("/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable("id") Long id) {
        Optional<Car> deletedCar = carService.deleteById(id);
        if (deletedCar.isPresent())
            throw new BadRequestException(BAD_REQUEST_MESSAGE, BAD_REQUEST);

        return new ResponseEntity<>(OK);
    }

    // Обовляет запись в БД. Указателем должен служить только id, серийные номера тоже можно сменить.
    // Сменить марку автомобиля нельзя. Её невозможно сменить
    // Дата смнится на дату изменения.
    @PutMapping("/{id}")
    public ResponseEntity<Car> setCar(@PathVariable("id") Long id,
                                      @RequestBody Car car) {
        return carService.findById(id).map(oldCar -> {
            oldCar.setColor(isEmpty(car.getColor()) ? oldCar.getColor() : car.getColor());
            oldCar.setNumber(isEmpty(car.getNumber()) ? oldCar.getNumber() : car.getNumber());
            oldCar.modifyTimestamp();
            return carService.save(oldCar);
        }).map(newCar -> new ResponseEntity<>(newCar, OK)).orElse(new ResponseEntity<>(car, BAD_REQUEST));
    }
}