package com.app.service.cars.provider.generic;

import java.util.List;

public interface DataProvider <T>{
    List<T> provide();
}
