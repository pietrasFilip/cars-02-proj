package com.app.service.sort;

import com.app.service.cars.provider.generic.DataProvider;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public abstract class AbstractSortServiceImpl <T> implements SortService<T>{
    protected final Set<T> cars;

    protected AbstractSortServiceImpl(DataProvider<T> dataProvider) {
        this.cars = new HashSet<>(dataProvider.provide());
    }
    @Override
    public List<T> sortBy(Comparator<T> comparator) {
        return cars
                .stream()
                .sorted(comparator)
                .toList();
    }
}
