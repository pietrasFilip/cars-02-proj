package com.app.persistence.data.reader.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WheelData {
    private Long id;
    private String model;
    private int size;
    private String tyreType;

    public static WheelData of(Long id, String model, int size, String tyreType) {
        return new WheelData(id, model, size, tyreType);
    }
}
