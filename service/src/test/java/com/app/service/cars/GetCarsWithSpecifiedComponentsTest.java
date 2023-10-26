package com.app.service.cars;

import com.app.persistence.model.car.Car;
import com.app.service.config.AppTestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static com.app.Cars.AUDI;
import static com.app.Cars.HONDA;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestConfig.class)
@TestPropertySource("classpath:app-test.properties")
class GetCarsWithSpecifiedComponentsTest {
    @Autowired
    private CarsService carsService;

    static Stream<Arguments> argSource() {
        return Stream.of(
                Arguments.of(Set.of("A", "B"), new ArrayList<Car>()),
                Arguments.of(Set.of("B", "D", "C"), List.of(HONDA)),
                Arguments.of(Set.of("A"), List.of(AUDI))
        );
    }

    @ParameterizedTest
    @MethodSource("argSource")
    @DisplayName("When given specified set of components")
    void test1(Set<String> components, List<Car> expected) {
        assertThat(carsService.getCarsWithSpecifiedComponents(components))
                .isEqualTo(expected);
    }
}
