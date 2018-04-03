package com.tsimura.statukma.model;

import com.tsimura.statukma.entity.Student;
import com.tsimura.statukma.entity.enums.Role;
import com.tsimura.statukma.entity.enums.StudyLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class MinimizedStudentModel extends EntityModel {

    private Integer format;
    private Integer yearOfStudy;
    private Integer yearEnter;

    private Date birthDate;

    private Role role = Role.STUDENT;
    private StudyLevel level;

    private Integer specId, facId;
    private String specName, facName;

    public MinimizedStudentModel(Student student) {
        this(
                student.getId(),
                student.getName(),
                student.getFormat(),
                student.getYearOfStudy(),
                student.getYearEnter(),
                student.getBirthDate(),
                student.getRole(),
                student.getLevel(),
                student.getSpeciality().getId(),
                student.getSpeciality().getFaculty().getId(),
                student.getSpeciality().getName(),
                student.getSpeciality().getFaculty().getName()
        );
    }

    private MinimizedStudentModel(Integer id, String name, Integer format, Integer yearOfStudy, Integer yearEnter, Date birthDate, Role role, StudyLevel level, Integer specId, Integer facId, String specName, String facName) {
        super(id, name);
        this.format = format;
        this.yearOfStudy = yearOfStudy;
        this.yearEnter = yearEnter;
        this.birthDate = birthDate;
        this.role = role;
        this.level = level;
        this.specId = specId;
        this.facId = facId;
        this.specName = specName;
        this.facName = facName;
    }

}
