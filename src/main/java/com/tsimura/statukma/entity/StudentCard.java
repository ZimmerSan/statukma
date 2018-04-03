package com.tsimura.statukma.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tsimura.statukma.entity.extra.StudentCardId;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Date;

@Entity
@Data
@IdClass(StudentCardId.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class StudentCard {

    @Id private String ser;
    @Id private String num;

    private Date date;
    private Date dateEnd;

}
