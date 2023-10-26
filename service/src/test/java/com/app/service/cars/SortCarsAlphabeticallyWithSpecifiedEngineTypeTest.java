package com.app.service.cars;

import com.app.service.config.AppTestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Stream;

import static com.app.Cars.*;
import static com.app.persistence.model.car.engine.type.EngineType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestConfig.class)
@TestPropertySource("classpath:app-test2.properties")
class SortCarsAlphabeticallyWithSpecifiedEngineTypeTest {
    @Autowired
    private CarsService carsService;

    /*public static Stream<Arguments> gasolineCars() {
        return Stream.of(
                Arguments.of(Set.of(SEAT, BMW, FORD))
        );
    }*/

    @Test
    @DisplayName("Diesel engine")
    void test1() {
        var expected = List.of(BMW, HONDA);

        assertThat(carsService.sortCarsAlphabeticallyWithSpecifiedEngineType(DIESEL))
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("Gasoline engine")
    void test2() {
        var expected = List.of(FORD, SEAT);

        assertThat(carsService.sortCarsAlphabeticallyWithSpecifiedEngineType(GASOLINE))
                .isEqualTo(expected);
    }

    @TestFactory
    @DisplayName("LPG engine")
    Stream<DynamicTest> test3() {
        var expected = List.of(AUDI);

        return Stream.of(carsService.sortCarsAlphabeticallyWithSpecifiedEngineType(LPG))
                .map(n -> dynamicTest("Is equal to expected", () -> assertThat(n).isEqualTo(expected)));
    }
}
