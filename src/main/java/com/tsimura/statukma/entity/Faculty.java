package com.tsimura.statukma.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(exclude = {"chairs", "specialities"}, callSuper = true)
public class Faculty extends BaseEntity {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "faculty")
    @LazyCollection(LazyCollectionOption.EXTRA)
    private List<Chair> chairs = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "faculty")
    @LazyCollection(LazyCollectionOption.EXTRA)
    private List<Speciality> specialities = new ArrayList<>();

}
