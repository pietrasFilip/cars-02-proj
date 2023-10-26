package com.app.service.cars.provider;

import com.app.persistence.data.reader.processor.CarDataProcessor;
import com.app.persistence.data.reader.processor.type.ProcessorType;
import com.app.persistence.model.car.Car;
import com.app.service.cars.provider.generic.AbstractDataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarsProviderImpl extends AbstractDataProvider<Car> implements CarsProvider{

    @Value("${processor.type}")
    private String processorType;
    @Value("${processor.json.path}")
    private String jsonPath;
    @Value("${processor.txt.path}")
    private String txtPath;
    private final CarDataProcessor carDataDbProcessor;
    private final CarDataProcessor carDataJsonProcessor;
    private final CarDataProcessor carDataTxtProcessor;

    @Override
    public List<Car> provide() {
        return switch (ProcessorType.valueOf(processorType)) {
            case FROM_DB_TO_CAR_WITH_VALIDATOR -> carDataDbProcessor.process("");
            case FROM_JSON_TO_CAR_WITH_VALIDATOR -> carDataJsonProcessor.process(jsonPath);
            case FROM_TXT_TO_CAR_WITH_VALIDATOR -> carDataTxtProcessor.process(txtPath);
        };
    }
}
