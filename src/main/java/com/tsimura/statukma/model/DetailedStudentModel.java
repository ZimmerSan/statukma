package com.tsimura.statukma.model;

import com.tsimura.statukma.entity.Student;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class DetailedStudentModel extends MinimizedStudentModel {

    List<DetailedEnrollmentModel> enrollments;

    public DetailedStudentModel(Student student) {
        super(student);

        this.enrollments = student.getEnrollments().stream().map(DetailedEnrollmentModel::new).collect(Collectors.toList());
    }

}
