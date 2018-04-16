package com.tsimura.statukma.entity;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.tsimura.statukma.entity.enums.RegType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(exclude = {"discipline", "student"}, callSuper = true)
public class Enrollment extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonUnwrapped
    private Discipline discipline;

    private Integer studentCourse;
    private Date enrollmentTime;
    @Enumerated(EnumType.STRING)
    private RegType regType;

}
