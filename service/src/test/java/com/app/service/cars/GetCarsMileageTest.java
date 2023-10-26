package com.app.service.cars;

import com.app.persistence.model.car.Car;
import com.app.service.config.AppTestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.LinkedHashMap;
import java.util.stream.Stream;

import static com.app.Cars.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestConfig.class)
@TestPropertySource("classpath:app-test.properties")
class GetCarsMileageTest {
    @Autowired
    private CarsService carsService;

    @TestFactory
    @DisplayName("When cars mileages are not sorted descending")
    Stream<DynamicTest> test1() {
        var expected = new LinkedHashMap<Car, Integer>();
        expected.put(AUDI, 900);
        expected.put(HONDA, 800);
        expected.put(BMW, 250);

        return Stream.of(carsService.getCarsMileage())
                .map(n -> dynamicTest(
                        "Is equal to expected", () -> assertThat(n).containsExactlyEntriesOf(expected)
                ));
    }
}
