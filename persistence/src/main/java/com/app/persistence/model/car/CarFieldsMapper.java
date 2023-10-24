package com.app.persistence.model.car;

import com.app.persistence.model.car.wheel.type.TyreType;

import java.math.BigDecimal;
import java.util.function.Function;

import static com.app.persistence.model.car.engine.EngineFieldsConverter.toPower;
import static com.app.persistence.model.car.wheel.WheelFieldsConverter.toTyreType;

public interface CarFieldsMapper {
    Function<Car, BigDecimal> convertToPrice = car -> car.price;
    Function<Car, Integer> convertToMileage = car -> car.mileage;
    Function<Car, Double> convertToEnginePower = car -> toPower.applyAsDouble(car.engine);
    Function<Car, TyreType> convertToTyreType = car -> toTyreType.apply(car.wheel);
    Function<Car, String> convertToModel = car -> car.model;
}
