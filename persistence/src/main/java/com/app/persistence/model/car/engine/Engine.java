package com.app.persistence.model.car.engine;

import com.app.persistence.model.car.engine.type.EngineType;

import java.util.Objects;

public class Engine {
    final Long id;
    final EngineType engineType;
    final double power;

    private Engine(Long id, EngineType engineType, double power) {
        this.id = id;
        this.engineType = engineType;
        this.power = power;
    }

    // Methods from Object class

    @Override
    public String toString() {
        return "Engine %s, power = %s".formatted(engineType, power);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Engine engine)) return false;
        return Double.compare(engine.power, power) == 0 && engineType == engine.engineType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(engineType, power);
    }

    // Static methods

    public static Engine of(Long id, EngineType engineType, double power) {
        return new Engine(id, engineType, power);
    }
}
