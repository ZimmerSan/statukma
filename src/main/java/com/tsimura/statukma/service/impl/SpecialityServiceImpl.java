package com.tsimura.statukma.service.impl;

import com.tsimura.statukma.entity.Speciality;
import com.tsimura.statukma.model.DetailedSpecialityModel;
import com.tsimura.statukma.model.MinimizedSpecialityModel;
import com.tsimura.statukma.model.helper.FilterRequest;
import com.tsimura.statukma.persistence.BaseRepository;
import com.tsimura.statukma.persistence.SpecialityRepository;
import com.tsimura.statukma.service.SpecialityService;
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
public class SpecialityServiceImpl extends AbstractServiceImpl<Speciality, MinimizedSpecialityModel, DetailedSpecialityModel> implements SpecialityService {

    private final SpecialityRepository repository;

    @Override
    public Page<MinimizedSpecialityModel> loadFiltered(Integer page, Integer size, FilterRequest filterRequest) {
        return loadFilteredRaw(page, size, filterRequest).map(MinimizedSpecialityModel::new);
    }

    @Override
    public Optional<Speciality> loadFull(Integer id) {
        Optional<Speciality> speciality = super.loadFull(id);
        speciality.ifPresent(s -> s.getStudents().stream().count());
        return speciality;
    }

    @Override
    public Optional<DetailedSpecialityModel> loadDetailed(Integer id) {
        return repository.findById(id).map(DetailedSpecialityModel::new);
    }

    @Override
    public List<DetailedSpecialityModel> loadDetailedBulk(List<Integer> ids) {
        return repository.findAllById(ids).stream().map(DetailedSpecialityModel::new).collect(Collectors.toList());
    }

    @Override
    protected Specification<Speciality> getFilterSpecification(FilterRequest request) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getName() != null && !request.getName().isEmpty())
                predicates.add(cb.like(root.get("name"), "%" + request.getName() + "%"));
            if (request.getFacultyId() != null)
                predicates.add(cb.equal(root.get("faculty"), request.getFacultyId()));

            Predicate[] p = predicates.toArray(new Predicate[predicates.size()]);
            return p.length == 0 ? null : p.length == 1 ? p[0] : cb.and(p);
        };
    }

    @Override
    protected BaseRepository<Speciality> getRepository() {
        return repository;
    }

}