package com.app.persistence.data.reader.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CarBodyData {
    private Long id;
    private String color;
    private String type;
    private Set<String> components;


    public static CarBodyData of(Long id, String color, String type, Set<String> components) {
        return new CarBodyData(id, color, type, components);
    }
}
