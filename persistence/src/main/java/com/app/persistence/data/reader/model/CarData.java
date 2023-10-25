package com.app.persistence.data.reader.model;

import com.app.persistence.model.car.Car;
import com.app.persistence.model.car.car_body.CarBody;
import com.app.persistence.model.car.car_body.color.CarBodyColor;
import com.app.persistence.model.car.car_body.type.CarBodyType;
import com.app.persistence.model.car.engine.Engine;
import com.app.persistence.model.car.engine.type.EngineType;
import com.app.persistence.model.car.wheel.Wheel;
import com.app.persistence.model.car.wheel.type.TyreType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CarData {
    private Long id;
    private String model;
    private BigDecimal price;
    private int mileage;
    private EngineData engine;
    private CarBodyData carBody;
    private WheelData wheel;

    public Car toCar() {
        return Car.of(
                id, model, price, mileage,
                Engine.of(engine.getId(), EngineType.valueOf(engine.getType()), engine.getPower()),
                CarBody.of(carBody.getId(), CarBodyColor.valueOf(carBody.getColor()),
                        CarBodyType.valueOf(carBody.getType()), carBody.getComponents()),
                Wheel.of(wheel.getId(), wheel.getModel(), wheel.getSize(),
                        TyreType.valueOf(wheel.getTyreType()))
        );
    }

    public static CarData of(Long id, String model, BigDecimal price, int mileage, EngineData engine, CarBodyData carBody, WheelData wheel) {
        return new CarData(id, model, price, mileage, engine, carBody, wheel);
    }
}
