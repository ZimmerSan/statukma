package com.tsimura.statukma.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tsimura.statukma.entity.enums.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"disciplines", "chair"}, callSuper = true)
public class Teacher extends BaseEntity {

    @ManyToMany
    @JsonManagedReference
    @JoinTable(name = "discipline_teacher", joinColumns = @JoinColumn(name = "discipline_id"), inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    private Set<Discipline> disciplines;

    @ManyToOne
    private Chair chair;

    @Enumerated(EnumType.STRING)
    private Role role = Role.TEACHER;

}
