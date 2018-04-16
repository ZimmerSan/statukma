package com.tsimura.statukma.model;

import com.tsimura.statukma.entity.Discipline;
import com.tsimura.statukma.entity.Teacher;
import com.tsimura.statukma.entity.enums.StudyLevel;
import com.tsimura.statukma.entity.extra.Semester;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class MinimizedDisciplineModel extends EntityModel {

    private String annotation;
    private Integer format;

    private Integer chairId, facId;
    private String chairName, facName;
    private StudyLevel level;

    private Integer enrollmentsCount;

    private Set<Integer> teacherIds;

    private Semester semester;

    public MinimizedDisciplineModel(Discipline discipline) {
        super(discipline.getId(), discipline.getName());
        this.annotation = discipline.getAnnotation();
        this.format = discipline.getFormat();

        this.chairId = discipline.getChair().getId();
        this.chairName = discipline.getChair().getName();

        this.facId = discipline.getChair().getFaculty().getId();
        this.facName = discipline.getChair().getFaculty().getName();

        this.level = discipline.getLevel();
        this.teacherIds = discipline.getTeachers().stream().map(Teacher::getId).collect(Collectors.toSet());
        this.semester = discipline.getSemester();

        this.enrollmentsCount = discipline.getEnrollments().size();
    }

}
