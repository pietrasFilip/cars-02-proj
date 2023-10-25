package com.app.persistence.data.reader.model.db;

import com.app.persistence.data.reader.model.CarData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CarDataDb {
    private Long id;
    private String model;
    private BigDecimal price;
    private int mileage;
    private EngineDataDb engine;
    private CarBodyDataDb carBody;
    private WheelDataDb wheel;

    public CarData toCarData() {
        return CarData.of(
                id, model, price, mileage, engine.toEngineData(), carBody.toCarBodyData(), wheel.toWheelData()
        );
    }
}
