package com.tsimura.statukma.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tsimura.statukma.entity.enums.Role;
import com.tsimura.statukma.entity.enums.StudyLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(exclude = {"speciality"}, callSuper = true)
public class Student extends BaseEntity {

    private Integer code;
    @Column(name = "np_cdoc")
    private String npCdoc;

    private Integer format;
    @Column(name = "year")
    private Integer yearOfStudy;
    private Integer yearEnter;

    private Date birthDate;

    @Enumerated(EnumType.STRING)
    private Role role = Role.STUDENT;
    @Enumerated(EnumType.STRING)
    private StudyLevel level;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Speciality speciality;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private StudentCard studentCard;
    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Diploma diploma;

}
