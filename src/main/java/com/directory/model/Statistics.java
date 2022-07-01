package com.directory.model;

import com.directory.service.StatisticsService;

import java.util.Date;

public class Statistics {

    private int carCount;
    private String popularBrand;
    private String popularColor;
    private Date algerDateOfEntry;
    private Date lastDateOfRefresh;

    public Statistics(StatisticsService collectStatistics) {
        carCount = collectStatistics.findCarCount();
        popularBrand = collectStatistics.findPopularBrand();
        popularColor = collectStatistics.findPopularColor();
        algerDateOfEntry = collectStatistics.findAlgerDateOfEntry();
        lastDateOfRefresh = collectStatistics.findLastDateOfRefresh();
    }

    public int getCarCount() {
        return carCount;
    }

    public Date getAlgerDateOfEntry() {
        return algerDateOfEntry;
    }

    public String getPopularColor() {
        return popularColor;
    }

    public String getPopularBrand() {
        return popularBrand;
    }

    public Date getLastDateOfRefresh() {
        return lastDateOfRefresh;
    }
}
