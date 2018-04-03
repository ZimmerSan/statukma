package com.tsimura.statukma.entity.extra;

import com.tsimura.statukma.entity.enums.Control;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Embeddable
public class Semester {

    private String semester;
    private Integer lectures;
    private Integer seminars;
    private Integer practices;
    private Double credits;
    private Integer hours;

    @Enumerated(EnumType.STRING)
    private Control control;

    private Integer year;

}
