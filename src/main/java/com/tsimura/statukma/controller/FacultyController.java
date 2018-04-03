package com.tsimura.statukma.controller;

import com.tsimura.statukma.entity.Faculty;
import com.tsimura.statukma.model.DetailedFacultyModel;
import com.tsimura.statukma.model.MinimizedFacultyModel;
import com.tsimura.statukma.model.helper.SearchCriteriaModel;
import com.tsimura.statukma.persistence.FacultyRepository;
import com.tsimura.statukma.service.AbstractService;
import com.tsimura.statukma.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/faculties")
public class FacultyController extends AbstractController<Faculty, MinimizedFacultyModel, DetailedFacultyModel> {

    private final FacultyService service;
    private final FacultyRepository repository;

    @RequestMapping("/loadSearchCriteria")
    public List<SearchCriteriaModel> loadSearchCriteria() {
        return repository.buildSearchCriteria();
    }

    @Override
    protected AbstractService<Faculty, MinimizedFacultyModel, DetailedFacultyModel> getService() {
        return service;
    }
}