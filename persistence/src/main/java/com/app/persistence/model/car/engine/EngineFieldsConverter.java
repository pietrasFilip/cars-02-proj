package com.app.persistence.model.car.engine;

import com.app.persistence.model.car.engine.type.EngineType;

import java.util.function.Function;
import java.util.function.ToDoubleFunction;

public interface EngineFieldsConverter {
    Function<Engine, EngineType> toEngineType = engine -> engine.engineType;
    ToDoubleFunction<Engine> toPower = engine -> engine.power;
}
