package com.app.persistence.data.reader.model.db;

import com.app.persistence.data.reader.model.EngineData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EngineDataDb {
    private Long id;
    private String type;
    private double power;

    public EngineData toEngineData() {
        return EngineData.of(id, type, power);
    }
}
