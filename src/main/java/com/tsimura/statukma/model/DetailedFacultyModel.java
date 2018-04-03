package com.tsimura.statukma.model;

import com.tsimura.statukma.entity.Faculty;
import com.tsimura.statukma.entity.Student;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class DetailedFacultyModel extends MinimizedFacultyModel {

    private Integer studentsCount;
    private Integer specialitiesCount;
    private Integer chairsCount;

    private Map<Integer, Long> studentsByCourses;

    public DetailedFacultyModel(Faculty faculty) {
        super(faculty);
        this.chairsCount = faculty.getChairs().size();
        this.specialitiesCount = faculty.getSpecialities().size();
        this.studentsCount = Math.toIntExact(faculty.getSpecialities().stream().mapToLong(s -> s.getStudents().size()).sum());
        this.studentsByCourses = faculty.getSpecialities().stream().flatMap(s -> s.getStudents().stream()).collect(Collectors.groupingBy(Student::getYearOfStudy, Collectors.counting()));
    }

}
