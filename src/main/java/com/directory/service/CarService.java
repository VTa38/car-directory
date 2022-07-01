package com.directory.service;

import com.directory.exception.NotFoundException;
import com.directory.exception.RequestException;
import com.directory.model.Car;
import com.directory.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CarService {

    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Iterable<Car> list() {
        Iterable<Car> cars = carRepository.findAll();

        return cars;
    }

    public Car findById(Long id) {
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isEmpty())
            throw new NotFoundException("Данной машины нет в справочнике");
        return carOptional.get();
    }

    public Car findByNumber(String number) {
        Optional<Car> carOptional = carRepository.findByNumber(number);
        if (carOptional.isEmpty())
            throw new NotFoundException("Машины с номером " + number + " нет в справочнике");

        return carOptional.get();
    }


    public List<Car> filterByBrand(String brand) {
        List<Car> carsByBrand = carRepository.findByBrand(brand);

        return carsByBrand;
    }

    public List<Car> filterByColor(String color) {
        List<Car> carsByColor = carRepository.findByColor(color);

        return carsByColor;
    }

    public Car addCar(Car car) {

        if (carRepository.findByNumber(car.getNumber()).isPresent())
            throw new RequestException("Машина с таким номером уже есть в справочнике");

        carRepository.save(car);
        return car;
    }

    public Car deleteCar(Long id) {

        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isEmpty())
            throw new NotFoundException();

        carRepository.deleteById(id);
        return carOptional.get();
    }

    public Car setCar(Long id, String number, String color) {

        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isEmpty())
            throw new NotFoundException("Данной машины нет в справочнике");

        Car car = carOptional.get();
        if (!number.equals("0")) {
            if (carRepository.findByNumber(number).isPresent())
                throw new RequestException("Машина с такими номерами уже есть в справочнике");

            car.setNumber(number);
        }
        if (!color.equals("0"))
            car.setColor(color);

        car.setDate();
        carRepository.save(car);
        return car;
    }
}
