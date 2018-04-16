package com.tsimura.statukma.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tsimura.statukma.entity.enums.StudyLevel;
import com.tsimura.statukma.entity.enums.Ztype;
import com.tsimura.statukma.entity.extra.Semester;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"chair", "teachers"}, callSuper = true)
public class Discipline extends BaseEntity {

    @Lob
    private String annotation;

    @Column(name = "sub_cdoc")
    private Integer subCdoc;
    private Integer format;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Chair chair;

    @Enumerated(EnumType.STRING)
    private StudyLevel level;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinTable(name = "discipline_teacher", joinColumns = @JoinColumn(name = "teacher_id"), inverseJoinColumns = @JoinColumn(name = "discipline_id"))
    private Set<Teacher> teachers;

    @OneToMany(mappedBy = "discipline")
    @LazyCollection(LazyCollectionOption.EXTRA)
    @JsonIgnore
    private List<Enrollment> enrollments;

    @ManyToOne
    private Speciality speciality;

    @Embedded
    private Semester semester;

    private Boolean selected;
    private String status;

    @Enumerated(EnumType.STRING)
    private Ztype ztype;

}
