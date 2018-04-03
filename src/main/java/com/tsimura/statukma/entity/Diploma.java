package com.tsimura.statukma.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tsimura.statukma.entity.extra.DiplomaId;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@Data
@IdClass(DiplomaId.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Diploma {

    @Id private String ser;
    @Id private String num;

}
