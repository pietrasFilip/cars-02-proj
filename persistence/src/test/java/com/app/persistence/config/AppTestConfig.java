package com.app.persistence.config;

import com.app.persistence.data.reader.factory.FromDbToCarWithValidator;
import com.app.persistence.data.reader.factory.FromJsonToCarWithValidator;
import com.app.persistence.data.reader.factory.FromTxtToCarWithValidator;
import com.app.persistence.data.reader.loader.repository.CarRepository;
import com.app.persistence.data.reader.loader.repository.db.impl.CarRepositoryImpl;
import com.app.persistence.data.reader.processor.CarDataProcessor;
import com.app.persistence.data.reader.processor.impl.CarDataProcessorDbImpl;
import com.app.persistence.data.reader.processor.impl.CarDataProcessorJsonImpl;
import com.app.persistence.data.reader.processor.impl.CarDataProcessorTxtImpl;
import com.app.persistence.data.reader.validator.CarBodyDataValidator;
import com.app.persistence.data.reader.validator.CarDataValidator;
import com.app.persistence.data.reader.validator.EngineDataValidator;
import com.app.persistence.data.reader.validator.WheelDataValidator;
import com.app.persistence.data.reader.validator.impl.CarBodyDataValidatorImpl;
import com.app.persistence.data.reader.validator.impl.CarDataValidatorImpl;
import com.app.persistence.data.reader.validator.impl.EngineDataValidatorImpl;
import com.app.persistence.data.reader.validator.impl.WheelDataValidatorImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.jdbi.v3.core.Jdbi;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@RequiredArgsConstructor
public class AppTestConfig {
    private final Environment environment;

    @Bean
    public Gson gson() {
        return new GsonBuilder().serializeNulls().setPrettyPrinting().create();
    }

    @Bean
    public Jdbi jdbi() {
        var url = environment.getRequiredProperty("db.url");
        var username = environment.getRequiredProperty("db.username");
        var password = environment.getRequiredProperty("db.password");
        return Jdbi.create(url, username, password);
    }

    @Bean
    public WheelDataValidator wheelDataValidator() {
        return new WheelDataValidatorImpl();
    }

    @Bean
    public CarBodyDataValidator carBodyDataValidator() {
        return new CarBodyDataValidatorImpl();
    }

    @Bean
    public EngineDataValidator engineDataValidator() {
        return new EngineDataValidatorImpl();
    }

    @Bean
    public CarRepository carRepository(Jdbi jdbi) {
        return new CarRepositoryImpl(jdbi);
    }

    @Bean
    public CarDataValidator carDataValidator(EngineDataValidator engineDataValidator,
                                             CarBodyDataValidator carBodyDataValidator,
                                             WheelDataValidator wheelDataValidator) {
        return new CarDataValidatorImpl(engineDataValidator, carBodyDataValidator, wheelDataValidator);
    }

    @Bean
    public FromDbToCarWithValidator dataDbFactory(CarRepository carRepository, CarDataValidator carDataValidator) {
        return new FromDbToCarWithValidator(carRepository, carDataValidator);
    }

    @Bean
    FromJsonToCarWithValidator dataJsonFactory(Gson gson, CarDataValidator carDataValidator) {
        return new FromJsonToCarWithValidator(gson, carDataValidator);
    }

    @Bean
    FromTxtToCarWithValidator dataTxtFactory(CarDataValidator carDataValidator) {
        return new FromTxtToCarWithValidator(carDataValidator);
    }

    @Bean
    public CarDataProcessor carDataDbProcessor(FromDbToCarWithValidator dataDbFactory) {
        return new CarDataProcessorDbImpl(dataDbFactory);
    }

    @Bean
    public CarDataProcessor carDataJsonProcessor(FromJsonToCarWithValidator dataJsonFactory) {
        return new CarDataProcessorJsonImpl(dataJsonFactory);
    }

    @Bean
    public CarDataProcessor carDataTxtProcessor(FromTxtToCarWithValidator dataTxtFactory) {
        return new CarDataProcessorTxtImpl(dataTxtFactory);
    }
}
