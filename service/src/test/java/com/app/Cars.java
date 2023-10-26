package com.app;

import com.app.persistence.model.car.Car;
import com.app.persistence.model.car.car_body.CarBody;
import com.app.persistence.model.car.engine.Engine;
import com.app.persistence.model.car.wheel.Wheel;

import java.math.BigDecimal;
import java.util.Set;

import static com.app.persistence.model.car.car_body.color.CarBodyColor.*;
import static com.app.persistence.model.car.car_body.type.CarBodyType.COMBI;
import static com.app.persistence.model.car.car_body.type.CarBodyType.HATCHBACK;
import static com.app.persistence.model.car.engine.type.EngineType.*;
import static com.app.persistence.model.car.wheel.type.TyreType.SUMMER;
import static com.app.persistence.model.car.wheel.type.TyreType.WINTER;

public interface Cars {
    Car BMW = Car.of(1L, "BMW", BigDecimal.valueOf(2000), 250,
            Engine.of(1L, DIESEL, 150.2),
            CarBody.of(1L, RED, COMBI, Set.of("A", "B", "C")),
            Wheel.of(1L, "A", 20, SUMMER));

    Car AUDI = Car.of(2L, "AUDI", BigDecimal.valueOf(1200), 900,
            Engine.of(2L, LPG, 158.2),
            CarBody.of(2L, BLACK, HATCHBACK, Set.of("A")),
            Wheel.of(2L, "A", 18, WINTER));

    Car HONDA = Car.of(3L, "HONDA", BigDecimal.valueOf(1000), 800,
            Engine.of(3L, DIESEL, 110.4),
            CarBody.of(3L, GREEN, COMBI, Set.of("B", "C", "D")),
            Wheel.of(3L, "A", 19, SUMMER));

    Car SEAT = Car.of(4L, "SEAT", BigDecimal.valueOf(2000), 350,
            Engine.of(4L, GASOLINE, 280.54),
            CarBody.of(4L, RED, COMBI, Set.of("D")),
            Wheel.of(3L, "A", 19, SUMMER));

    Car FORD = Car.of(5L, "FORD", BigDecimal.valueOf(5000), 250,
            Engine.of(5L, GASOLINE, 200.8),
            CarBody.of(5L, BLACK, HATCHBACK, Set.of("B", "D")),
            Wheel.of(3L, "A", 19, SUMMER));
}
