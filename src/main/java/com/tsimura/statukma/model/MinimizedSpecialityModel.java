package com.tsimura.statukma.model;

import com.tsimura.statukma.entity.Speciality;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MinimizedSpecialityModel extends EntityModel {

    private String facName;
    private Integer facId;

    public MinimizedSpecialityModel(Speciality speciality) {
        this(
                speciality.getId(),
                speciality.getName(),
                speciality.getFaculty().getName(),
                speciality.getFaculty().getId()
        );
    }

    private MinimizedSpecialityModel(Integer id, String name, String facName, Integer facId) {
        super(id, name);
        this.facName = facName;
        this.facId = facId;
    }
}
