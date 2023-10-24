package com.app.persistence.model.car.wheel;

import com.app.persistence.model.car.wheel.type.TyreType;

import java.util.function.Function;

public interface WheelFieldsConverter {
    Function<Wheel, TyreType> toTyreType = wheel -> wheel.tyreType;
    Function<Wheel, Integer> toTyreSize = wheel -> wheel.size;
}
