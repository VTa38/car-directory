package com.directory.model;

import java.util.Date;

public record Statistics(int carCount,
                         String popularBrand,
                         String popularColor,
                         Date firsUpdateDate,
                         Date lastUpdateDate) {

    public int getCarCount() {
        return carCount;
    }

    public Date getFirsUpdateDate() {
        return firsUpdateDate;
    }

    public String getPopularColor() {
        return popularColor;
    }

    public String getPopularBrand() {
        return popularBrand;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }
}
