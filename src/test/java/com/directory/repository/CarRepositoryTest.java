package com.directory.repository;


import com.directory.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CarRepositoryTest {

    @Autowired
    private CarRepository underTestCarRepository;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(underTestCarRepository).build();
    }

    @Test
    void delete() {
        // given
        Car carTest = new Car("TestNumber", "TestBrand", "TestColor");
        underTestCarRepository.save(carTest);

        // when
        underTestCarRepository.delete(carTest);
        boolean isFind = underTestCarRepository.findById(carTest.getId()).isPresent();

        // then
        assertThat(isFind).isFalse();
    }

    @Test
    void findById() {
        // given
        Car carTest = new Car("TestNumber", "TestBrand", "TestColor");
        underTestCarRepository.save(carTest);

        // when
        boolean isFind = underTestCarRepository.findById(carTest.getId()).isPresent();

        // then
        assertThat(isFind).isTrue();
        underTestCarRepository.delete(carTest);
    }

    @Test
    void findByNotExistId() {
        // given
        Car carTest = new Car("TestNumber", "TestBrand", "TestColor");
        underTestCarRepository.save(carTest);

        // when
        boolean isFind = underTestCarRepository.findById(9000L).isPresent();

        // then
        assertThat(isFind).isFalse();
        underTestCarRepository.delete(carTest);
    }

    @Test
    void findByNumber() {
        // given
        Car carTest = new Car("TestNumber", "TestBrand", "TestColor");
        underTestCarRepository.save(carTest);

        // when
        boolean isFind = underTestCarRepository.findByNumber("TestNumber").isPresent();

        // then
        assertThat(isFind).isTrue();
        underTestCarRepository.delete(carTest);
    }

    @Test
    void findByNotExistNumber() {
        // given
        Car carTest = new Car("TestNumber", "TestBrand", "TestColor");
        underTestCarRepository.save(carTest);

        // when
        boolean isFind = underTestCarRepository.findByNumber("NotExistNumber").isPresent();

        // then
        assertThat(isFind).isFalse();
        underTestCarRepository.delete(carTest);
    }

    @Test
    void findByBrand() {
        // given
        Car carTest = new Car("TestNumber", "TestBrand", "TestColor");
        underTestCarRepository.save(carTest);

        // when
        boolean isFind = underTestCarRepository.findByBrand("TestBrand").contains(carTest);

        // then
        assertThat(isFind).isTrue();
        underTestCarRepository.delete(carTest);
    }

    @Test
    void findByNotExistBrand() {
        // given
        Car carTest = new Car("TestNumber", "TestBrand", "TestColor");
        underTestCarRepository.save(carTest);

        // when
        boolean isFind = underTestCarRepository.findByBrand("TestNotExistBrand").contains(carTest);

        // then
        assertThat(isFind).isFalse();
        underTestCarRepository.delete(carTest);
    }

    @Test
    void findByColor() {
        // given
        Car carTest = new Car("TestNumber", "TestBrand", "TestColor");
        underTestCarRepository.save(carTest);

        // when
        boolean isFind = underTestCarRepository.findByColor("TestColor").contains(carTest);

        // then
        assertThat(isFind).isTrue();
        underTestCarRepository.delete(carTest);
    }

    @Test
    void findByNotExistColor() {
        // given
        Car carTest = new Car("TestNumber", "TestBrand", "TestColor");
        underTestCarRepository.save(carTest);

        // when
        boolean isFind = underTestCarRepository.findByColor("TestNotExistColor").contains(carTest);

        // then
        assertThat(isFind).isFalse();
        underTestCarRepository.delete(carTest);
    }
}