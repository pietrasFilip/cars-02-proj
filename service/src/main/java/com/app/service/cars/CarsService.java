package com.app.service.cars;

import com.app.persistence.model.car.Car;
import com.app.persistence.model.car.car_body.type.CarBodyType;
import com.app.persistence.model.car.engine.type.EngineType;
import com.app.persistence.model.car.wheel.type.TyreType;
import com.app.service.car_statiscics.BigDecimalStatistics;
import com.app.service.car_statiscics.CarStatisticsItem;
import com.app.service.sort.SortService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

public sealed interface CarsService extends SortService<Car> permits CarsServiceImpl {
    List<Car> getCarsWithSpecifiedBodyTypeWithPriceBetween(CarBodyType carBodyType, BigDecimal from, BigDecimal to);
    List<Car> sortCarsAlphabeticallyWithSpecifiedEngineType(EngineType engineType);
    BigDecimalStatistics getCarNumberStatistics(CarStatisticsItem carStatisticsItem);
    Map<Car, Integer> getCarsMileage();
    Map<TyreType, List<Car>> getCarsWithTyreType();
    List<Car> getCarsWithSpecifiedComponents(Set<String> components);
}
