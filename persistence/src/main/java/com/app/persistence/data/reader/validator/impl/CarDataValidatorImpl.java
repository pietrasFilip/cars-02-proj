package com.app.persistence.data.reader.validator.impl;

import com.app.persistence.data.reader.model.CarData;
import com.app.persistence.data.reader.validator.CarBodyDataValidator;
import com.app.persistence.data.reader.validator.CarDataValidator;
import com.app.persistence.data.reader.validator.EngineDataValidator;
import com.app.persistence.data.reader.validator.WheelDataValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.app.persistence.data.reader.validator.DataValidator.*;

@Component
@RequiredArgsConstructor
public class CarDataValidatorImpl implements CarDataValidator {
    private final EngineDataValidator engineDataValidator;
    private final CarBodyDataValidator carBodyDataValidator;
    private final WheelDataValidator wheelDataValidator;

    @Value("${validator.regex.car.model}")
    private String carModelRegex;

    @Override
    public List<CarData> validate(List<CarData> carData) {
        return carData
                .stream()
                .map(this::validateSingleCar)
                .toList();
    }

    @Override
    public CarData validateSingleCar(CarData carData) {
        return CarData.of(
                carData.getId(),
                validateMatchesRegex(carModelRegex, carData.getModel()),
                validatePositiveDecimal(carData.getPrice()),
                validatePositiveInt(carData.getMileage()),
                engineDataValidator.validate(carData.getEngine()),
                carBodyDataValidator.validate(carData.getCarBody()),
                wheelDataValidator.validate(carData.getWheel())
        );
    }
}
