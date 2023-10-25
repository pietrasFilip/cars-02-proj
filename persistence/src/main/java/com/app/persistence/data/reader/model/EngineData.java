package com.app.persistence.data.reader.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EngineData {
    private Long id;
    private String type;
    private double power;

    public static EngineData of(Long id, String engineType, double power) {
        return new EngineData(id, engineType, power);
    }
}
