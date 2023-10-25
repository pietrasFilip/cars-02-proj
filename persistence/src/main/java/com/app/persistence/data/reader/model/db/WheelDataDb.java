package com.app.persistence.data.reader.model.db;

import com.app.persistence.data.reader.model.WheelData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WheelDataDb {
    private Long id;
    private String model;
    private int size;
    private String tyreType;

    public WheelData toWheelData() {
        return WheelData.of(id, model, size, tyreType);
    }
}
