package com.tsimura.statukma.model;

import com.tsimura.statukma.entity.Enrollment;
import com.tsimura.statukma.entity.enums.RegType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class DetailedEnrollmentModel extends EntityModel {

    private Integer studentCourse;
    private Date enrollmentTime;
    private RegType regType;

    private MinimizedDisciplineModel discipline;
    private MinimizedStudentModel student;

    public DetailedEnrollmentModel(Enrollment enrollment) {
        super(enrollment.getId(), enrollment.getName());
        this.studentCourse = enrollment.getStudentCourse();
        this.enrollmentTime = enrollment.getEnrollmentTime();
        this.regType = enrollment.getRegType();

        this.discipline = new MinimizedDisciplineModel(enrollment.getDiscipline());
        this.student = new MinimizedStudentModel(enrollment.getStudent());
    }

}
