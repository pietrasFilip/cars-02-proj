package com.app.service.cars;

import com.app.persistence.model.car.Car;
import com.app.persistence.model.car.car_body.type.CarBodyType;
import com.app.persistence.model.car.engine.type.EngineType;
import com.app.persistence.model.car.exception.CarException;
import com.app.persistence.model.car.wheel.type.TyreType;
import com.app.service.car_statiscics.BigDecimalStatistics;
import com.app.service.car_statiscics.CarStatisticsItem;
import com.app.service.car_statiscics.collector.BigDecimalSummaryStatistics;
import com.app.service.cars.provider.CarsProvider;
import com.app.service.sort.AbstractSortServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.app.persistence.model.car.CarComparator.byModel;
import static com.app.persistence.model.car.CarFieldsMapper.*;
import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

@Service
public final class CarsServiceImpl extends AbstractSortServiceImpl<Car> implements CarsService{

    public CarsServiceImpl(CarsProvider dataProvider) {
        super(dataProvider);
    }

    /**
     *
     * @param carBodyType Type of car body.
     * @param from        Min price.
     * @param to          Max price.
     * @return            List of cars with specified CarBodyType and prices between from and to.
     */
    @Override
    public List<Car> getCarsWithSpecifiedBodyTypeWithPriceBetween(CarBodyType carBodyType, BigDecimal from, BigDecimal to) {
        if (from.compareTo(to) > 0) {
            throw new CarException("Wrong price range");
        }

        return cars
                .stream()
                .filter(car -> car.hasBodyType(carBodyType) && car.hasPriceBetween(from, to))
                .toList();
    }

    /**
     *
     * @param engineType Type of car engine.
     * @return           Alphabetically sorted list of cars with specified EngineType
     */
    @Override
    public List<Car> sortCarsAlphabeticallyWithSpecifiedEngineType(EngineType engineType) {
        return cars
                .stream()
                .filter(car -> car.hasEngineType(engineType))
                .sorted(comparing(convertToModel))
                .toList();
    }

    /**
     *
     * @param carStatisticsItem Statistic item.
     * @return                  Min, average, max, sum and count of specified statistic item.
     */
    @Override
    public BigDecimalStatistics getCarNumberStatistics(CarStatisticsItem carStatisticsItem) {
        return switch (carStatisticsItem) {
            case PRICE -> priceStats();
            case MILEAGE -> mileageStats();
            case ENGINE_POWER -> enginePowerStats();
        };
    }

    private BigDecimalStatistics priceStats() {
        return cars
                .stream()
                .map(convertToPrice)
                .collect(new BigDecimalSummaryStatistics());
    }

    private BigDecimalStatistics mileageStats() {
        return cars
                .stream()
                .map(convertToMileage)
                .map(BigDecimal::new)
                .collect(new BigDecimalSummaryStatistics());
    }

    private BigDecimalStatistics enginePowerStats() {
        return cars
                .stream()
                .map(convertToEnginePower)
                .map(BigDecimal::new)
                .collect(new BigDecimalSummaryStatistics());
    }

    /**
     *
     * @return Map of cars with their mileages sorted descending by value.
     */
    @Override
    public Map<Car, Integer> getCarsMileage() {
        return cars
                .stream()
                .collect(toMap(
                        car -> car,
                        convertToMileage,
                        (c1, c2) -> c1,
                        LinkedHashMap::new
                ))
                .entrySet()
                .stream()
                .sorted(reverseOrder(comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (c1, c2) -> c1, LinkedHashMap::new));
    }

    /**
     *
     * @return Map of TyreType and cars which contain specified TyreType sorted descending by map value length.
     */
    @Override
    public Map<TyreType, List<Car>> getCarsWithTyreType() {
        return cars
                .stream()
                .collect(groupingBy(convertToTyreType))
                .entrySet()
                .stream()
                .sorted(reverseOrder(comparingInt(c -> c.getValue().size())))
                .collect(toMap(Map.Entry::getKey, val -> val.getValue()
                                .stream()
                                .sorted(byModel)
                                .toList(),
                        (c1, c2) -> c1, LinkedHashMap::new));
    }

    /**
     *
     * @param components Set of components which car should contain.
     * @return           List of cars containing exactly specified components.
     */
    @Override
    public List<Car> getCarsWithSpecifiedComponents(Set<String> components) {
        return cars
                .stream()
                .filter(car -> car.hasExactlyComponents(components))
                .sorted(comparing(convertToModel))
                .toList();
    }
}
