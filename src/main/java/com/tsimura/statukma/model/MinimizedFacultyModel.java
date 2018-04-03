package com.tsimura.statukma.model;

import com.tsimura.statukma.entity.Faculty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MinimizedFacultyModel extends EntityModel {

    public MinimizedFacultyModel(Faculty faculty) {
        super(faculty.getId(), faculty.getName());
    }

}
