package com.tsimura.statukma.model;

import com.tsimura.statukma.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class EntityModel {

    private Integer id;
    private String name;

    public <ENT extends BaseEntity> EntityModel(ENT baseEntity) {
        this(baseEntity.getId(), baseEntity.getName());
    }

}
