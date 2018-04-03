package com.tsimura.statukma.service.impl;

import com.tsimura.statukma.entity.Faculty;
import com.tsimura.statukma.model.DetailedFacultyModel;
import com.tsimura.statukma.model.MinimizedFacultyModel;
import com.tsimura.statukma.model.helper.FilterRequest;
import com.tsimura.statukma.persistence.BaseRepository;
import com.tsimura.statukma.persistence.FacultyRepository;
import com.tsimura.statukma.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl extends AbstractServiceImpl<Faculty, MinimizedFacultyModel, DetailedFacultyModel> implements FacultyService {

    private final FacultyRepository repository;

    @Override
    public Page<MinimizedFacultyModel> loadFiltered(Integer page, Integer size, FilterRequest filterRequest) {
        return loadFilteredRaw(page, size, filterRequest).map(MinimizedFacultyModel::new);
    }

    @Override
    public Optional<Faculty> loadFull(Integer id) {
        Optional<Faculty> byId = super.loadFull(id);
        byId.ifPresent(faculty -> {
            faculty.getChairs().stream().count();
            faculty.getSpecialities().stream().forEach(speciality -> speciality.getStudents().stream().count());
        });
        return byId;
    }

    @Override
    public Optional<DetailedFacultyModel> loadDetailed(Integer id) {
        return repository.findById(id).map(DetailedFacultyModel::new);
    }

    @Override
    public List<DetailedFacultyModel> loadDetailedBulk(List<Integer> ids) {
        return repository.findAllById(ids).stream().map(DetailedFacultyModel::new).collect(Collectors.toList());
    }

    @Override
    protected Specification<Faculty> getFilterSpecification(FilterRequest request) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getName() != null && !request.getName().isEmpty())
                predicates.add(cb.like(root.get("name"), "%" + request.getName() + "%"));

            Predicate[] p = predicates.toArray(new Predicate[predicates.size()]);
            return p.length == 0 ? null : p.length == 1 ? p[0] : cb.and(p);
        };
    }

    @Override
    protected BaseRepository<Faculty> getRepository() {
        return repository;
    }

}