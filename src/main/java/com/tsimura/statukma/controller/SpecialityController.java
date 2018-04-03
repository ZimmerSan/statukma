package com.tsimura.statukma.controller;

import com.tsimura.statukma.entity.Speciality;
import com.tsimura.statukma.model.DetailedSpecialityModel;
import com.tsimura.statukma.model.MinimizedSpecialityModel;
import com.tsimura.statukma.model.helper.SearchCriteriaModel;
import com.tsimura.statukma.persistence.SpecialityRepository;
import com.tsimura.statukma.service.AbstractService;
import com.tsimura.statukma.service.SpecialityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/specialities")
public class SpecialityController extends AbstractController<Speciality, MinimizedSpecialityModel, DetailedSpecialityModel> {

    private final SpecialityService service;
    private final SpecialityRepository repository;

    @RequestMapping("/loadSearchCriteria")
    public List<SearchCriteriaModel> loadSearchCriteria() {
        return repository.buildSearchCriteria();
    }

    @Override
    protected AbstractService<Speciality, MinimizedSpecialityModel, DetailedSpecialityModel> getService() {
        return service;
    }
}