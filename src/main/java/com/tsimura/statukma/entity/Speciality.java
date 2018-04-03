package com.tsimura.statukma.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(exclude = {"faculty", "students"}, callSuper = true)
public class Speciality extends BaseEntity {

    private String code;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Faculty faculty;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "speciality")
    @LazyCollection(LazyCollectionOption.EXTRA)
    private List<Student> students = new ArrayList<>();

}
