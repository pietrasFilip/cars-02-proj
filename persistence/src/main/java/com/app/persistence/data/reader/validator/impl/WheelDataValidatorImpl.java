package com.app.persistence.data.reader.validator.impl;

import com.app.persistence.data.reader.model.WheelData;
import com.app.persistence.data.reader.validator.DataValidator;
import com.app.persistence.data.reader.validator.WheelDataValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WheelDataValidatorImpl implements WheelDataValidator {
    @Value("${validator.regex.wheel.model}")
    private String modelRegex;

    @Override
    public WheelData validate(WheelData wheelData) {
        return WheelData.of(
                wheelData.getId(),
                DataValidator.validateMatchesRegex(modelRegex, wheelData.getModel()),
                DataValidator.validatePositiveInt(wheelData.getSize()),
                DataValidator.validateNull(wheelData.getTyreType())
        );
    }
}
