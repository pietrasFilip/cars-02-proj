package com.app.persistence.data.reader.loader.car.txt;

import com.app.persistence.data.reader.model.CarData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;

import java.io.File;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class CarDataTxtLoaderTest {

    @TestFactory
    @DisplayName("When there is data to load from .txt file.")
    Stream<DynamicNode> test1() {
        var carsPath = new File("src/test/data/test-cars.txt").getAbsoluteFile().getPath();
        var jsonLoader = new CarDataTxtLoaderImpl();

        return Stream.of(jsonLoader.load(carsPath))
                .map(n -> dynamicContainer(
                        "Container" + n, Stream.of(
                                dynamicTest("Is instance of List",
                                        () -> assertThat(n).isInstanceOf(List.class)),
                                dynamicTest("Is instance of CarData",
                                        () -> n.forEach(o -> assertThat(o).isInstanceOf(CarData.class))),
                                dynamicTest("Has exactly size of 2",
                                        () -> assertThat(n).hasSize(2))
                        )
                ));
    }
}
