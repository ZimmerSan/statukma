package com.tsimura.statukma.model;

import com.tsimura.statukma.entity.Speciality;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DetailedSpecialityModel extends MinimizedSpecialityModel {

    private Integer studentsCount;

    public DetailedSpecialityModel(Speciality speciality) {
        super(speciality);
        this.studentsCount = speciality.getStudents().size();
    }

}
