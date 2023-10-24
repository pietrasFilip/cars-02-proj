package com.app.persistence.model.car;

import java.util.Comparator;

import static com.app.persistence.model.car.car_body.CarBodyFieldsConverter.toComponents;
import static com.app.persistence.model.car.engine.EngineFieldsConverter.toPower;
import static com.app.persistence.model.car.wheel.WheelFieldsConverter.toTyreSize;

public interface CarComparator {
    Comparator<Car> components = Comparator.comparing(car -> toComponents.apply(car.carBody).size());
    Comparator<Car> byCarBodyComponents = components.thenComparing(car -> car.model);
    Comparator<Car> byEnginePower = Comparator.comparing(car -> toPower.applyAsDouble(car.engine));
    Comparator<Car> byTireSize = Comparator.comparing(car -> toTyreSize.apply(car.wheel));
    Comparator<Car> byModel = Comparator.comparing(car -> car.model);
}
