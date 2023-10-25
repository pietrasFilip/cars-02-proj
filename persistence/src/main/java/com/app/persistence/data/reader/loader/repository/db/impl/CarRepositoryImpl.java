package com.app.persistence.data.reader.loader.repository.db.impl;

import com.app.persistence.data.reader.loader.repository.CarRepository;
import com.app.persistence.data.reader.loader.repository.db.generic.AbstractCrudRepository;
import com.app.persistence.data.reader.loader.repository.db.impl.exception.CarDataRepositoryException;
import com.app.persistence.data.reader.model.db.CarBodyDataDb;
import com.app.persistence.data.reader.model.db.CarDataDb;
import com.app.persistence.data.reader.model.db.EngineDataDb;
import com.app.persistence.data.reader.model.db.WheelDataDb;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRepositoryImpl extends AbstractCrudRepository<CarDataDb, Long> implements CarRepository {

    public CarRepositoryImpl(Jdbi jdbi) {
        super(jdbi);
    }

    @Override
    public List<CarDataDb> findAllCars() {
        try {
            var sql = """
                select cars.id as car_id, cars.model, cars.price, cars.mileage,
                       engines.id as engine_id, engines.type as engine_type, engines.power,
                       cars_bodies.id as car_body_id, cars_bodies.color, cars_bodies.type as car_body_type, components.name as component_name,
                       wheels.id as wheel_id, wheels.type as tyre_type, wheels.model as wheel_model, wheels.size
                from cars
                         join engines on cars.engine_id = engines.id
                         join cars_bodies on cars.car_body_id = cars_bodies.id
                         join cars_bodies_with_components cbwc on cars_bodies.id = cbwc.car_body_id
                         join components on cbwc.component_id = components.id
                         join wheels on cars.wheel_id = wheels.id;
                         """;

            return jdbi.withHandle(handle -> handle
                    .createQuery(sql)
                    .map((rs, ctx) -> CarDataDb
                            .builder()
                            .id(rs.getLong("car_id"))
                            .model(rs.getString("model"))
                            .price(rs.getBigDecimal("price"))
                            .mileage(rs.getInt("mileage"))
                            .engine(EngineDataDb
                                    .builder()
                                    .id(rs.getLong("engine_id"))
                                    .type(rs.getString("engine_type"))
                                    .power(rs.getDouble("power"))
                                    .build())
                            .carBody(CarBodyDataDb
                                    .builder()
                                    .id(rs.getLong("car_body_id"))
                                    .color(rs.getString("color"))
                                    .type(rs.getString("car_body_type"))
                                    .component(rs.getString("component_name"))
                                    .build())
                            .wheel(WheelDataDb
                                    .builder()
                                    .id(rs.getLong("wheel_id"))
                                    .tyreType(rs.getString("tyre_type"))
                                    .model(rs.getString("wheel_model"))
                                    .size(rs.getInt("size"))
                                    .build())
                            .build())
                    .list());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CarDataRepositoryException(e.getMessage());
        }
    }
}
