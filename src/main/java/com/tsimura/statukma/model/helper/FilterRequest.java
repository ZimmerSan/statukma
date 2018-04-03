package com.tsimura.statukma.model.helper;

import lombok.Data;

@Data
public class FilterRequest {

    private String name;
    private Integer facultyId;
    private Integer specialityId;
    private Integer course;

}
