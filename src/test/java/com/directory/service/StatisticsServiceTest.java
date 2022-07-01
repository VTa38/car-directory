package com.directory.service;

import com.directory.model.Car;
import com.directory.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StatisticsServiceTest {

    @Autowired
    private StatisticsService underTestStatisticsService;

    @Autowired
    private CarRepository carRepository;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(underTestStatisticsService).build();
    }

    @Test
    void findCarCount() {
        // given
        int testCarCount = underTestStatisticsService.findCarCount();
        Car carTest = new Car("TestNumber", "TestBrand", "TestColor");
        carRepository.save(carTest);

        // when
        int newTestCarCount = underTestStatisticsService.findCarCount();
        boolean isFind = testCarCount + 1 == newTestCarCount;
        carRepository.delete(carTest);

        // then
        assertThat(isFind).isTrue();
    }

    @Test
    void notFindWrongCarCount() {
        // given
        int testCarCount = underTestStatisticsService.findCarCount();

        // when
        int newTestCarCount = underTestStatisticsService.findCarCount();
        boolean isFind = testCarCount == newTestCarCount + 1;

        // then
        assertThat(isFind).isFalse();
    }

    @Test
    void findAlgerDateOfEntry() {
        // given
        Date testCarDate = underTestStatisticsService.findAlgerDateOfEntry();
        Car carTest = new Car("TestNumber", "TestBrand", "TestColor");
        carRepository.save(carTest);

        // when
        Date newTestCarDate = carTest.getDate();
        boolean isFind = testCarDate.after(newTestCarDate);
        carRepository.delete(carTest);

        // then
        assertThat(isFind).isFalse();
    }

    @Test
    void findPopularColor() {
        // given
        String testColor = underTestStatisticsService.findPopularColor();

        // when
        boolean isFind = testColor.equals("notPopularColor");

        // then
        assertThat(isFind).isFalse();
    }

    @Test
    void findAllColor() {
        // given
        Car carTest = new Car("TestNumber", "TestBrand", "TestColor");
        carRepository.save(carTest);
        Set<String> testColors = underTestStatisticsService.findAllColor();

        // when
        boolean isFind = testColors.contains("TestColor");
        carRepository.delete(carTest);

        // then
        assertThat(isFind).isTrue();
    }

    @Test
    void notFindColorInAllColorSet() {
        // given
        Set<String> testColors = underTestStatisticsService.findAllColor();

        // when
        boolean isFind = testColors.contains("TestColor");

        // then
        assertThat(isFind).isFalse();
    }

    @Test
    void findPopularBrand() {
        // given
        String testBrand = underTestStatisticsService.findPopularBrand();

        // when
        boolean isFind = testBrand.equals("notPopularBrand");

        // then
        assertThat(isFind).isFalse();
    }

    @Test
    void findAllBrant() {
        // given
        Car carTest = new Car("TestNumber", "TestBrand", "TestColor");
        carRepository.save(carTest);
        Set<String> testBrand = underTestStatisticsService.findAllBrand();

        // when
        boolean isFind = testBrand.contains(carTest.getBrand());
        carRepository.delete(carTest);

        // then
        assertThat(isFind).isTrue();
    }

    @Test
    void notFindBrandInAllBrantSet() {
        // given
        Set<String> testBrand = underTestStatisticsService.findAllBrand();

        // when
        boolean isFind = testBrand.contains("TestBrand");

        // then
        assertThat(isFind).isFalse();
    }

    @Test
    void findLastDateOfRefresh() {
        // given
        Car carTest = new Car("TestNumber", "TestBrand", "TestColor");
        carRepository.save(carTest);
        Date testCarDate = underTestStatisticsService.findLastDateOfRefresh();

        // when
        Date oldTestCarDate = underTestStatisticsService.findAlgerDateOfEntry();
        boolean isFind = testCarDate.equals(oldTestCarDate);
        carRepository.delete(carTest);

        // then
        assertThat(isFind).isFalse();
    }

    @Test
    void notFindWrongLastDateOfRefresh() {
        // given
        Date testCarDate = underTestStatisticsService.findAlgerDateOfEntry();
        Car carTest = new Car("TestNumber", "TestBrand", "TestColor");
        carRepository.save(carTest);

        // when
        Date newTestCarDate = underTestStatisticsService.findLastDateOfRefresh();
        boolean isFind = testCarDate.after(newTestCarDate);
        carRepository.delete(carTest);

        // then
        assertThat(isFind).isFalse();
    }
}