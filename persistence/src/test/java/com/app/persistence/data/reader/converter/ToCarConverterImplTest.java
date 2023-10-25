package com.app.persistence.data.reader.converter;

import com.app.persistence.data.reader.loader.car.json.CarDataJsonLoaderImpl;
import com.app.persistence.data.reader.loader.car.txt.CarDataTxtLoaderImpl;
import com.app.persistence.model.car.Car;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;

import java.io.File;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class ToCarConverterImplTest {

    @TestFactory
    @DisplayName("When there is from CarData to Car in .json format")
    Stream<DynamicNode> test1() {
        var carsPath = new File("src/test/data/test-cars.json").getAbsoluteFile().getPath();
        var jsonLoader = new CarDataJsonLoaderImpl(new GsonBuilder().setPrettyPrinting().create());
        var data = jsonLoader.load(carsPath);

        var converter = new ToCarConverterImpl();
        return Stream.of(converter.convert(data))
                .map(n -> dynamicContainer(
                        "Container" + n, Stream.of(
                                dynamicTest("Is instance of Car",
                                        () -> n.forEach(car -> assertThat(car).isInstanceOf(Car.class))),
                        dynamicTest("Has size of 2",
                                ()-> assertThat(n).hasSize(2))
                        )
                ));
    }

    @TestFactory
    @DisplayName("When there is from CarData to Car in .txt format")
    Stream<DynamicNode> test2() {
        var carsPath = new File("src/test/data/test-cars.txt").getAbsoluteFile().getPath();
        var txtLoader = new CarDataTxtLoaderImpl();
        var data = txtLoader.load(carsPath);

        var converter = new ToCarConverterImpl();
        return Stream.of(converter.convert(data))
                .map(n -> dynamicContainer(
                        "Container" + n, Stream.of(
                                dynamicTest("Is instance of Car",
                                        () -> n.forEach(car -> assertThat(car).isInstanceOf(Car.class))),
                                dynamicTest("Has size of 2",
                                        ()-> assertThat(n).hasSize(2))
                        )
                ));
    }
}
