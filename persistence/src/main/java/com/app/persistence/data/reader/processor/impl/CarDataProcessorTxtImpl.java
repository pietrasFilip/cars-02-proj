package com.app.persistence.data.reader.processor.impl;

import com.app.persistence.data.reader.converter.Converter;
import com.app.persistence.data.reader.factory.FromTxtToCarWithValidator;
import com.app.persistence.data.reader.loader.DataLoader;
import com.app.persistence.data.reader.model.CarData;
import com.app.persistence.data.reader.processor.CarDataProcessor;
import com.app.persistence.data.reader.validator.DataValidator;
import com.app.persistence.model.car.Car;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarDataProcessorTxtImpl implements CarDataProcessor {
    private final DataLoader<List<CarData>> dataLoader;
    private final DataValidator<List<CarData>> validator;
    private final Converter<List<CarData>, List<Car>> converter;

    public CarDataProcessorTxtImpl(FromTxtToCarWithValidator dataFactory) {
        this.dataLoader = dataFactory.createDataLoader();
        this.validator = dataFactory.createValidator();
        this.converter = dataFactory.createConverter();
    }

    @Override
    public List<Car> process(String path) {
        var loadedData = dataLoader.load(path);
        var validatedData = validator.validate(loadedData);
        return converter.convert(validatedData);
    }
}
