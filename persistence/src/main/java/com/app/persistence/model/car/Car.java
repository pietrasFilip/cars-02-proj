package com.app.persistence.model.car;

import com.app.persistence.model.car.car_body.CarBody;
import com.app.persistence.model.car.car_body.type.CarBodyType;
import com.app.persistence.model.car.engine.Engine;
import com.app.persistence.model.car.engine.type.EngineType;
import com.app.persistence.model.car.wheel.Wheel;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

import static com.app.persistence.model.car.car_body.CarBodyFieldsConverter.toCarBodyType;
import static com.app.persistence.model.car.car_body.CarBodyFieldsConverter.toComponents;
import static com.app.persistence.model.car.engine.EngineFieldsConverter.toEngineType;

public class Car {
    final Long id;
    final String model;
    final BigDecimal price;
    final int mileage;
    final Engine engine;
    final CarBody carBody;
    final Wheel wheel;

    private Car(Long id, String model, BigDecimal price, int mileage, Engine engine, CarBody carBody, Wheel wheel) {
        this.id = id;
        this.model = model;
        this.price = price;
        this.mileage = mileage;
        this.engine = engine;
        this.carBody = carBody;
        this.wheel = wheel;
    }

    // Methods that give information about car

    /**
     *
     * @param carBodyType Type of car body.
     * @return True when car has the same body type that was given or false when not.
     */
    public boolean hasBodyType(CarBodyType carBodyType) {
        return toCarBodyType.apply(this.carBody).equals(carBodyType);
    }

    /**
     *
     * @param from Price lower limit.
     * @param to Price upper limit.
     * @return True when car price is between given limits or false when not.
     */
    public boolean hasPriceBetween(BigDecimal from, BigDecimal to) {
        return this.price.compareTo(from) >= 0 && this.price.compareTo(to) <= 0;
    }

    public boolean hasEngineType(EngineType engineType) {
        return toEngineType.apply(this.engine).equals(engineType);
    }

    public boolean hasExactlyComponents(Set<String> givenComponents) {
        return givenComponents.containsAll(toComponents.apply(this.carBody)) && givenComponents.size() == toComponents.apply(this.carBody).size();
    }

    // Methods from Object class

    @Override
    public String toString() {
        return "{Car: Model - %s, Price - %s, Mileage - %s, Engine - %s, Car Body - %s, Wheel - %s}%n"
                .formatted(model, price, mileage, engine, carBody, wheel);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car car)) return false;
        return Objects.equals(model, car.model) && mileage == car.mileage && Objects.equals(price, car.price)
                && Objects.equals(engine, car.engine) && Objects.equals(carBody, car.carBody) && Objects.equals(wheel, car.wheel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, price, mileage, engine, carBody, wheel);
    }

    // Static methods

    public static Car of(Long id, String model, BigDecimal price, int mileage, Engine engine, CarBody carBody, Wheel wheel) {
        return new Car(id, model, price, mileage, engine, carBody, wheel);
    }

}
