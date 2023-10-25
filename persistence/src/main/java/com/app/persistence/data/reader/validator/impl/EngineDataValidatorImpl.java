package com.app.persistence.data.reader.validator.impl;

import com.app.persistence.data.reader.model.EngineData;
import com.app.persistence.data.reader.validator.EngineDataValidator;
import org.springframework.stereotype.Component;

import static com.app.persistence.data.reader.validator.DataValidator.validateNull;
import static com.app.persistence.data.reader.validator.DataValidator.validatePositiveDouble;

@Component
public class EngineDataValidatorImpl implements EngineDataValidator {
    @Override
    public EngineData validate(EngineData engineData) {
        return EngineData.of(
                engineData.getId(),
                validateNull(engineData.getType()),
                validatePositiveDouble(engineData.getPower())
        );
    }
}
