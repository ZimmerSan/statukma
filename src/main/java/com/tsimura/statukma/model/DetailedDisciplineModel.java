package com.tsimura.statukma.model;

import com.tsimura.statukma.entity.Discipline;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class DetailedDisciplineModel extends MinimizedDisciplineModel {

    List<DetailedEnrollmentModel> enrollments;

    public DetailedDisciplineModel(Discipline discipline) {
        super(discipline);

        this.enrollments = discipline.getEnrollments().stream().map(DetailedEnrollmentModel::new).collect(Collectors.toList());
    }

}
