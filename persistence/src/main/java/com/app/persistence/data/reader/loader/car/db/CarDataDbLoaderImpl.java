package com.app.persistence.data.reader.loader.car.db;

import com.app.persistence.data.reader.loader.repository.CarRepository;
import com.app.persistence.data.reader.model.CarData;
import com.app.persistence.data.reader.model.db.CarDataDb;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toMap;

@Component
@RequiredArgsConstructor
public class CarDataDbLoaderImpl implements CarDataDbLoader{
    private final CarRepository carDataRepository;
    @Override
    public List<CarData> load(String path) {
        return carDataRepository
                .findAllCars()
                .stream()
                .map(CarDataDb::toCarData)
                .collect(toMap(CarData::getId, val -> val, (c1, c2) -> {
                    c1.getCarBody().getComponents().addAll(c2.getCarBody().getComponents());
                    return c1;
                }))
                .values()
                .stream()
                .toList();
    }
}
