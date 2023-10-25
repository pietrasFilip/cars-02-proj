package com.app.persistence.data.reader.model.db;

import com.app.persistence.data.reader.model.CarBodyData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CarBodyDataDb {
    private Long id;
    private String color;
    private String type;
    private String component;

    public CarBodyData toCarBodyData() {
        return CarBodyData.of(id, color, type, new HashSet<>(
                List.of(component)));
    }
}
