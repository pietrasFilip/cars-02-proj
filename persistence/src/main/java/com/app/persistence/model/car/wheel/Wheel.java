package com.app.persistence.model.car.wheel;

import com.app.persistence.model.car.wheel.type.TyreType;

import java.util.Objects;

public class Wheel {
    final Long id;
    final String model;
    final int size;
    final TyreType tyreType;

    private Wheel(Long id, String model, int size, TyreType tyreType) {
        this.id = id;
        this.model = model;
        this.size = size;
        this.tyreType = tyreType;
    }

    // Methods from Object class

    @Override
    public String toString() {
        return "Model - %s, Size - %s, TyreType - %s".formatted(model, size, tyreType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wheel wheel)) return false;
        return size == wheel.size && Objects.equals(model, wheel.model) && tyreType == wheel.tyreType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, size, tyreType);
    }

    // Static methods

    public static Wheel of(Long id, String model, int size, TyreType tyreType) {
        return new Wheel(id, model, size, tyreType);
    }
}
