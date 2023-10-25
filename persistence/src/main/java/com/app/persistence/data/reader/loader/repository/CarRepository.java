package com.app.persistence.data.reader.loader.repository;

import com.app.persistence.data.reader.loader.repository.db.generic.CrudRepository;
import com.app.persistence.data.reader.model.db.CarDataDb;

import java.util.List;

public interface CarRepository extends CrudRepository<CarDataDb, Long> {
    List<CarDataDb> findAllCars();
}
