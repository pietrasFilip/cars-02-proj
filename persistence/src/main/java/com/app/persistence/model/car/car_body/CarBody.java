package com.app.persistence.model.car.car_body;

import com.app.persistence.model.car.car_body.color.CarBodyColor;
import com.app.persistence.model.car.car_body.type.CarBodyType;

import java.util.Objects;
import java.util.Set;

public class CarBody {
    final Long id;
    final CarBodyColor color;
    final CarBodyType type;
    final Set<String> components;

    private CarBody(Long id, CarBodyColor color, CarBodyType type, Set<String> components) {
        this.id = id;
        this.color = color;
        this.type = type;
        this.components = components;
    }

    // Methods from Object class

    @Override
    public String toString() {
        return "Car body: Color %s, Type %s, Components %s".formatted(color, type, components);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarBody carBody)) return false;
        return color == carBody.color && type == carBody.type && Objects.equals(components, carBody.components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type, components);
    }

    // Static methods

    public static CarBody of(Long id, CarBodyColor color, CarBodyType type, Set<String> components) {
        return new CarBody(id, color, type, components);
    }
}
