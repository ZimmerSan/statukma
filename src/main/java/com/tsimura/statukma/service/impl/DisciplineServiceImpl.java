package com.tsimura.statukma.service.impl;

import com.tsimura.statukma.entity.Chair;
import com.tsimura.statukma.entity.Discipline;
import com.tsimura.statukma.model.DetailedDisciplineModel;
import com.tsimura.statukma.model.MinimizedDisciplineModel;
import com.tsimura.statukma.model.helper.FIlterHelper;
import com.tsimura.statukma.model.helper.FilterRequest;
import com.tsimura.statukma.persistence.BaseRepository;
import com.tsimura.statukma.persistence.DisciplineRepository;
import com.tsimura.statukma.service.DisciplineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DisciplineServiceImpl extends AbstractServiceImpl<Discipline, MinimizedDisciplineModel, DetailedDisciplineModel> implements DisciplineService {

    private final DisciplineRepository repository;

    @Override
    public Page<MinimizedDisciplineModel> loadFiltered(Integer page, Integer size, FilterRequest filterRequest) {
        return loadFilteredRaw(page, size, filterRequest).map(MinimizedDisciplineModel::new);
    }

    @Override
    public Optional<DetailedDisciplineModel> loadDetailed(Integer id) {
        return repository.findById(id).map(DetailedDisciplineModel::new);
    }

    @Override
    public List<DetailedDisciplineModel> loadDetailedBulk(List<Integer> ids) {
        return repository.findAllById(ids).stream().map(DetailedDisciplineModel::new).collect(Collectors.toList());
    }

    @Override
    protected Specification<Discipline> getFilterSpecification(FilterRequest request) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getName() != null && !request.getName().isEmpty())
                predicates.add(cb.like(root.get("name"), "%" + request.getName() + "%"));

            Join<Discipline, Chair> chair = root.join("chair");
//            if (request.getSpecialityId() != null)
//                predicates.add(cb.equal(chair.get("id"), request.getSpecialityId()));
            if (request.getFacultyId() != null)
                predicates.add(cb.equal(chair.get("faculty"), request.getFacultyId()));

            if (request.getEnrollmentOrder() == FIlterHelper.Order.ASC)
                query.orderBy(cb.asc(cb.size(root.get("enrollments"))));
            else if (request.getEnrollmentOrder() == FIlterHelper.Order.DESC)
                query.orderBy(cb.desc(cb.size(root.get("enrollments"))));

            Predicate[] p = predicates.toArray(new Predicate[predicates.size()]);
            return p.length == 0 ? null : p.length == 1 ? p[0] : cb.and(p);
        };
    }

    @Override
    protected BaseRepository<Discipline> getRepository() {
        return repository;
    }

}