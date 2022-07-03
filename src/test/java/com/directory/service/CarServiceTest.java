//package com.directory.service;
//
//import com.directory.model.Car;
//import com.directory.repository.CarRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//class CarServiceTest {
//
//    @Autowired
//    private CarService underTestCarService;
//
//    @Autowired
//    private CarRepository carRepository;
//
//    MockMvc mockMvc;
//
//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(underTestCarService).build();
//    }
//
//    @Test
//    void list() {
//
//        // given
//        Iterable<Car> testCars = underTestCarService.list();
//
//        // when
//        Iterable<Car> newTestCars = underTestCarService.list();
//        boolean isFind = testCars.equals(newTestCars);
//
//        // then
//        assertThat(isFind).isTrue();
//    }
//
//    @Test
//    void wrongList() {
//
//        // given
//        Iterable<Car> testCars = underTestCarService.list();
//        Car carTest = new Car("TestNumber", "TestBrand", "TestColor");
//        carRepository.save(carTest);
//
//
//        // when
//        Iterable<Car> newTestCars = underTestCarService.list();
//        boolean isFind = testCars.equals(newTestCars);
//        carRepository.delete(carTest);
//
//        // then
//        assertThat(isFind).isFalse();
//    }
//
//
//    @Test
//    void findById() {
//        // given
//        Car carTest = new Car("TestNumber", "TestBrand", "TestColor");
//        carRepository.save(carTest);
//
//        // when
//        boolean isFind = underTestCarService.findById(carTest.getId()).equals(carTest);
//        carRepository.delete(carTest);
//
//        // then
//        assertThat(isFind).isTrue();
//    }
//
//    @Test
//    void notFindById() {
//        // given
//        Car carTest = new Car("TestNumber", "TestBrand", "TestColor");
//        carRepository.save(carTest);
//        Car otherCarTest = new Car("NewTestNumber", "NewTestBrand", "TestColor");
//
//        // when
//        boolean isFind = otherCarTest.equals(underTestCarService.findById(carTest.getId()));
//        carRepository.delete(carTest);
//
//        // then
//        assertThat(isFind).isFalse();
//    }
//
//    @Test
//    void findByNumber() {
//        // given
//        Car carTest = new Car("TestNumber", "TestBrand", "TestColor");
//        carRepository.save(carTest);
//
//        // when
//        boolean isFind = underTestCarService.findByNumber(carTest.getNumber()).equals(carTest);
//        carRepository.delete(carTest);
//
//        // then
//        assertThat(isFind).isTrue();
//    }
//
//    @Test
//    void notFindByNumber() {
//        // given
//        Car carTest = new Car("TestNumber", "TestBrand", "TestColor");
//        carRepository.save(carTest);
//        Car otherCarTest = new Car("NewTestNumber", "NewTestBrand", "TestColor");
//
//        // when
//        boolean isFind = otherCarTest.equals(underTestCarService.findByNumber(carTest.getNumber()));
//        carRepository.delete(carTest);
//
//        // then
//        assertThat(isFind);
//    }
//
//    @Test
//    void filterByBrand() {
//        // given
//        Car carTest = new Car("TestNumber", "TestBrand", "TestColor");
//        carRepository.save(carTest);
//
//        // when
//        boolean isFind = underTestCarService.findByBrand("TestBrand").contains(carTest);
//        carRepository.delete(carTest);
//
//        // then
//        assertThat(isFind).isTrue();
//    }
//
//    @Test
//    void filterByBrandNotFound() {
//        // given
//        Car carTest = new Car("TestNumber", "TestBrand", "TestColor");
//
//        // when
//        boolean isFind = underTestCarService.findByBrand("TestBrand").contains(carTest);
//
//        // then
//        assertThat(isFind).isFalse();
//    }
//
//    @Test
//    void filterByColor() {
//        // given
//        Car carTest = new Car("TestNumber", "TestBrand", "TestColor");
//        carRepository.save(carTest);
//
//        // when
//        boolean isFind = underTestCarService.findByColor("TestColor").contains(carTest);
//        carRepository.delete(carTest);
//
//        // then
//        assertThat(isFind).isTrue();
//    }
//
//    @Test
//    void filterByColorNotFound() {
//        // given
//        Car carTest = new Car("TestNumber", "TestBrand", "TestColor");
//
//        // when
//        boolean isFind = underTestCarService.findByColor("TestColor").contains(carTest);
//
//        // then
//        assertThat(isFind).isFalse();
//    }
//
//    @Test
//    void addCar() {
//        // given
//        Car carTest = new Car("TestNumber", "TestBrand", "TestColor");
//        underTestCarService.save(carTest);
//
//        // when
//        boolean isFind = carRepository.findByNumber("TestNumber").isPresent();
//        carRepository.delete(carTest);
//
//        // then
//        assertThat(isFind).isTrue();
//    }
//
//    @Test
//    void setCar() {
//        // given
//        Car carTest = new Car("TestNumber", "TestBrand", "TestColor");
//        underTestCarService.save(carTest);
//        underTestCarService.setCar(carTest.getId(), "0", "NewColor");
//
//        // when
//        boolean isFind = carRepository.findById(carTest.getId()).get().getColor().equals("TestColor");
//        carRepository.delete(carTest);
//
//        // then
//        assertThat(isFind).isFalse();
//    }
//
//    @Test
//    void notSetCar() {
//        // given
//        Car carTest = new Car("TestNumber", "TestBrand", "TestColor");
//        underTestCarService.save(carTest);
//        underTestCarService.setCar(carTest.getId(), "0", "NewColor");
//
//        // when
//        boolean isFind = carRepository.findById(carTest.getId()).get().getNumber().equals("TestNumber");
//        carRepository.delete(carTest);
//
//        // then
//        assertThat(isFind).isTrue();
//    }
//
//    @Test
//    void delete() {
//        // given
//        Car carTest = new Car("TestNumber", "UniqueTestBrand", "TestColor");
//        carRepository.save(carTest);
//
//        // when
//        underTestCarService.deleteById(carTest.getId());
//        boolean isFind = carRepository.findByBrand("UniqueBrand").contains(carTest);
//
//        // then
//        assertThat(isFind).isFalse();
//    }
//}