package com.app.service.cars;

import com.app.service.car_statiscics.BigDecimalStatistics;
import com.app.service.car_statiscics.CarStatisticsItem;
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

import java.math.BigDecimal;
import java.util.stream.Stream;

import static com.app.service.car_statiscics.CarStatisticsItem.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestConfig.class)
@TestPropertySource("classpath:app-test.properties")
public class GetCarNumberStatisticsTest {
    @Autowired
    private CarsService carsService;

    public static Stream<Arguments> argSource() {
        return Stream.of(
                Arguments.of(PRICE,
                        new BigDecimalStatistics(BigDecimal.valueOf(1000),
                        BigDecimal.valueOf(1400), BigDecimal.valueOf(2000),
                        BigDecimal.valueOf(4200), 3)),
                Arguments.of(MILEAGE,
                        new BigDecimalStatistics(BigDecimal.valueOf(250),
                        BigDecimal.valueOf(650), BigDecimal.valueOf(900),
                        BigDecimal.valueOf(1950), 3)),
                Arguments.of(ENGINE_POWER,
                        new BigDecimalStatistics(
                        new BigDecimal("110.400000000000005684341886080801486968994140625"),
                        new BigDecimal("139.599999999999994315658113919198513031005859375"),
                        new BigDecimal("158.19999999999998863131622783839702606201171875"),
                        new BigDecimal("418.799999999999982946974341757595539093017578125"), 3))
        );
    }

    @ParameterizedTest
    @MethodSource("argSource")
    @DisplayName("When cars statistics is different than expected")
    void test1(CarStatisticsItem statisticsItem, BigDecimalStatistics expected) {
        assertThat(carsService.getCarNumberStatistics(statisticsItem))
                .isEqualTo(expected);
    }
}
