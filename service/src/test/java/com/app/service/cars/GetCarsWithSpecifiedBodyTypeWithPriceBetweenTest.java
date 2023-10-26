package com.app.service.cars;

import com.app.persistence.model.car.Car;
import com.app.persistence.model.car.exception.CarException;
import com.app.service.config.AppTestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.app.Cars.BMW;
import static com.app.persistence.model.car.car_body.type.CarBodyType.COMBI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestConfig.class)
@TestPropertySource("classpath:app-test.properties")
class GetCarsWithSpecifiedBodyTypeWithPriceBetweenTest {
    @Autowired
    private CarsService carsService;

    static Stream<Arguments> argSource() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(1300), BigDecimal.valueOf(1000)),
                Arguments.of(BigDecimal.valueOf(4000), BigDecimal.valueOf(2000)),
                Arguments.of(BigDecimal.valueOf(2000), BigDecimal.valueOf(1900)),
                Arguments.of(BigDecimal.valueOf(3000), BigDecimal.valueOf(2500))
        );
    }

    static Stream<Arguments> argSourceNoCarsBetween() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(200), BigDecimal.valueOf(500)),
                Arguments.of(BigDecimal.valueOf(6000), BigDecimal.valueOf(8000))
        );
    }

    @ParameterizedTest
    @MethodSource("argSource")
    @DisplayName("When price range is not correct")
    void test1(BigDecimal from, BigDecimal to) {
        assertThatThrownBy(() -> carsService.getCarsWithSpecifiedBodyTypeWithPriceBetween(COMBI, from, to))
                .isInstanceOf(CarException.class)
                .hasMessage("Wrong price range");
    }

    @ParameterizedTest
    @MethodSource("argSourceNoCarsBetween")
    @DisplayName("No cars between range")
    void test2(BigDecimal from, BigDecimal to) {
        var expected = new ArrayList<Car>();
        assertThat(carsService.getCarsWithSpecifiedBodyTypeWithPriceBetween(COMBI, from, to))
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("When filtered incorrectly")
    void test3() {
        var FROM = BigDecimal.valueOf(1100);
        var TO = BigDecimal.valueOf(2100);

        var expected = List.of(BMW);
        assertThat(carsService.getCarsWithSpecifiedBodyTypeWithPriceBetween(COMBI, FROM, TO))
                .isEqualTo(expected);
    }
}
