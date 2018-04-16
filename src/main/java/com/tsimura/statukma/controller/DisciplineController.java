package com.tsimura.statukma.controller;

import com.tsimura.statukma.entity.Discipline;
import com.tsimura.statukma.model.DetailedDisciplineModel;
import com.tsimura.statukma.model.MinimizedDisciplineModel;
import com.tsimura.statukma.model.helper.SearchCriteriaModel;
import com.tsimura.statukma.persistence.DisciplineRepository;
import com.tsimura.statukma.service.AbstractService;
import com.tsimura.statukma.service.DisciplineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/disciplines")
public class DisciplineController extends AbstractController<Discipline, MinimizedDisciplineModel, DetailedDisciplineModel> {

    private final DisciplineService service;
    private final DisciplineRepository repository;

    @RequestMapping("/loadSearchCriteria")
    public List<SearchCriteriaModel> loadSearchCriteria() {
        return repository.buildSearchCriteria();
    }

    @Override
    protected AbstractService<Discipline, MinimizedDisciplineModel, DetailedDisciplineModel> getService() {
        return service;
    }
}