package com.tsimura.statukma.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class EntityModel {

    private Integer id;
    private String name;

}
