package com.tsimura.statukma.controller;

import com.tsimura.statukma.entity.Student;
import com.tsimura.statukma.model.DetailedStudentModel;
import com.tsimura.statukma.model.MinimizedStudentModel;
import com.tsimura.statukma.service.AbstractService;
import com.tsimura.statukma.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController extends AbstractController<Student, MinimizedStudentModel, DetailedStudentModel> {

    private final StudentService studentService;

    @Override
    protected AbstractService<Student, MinimizedStudentModel, DetailedStudentModel> getService() {
        return studentService;
    }

}