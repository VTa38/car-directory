package com.directory.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

@Entity
public class Car {
    private static final Calendar calendar = new GregorianCalendar();
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String number;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String color;

    private Date timestamp;

    public Date getTimestamp() {
        if (timestamp == null)
            modifyTimestamp();
        return timestamp;
    }

    public Car() {
        timestamp = calendar.getTime();
    }

    public Car(String number, String brand, String color) {
        this.number = number;
        this.brand = brand;
        this.color = color;
        this.timestamp = calendar.getTime();
    }

    // Обновляет дату. Явно задать дату нельзя, только обновить при необходимости
    public void modifyTimestamp() {
        this.timestamp = calendar.getTime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id.equals(car.id) && number.equals(car.number) && brand.equals(car.brand) && color.equals(car.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, brand, color);
    }
}