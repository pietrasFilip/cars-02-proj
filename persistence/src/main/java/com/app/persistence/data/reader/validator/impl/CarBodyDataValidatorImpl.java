package com.app.persistence.data.reader.validator.impl;

import com.app.persistence.data.reader.model.CarBodyData;
import com.app.persistence.data.reader.validator.CarBodyDataValidator;
import com.app.persistence.data.reader.validator.DataValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CarBodyDataValidatorImpl implements CarBodyDataValidator {
    @Value("${validator.regex.items.name}")
    private String itemsRegex;

    @Override
    public CarBodyData validate(CarBodyData carBodyData) {
        return CarBodyData.of(
                carBodyData.getId(),
                DataValidator.validateNull(carBodyData.getColor()),
                DataValidator.validateNull(carBodyData.getType()),
                DataValidator.validateItemsMatchesRegex(itemsRegex, carBodyData.getComponents())
        );
    }
}
