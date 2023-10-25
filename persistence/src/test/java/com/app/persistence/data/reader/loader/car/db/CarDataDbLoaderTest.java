package com.app.persistence.data.reader.loader.car.db;

import com.app.persistence.data.reader.model.CarBodyData;
import com.app.persistence.data.reader.model.CarData;
import com.app.persistence.data.reader.model.EngineData;
import com.app.persistence.data.reader.model.WheelData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarDataDbLoaderTest {
    @Mock
    CarDataDbLoaderImpl carDataDbLoader;

    @TestFactory
    @DisplayName("When there is data to load from db")
    Stream<DynamicNode> test() {
        when(carDataDbLoader.load(""))
                .thenAnswer(invocationOnMock ->
                        List.of(
                                CarData.of(1L,"AUDI", BigDecimal.valueOf(120), 12000,
                                        EngineData.of(1L,"DIESEL", 210.0),
                                        CarBodyData.of(1L,"BLACK", "HATCHBACK", Set.of("ABS", "AIR CONDITIONING")),
                                        WheelData.of(1L,"PIRELLI", 18,"SUMMER")),
                                CarData.of(2L, "BMW", BigDecimal.valueOf(150), 10000,
                                        EngineData.of(1L, "DIESEL", 210.0),
                                        CarBodyData.of(1L, "BLACK", "HATCHBACK", Set.of("ABS", "AIR CONDITIONING")),
                                        WheelData.of(2L, "PIRELLI", 19, "SUMMER")))
                );

        return Stream.of(carDataDbLoader.load(""))
                .map(n -> dynamicContainer(
                        "Container" + n, Stream.of(
                                dynamicTest("Is instance of List",
                                        () -> assertThat(n).isInstanceOf(List.class)),
                                dynamicTest("Is instance of CarData",
                                        () -> n.forEach(car -> assertThat(car).isInstanceOf(CarData.class))),
                                dynamicTest("Has Exactly size of 2",
                                        () -> assertThat(n).hasSize(2))
                        )
                ));
    }
}
