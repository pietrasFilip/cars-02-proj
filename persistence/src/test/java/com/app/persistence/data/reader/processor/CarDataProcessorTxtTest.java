package com.app.persistence.data.reader.processor;

import com.app.persistence.config.AppTestConfig;
import com.app.persistence.model.car.Car;
import com.app.persistence.model.car.car_body.CarBody;
import com.app.persistence.model.car.engine.Engine;
import com.app.persistence.model.car.wheel.Wheel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import static com.app.persistence.model.car.car_body.color.CarBodyColor.BLACK;
import static com.app.persistence.model.car.car_body.type.CarBodyType.HATCHBACK;
import static com.app.persistence.model.car.engine.type.EngineType.DIESEL;
import static com.app.persistence.model.car.wheel.type.TyreType.SUMMER;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestConfig.class)
@TestPropertySource("classpath:process-test.properties")
class CarDataProcessorTxtTest {
    @Autowired
    CarDataProcessor carDataTxtProcessor;

    @Test
    @DisplayName("When data source is txt")
    void test1() {
        var expected = List.of(
                Car.of(1L,"AUDI", BigDecimal.valueOf(120), 12000,
                        Engine.of(1L, DIESEL, 210.0),
                        CarBody.of(1L, BLACK, HATCHBACK, Set.of("ABS", "AIR CONDITIONING")),
                        Wheel.of(1L, "PIRELLI", 18,SUMMER)),
                Car.of(2L, "BMW", BigDecimal.valueOf(150), 10000,
                        Engine.of(1L, DIESEL, 210.0),
                        CarBody.of(1L, BLACK, HATCHBACK, Set.of("ABS", "AIR CONDITIONING")),
                        Wheel.of(2L, "PIRELLI", 19,SUMMER)));

        var path = Paths
                .get("src", "test", "data", "test-cars.txt")
                .toFile()
                .getAbsolutePath();

        assertThat(carDataTxtProcessor.process(path))
                .hasSize(2)
                .isInstanceOf(List.class)
                .isEqualTo(expected);
    }
}
