package com.directory.controller;

import com.directory.model.Statistics;
import com.directory.service.StatisticsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RequestMapping("/statistics")
@RestController
public record StatisticsController(StatisticsService statisticsService) {

    // Выводит основную статистику справочника
    @GetMapping
    public ResponseEntity<Statistics> statistics() {
        return new ResponseEntity<>(statisticsService.getStatistics(), HttpStatus.OK);
    }

    // Выводит все уникальные марки автомобилей, которые есть в справочнике
    @GetMapping("/brands")
    public ResponseEntity<Set<String>> getAllBrand() {
        return new ResponseEntity<>(statisticsService.getAllBrand(), HttpStatus.OK);
    }

    // Выводит все уникальные цвета автомобилей, которые есть в справочнике
    @GetMapping("/colors")
    public ResponseEntity<Set<String>> getAllColor() {
        return new ResponseEntity<>(statisticsService.getAllColor(), HttpStatus.OK);
    }
}
