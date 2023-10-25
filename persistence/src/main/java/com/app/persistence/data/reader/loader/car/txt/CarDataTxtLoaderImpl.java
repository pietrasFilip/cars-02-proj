package com.app.persistence.data.reader.loader.car.txt;

import com.app.persistence.data.reader.loader.exception.TxtLoaderException;
import com.app.persistence.data.reader.model.CarBodyData;
import com.app.persistence.data.reader.model.CarData;
import com.app.persistence.data.reader.model.EngineData;
import com.app.persistence.data.reader.model.WheelData;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.valueOf;

@Component
public class CarDataTxtLoaderImpl implements CarDataTxtLoader{
    @Override
    public List<CarData> load(String path) {
        try (var lines = Files.lines(Paths.get(path))){
            return lines
                    .map(line -> {
                        var items = line.split(";");
                        var components = Arrays
                                .stream(items[10].split(","))
                                .collect(Collectors.toSet());

                        return CarData.of(valueOf(items[0]), items[1], new BigDecimal(items[2]),
                                parseInt(items[3]),
                                EngineData.of(valueOf(items[4]), items[5], parseDouble(items[6])),
                                        CarBodyData.of(valueOf(items[7]), items[8], items[9], components),
                                        WheelData.of(valueOf(items[11]), items[12],
                                                parseInt(items[13]), items[14]));
                    })
                    .toList();
        } catch (Exception e) {
            throw new TxtLoaderException(e.getMessage());
        }
    }
}
