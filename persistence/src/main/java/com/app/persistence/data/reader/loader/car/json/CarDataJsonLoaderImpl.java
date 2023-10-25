package com.app.persistence.data.reader.loader.car.json;

import com.app.persistence.data.reader.loader.FromJsonToObjectLoader;
import com.app.persistence.data.reader.model.CarData;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CarDataJsonLoaderImpl extends FromJsonToObjectLoader<List<CarData>> implements CarDataJsonLoader {
    public CarDataJsonLoaderImpl(Gson gson) {
        super(gson);
    }

    @Override
    public List<CarData> load(String path) {
        return loadObject(path);
    }
}