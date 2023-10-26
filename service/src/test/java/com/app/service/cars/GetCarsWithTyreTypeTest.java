package com.app.service.cars;

import com.app.persistence.model.car.Car;
import com.app.persistence.model.car.wheel.type.TyreType;
import com.app.service.config.AppTestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.LinkedHashMap;
import java.util.List;

import static com.app.Cars.*;
import static com.app.persistence.model.car.wheel.type.TyreType.SUMMER;
import static com.app.persistence.model.car.wheel.type.TyreType.WINTER;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestConfig.class)
@TestPropertySource("classpath:app-test.properties")
class GetCarsWithTyreTypeTest {
    @Autowired
    private CarsService carsService;

    @Test
    @DisplayName("When grouped by tyre type and sorted by number of elements in collection")
    void test1() {
        var expected = new LinkedHashMap<TyreType, List<Car>>();
        expected.put(SUMMER, List.of(BMW, HONDA));
        expected.put(WINTER, List.of(AUDI));

        assertThat(carsService.getCarsWithTyreType())
                .containsExactlyEntriesOf(expected);
    }
}