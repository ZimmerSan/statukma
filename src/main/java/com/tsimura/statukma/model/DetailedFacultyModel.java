package com.tsimura.statukma.model;

import com.tsimura.statukma.entity.Faculty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class DetailedFacultyModel extends MinimizedFacultyModel {

    private Integer studentsCount;
    private Integer specialitiesCount;
    private Integer chairsCount;

    private List<MinimizedStudentModel> students;

    public DetailedFacultyModel(Faculty faculty) {
        super(faculty);
        this.chairsCount = faculty.getChairs().size();
        this.specialitiesCount = faculty.getSpecialities().size();
        this.studentsCount = Math.toIntExact(faculty.getSpecialities().stream().mapToLong(s -> s.getStudents().size()).sum());
        this.students = faculty.getSpecialities().stream().flatMap(s -> s.getStudents().stream()).map(MinimizedStudentModel::new).collect(Collectors.toList());
    }

}
