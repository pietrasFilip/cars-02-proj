package com.app.persistence.model.car.car_body;

import com.app.persistence.model.car.car_body.type.CarBodyType;

import java.util.Set;
import java.util.function.Function;

public interface CarBodyFieldsConverter {
    Function<CarBody, CarBodyType> toCarBodyType = carBody -> carBody.type;
    Function<CarBody, Set<String>> toComponents = carBody -> carBody.components;
}
