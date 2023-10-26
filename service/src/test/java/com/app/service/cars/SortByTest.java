package com.app.service.cars;

import com.app.service.config.AppTestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.app.Cars.*;
import static com.app.persistence.model.car.CarComparator.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestConfig.class)
@TestPropertySource("classpath:app-test.properties")
class SortByTest {
    @Autowired
    private CarsService carsService;

    @Test
    @DisplayName("Ascending by number of components")
    void test1() {
        var expected = List.of(AUDI, BMW, HONDA);

        assertThat(carsService.sortBy(byCarBodyComponents))
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("Descending by number of components")
    void test2() {
        var expected = List.of(HONDA, BMW, AUDI);

        assertThat(carsService.sortBy(byCarBodyComponents.reversed()))
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("Ascending by engine power")
    void test3() {
        var expected = List.of(HONDA, BMW, AUDI);

        assertThat(carsService.sortBy(byEnginePower))
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("Descending by engine power")
    void test4() {
        var expected = List.of(AUDI, BMW, HONDA);

        assertThat(carsService.sortBy(byEnginePower.reversed()))
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("Ascending by tire size")
    void test5() {
        var expected = List.of(AUDI, HONDA, BMW);

        assertThat(carsService.sortBy(byTireSize))
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("Descending by tire size")
    void test6() {
        var expected = List.of(BMW, HONDA, AUDI);

        assertThat(carsService.sortBy(byTireSize.reversed()))
                .isEqualTo(expected);
    }
}
