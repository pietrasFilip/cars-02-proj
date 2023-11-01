package com.app.web.routing;

import com.app.persistence.model.car.car_body.type.CarBodyType;
import com.app.persistence.model.car.engine.type.EngineType;
import com.app.service.car_statiscics.CarStatisticsItem;
import com.app.service.cars.CarsService;
import com.app.service.cars.provider.CarsProvider;
import com.app.web.dto.ResponseDto;
import com.app.web.transformer.JsonTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Collectors;

import static spark.Spark.get;
import static spark.Spark.path;

@Component
@RequiredArgsConstructor
public class CarsRouting {
    private final CarsService carsService;
    private final JsonTransformer jsonTransformer;
    private final CarsProvider carsProvider;

    public void routes() {
        // http://localhost:8080/is_auth
        path("/is_auth", () -> {

            // http://localhost:8080/is_auth/cars
            path("/cars", () -> {

                // http://localhost:8080/is_auth/cars/mileage
                get(
                        "/mileage",
                        (request, response) -> {
                            response.header("Content-Type", "application/json;charset=utf-8");
                            return new ResponseDto<>(carsService.getCarsMileage());
                        },
                        jsonTransformer
                );

                // http://localhost:8080/is_auth/cars/statistic/statisticItem
                get(
                        "/statistics/:statisticItem",
                        (request, response) -> {
                            var statisticItem = CarStatisticsItem.valueOf(request.params("statisticItem").toUpperCase());
                            response.header("Content-Type", "application/json;charset=utf-8");
                            return new ResponseDto<>(carsService.getCarNumberStatistics(statisticItem));
                        },
                        jsonTransformer
                );

                // http://localhost:8080/is_auth/cars/with_specified
                path("/with_specified", () -> {

                    // http://localhost:8080/is_auth/cars/with_specified/carBody/priceFrom/priceTo
                    get(
                            "/:carBody/:priceFrom/:priceTo",
                            (request, response) -> {
                                var carBodyType = CarBodyType.valueOf(request.params("carBody").toUpperCase());
                                var priceFrom = new BigDecimal(request.params("priceFrom"));
                                var priceTo = new BigDecimal(request.params("priceTo"));

                                response.header("Content-Type", "application/json;charset=utf-8");
                                return new ResponseDto<>(carsService.getCarsWithSpecifiedBodyTypeWithPriceBetween(
                                        carBodyType, priceFrom, priceTo
                                ));
                            },
                            jsonTransformer
                    );

                    // http://localhost:8080/is_auth/cars/with_specified/engineType
                    get(
                            "/:engineType",
                            (request, response) -> {
                                var engineType = EngineType.valueOf(request.params("engineType").toUpperCase());

                                response.header("Content-Type", "application/json;charset=utf-8");
                                return new ResponseDto<>(
                                        carsService.sortCarsAlphabeticallyWithSpecifiedEngineType(engineType));
                            },
                            jsonTransformer
                    );
                });
            });
        });

        // http://localhost:8080/admin
        path("/admin", () -> {

            // http://localhost:8080/admin/cars
            path("/cars", () -> {

                // http://localhost:8080/admin/cars/tyre_type
                get(
                        "/tyre_type",
                        (request, response) -> {
                            response.header("Content-Type", "application/json;charset=utf-8");
                            return new ResponseDto<>(carsService.getCarsWithTyreType());
                        },
                        jsonTransformer
                );

                // http://localhost:8080/admin/cars/components
                path("/components", () -> {

                    // http://localhost:8080/admin/cars/components/component1-component2
                    get(
                            "/:components",
                            (request, response) -> {
                                var components = Arrays.stream(request.params("components")
                                        .split("-"))
                                        .collect(Collectors.toSet());
                                response.header("Content-Type", "application/json;charset=utf-8");
                                return new ResponseDto<>(carsService.getCarsWithSpecifiedComponents(components));
                            },
                            jsonTransformer
                    );
                });
            });
        });

        // http://localhost:8080/all
        path("/all", () -> {

            // http://localhost:8080/all/cars
            get(
                    "/cars",
                    (request, response) -> {
                        response.header("Content-Type", "application/json;charset=utf-8");
                        return new ResponseDto<>(carsProvider.provide());
                    },
                    jsonTransformer
            );
        });
    }
}
