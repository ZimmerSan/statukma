package com.tsimura.statukma.service.impl;

import com.tsimura.statukma.entity.Speciality;
import com.tsimura.statukma.entity.Student;
import com.tsimura.statukma.model.DetailedStudentModel;
import com.tsimura.statukma.model.MinimizedStudentModel;
import com.tsimura.statukma.model.helper.FilterRequest;
import com.tsimura.statukma.persistence.BaseRepository;
import com.tsimura.statukma.persistence.StudentRepository;
import com.tsimura.statukma.service.StudentService;
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
public class StudentServiceImpl extends AbstractServiceImpl<Student, MinimizedStudentModel, DetailedStudentModel> implements StudentService {

    private final StudentRepository repository;

    @Override
    public Page<MinimizedStudentModel> loadFiltered(Integer page, Integer size, FilterRequest filterRequest) {
        return loadFilteredRaw(page, size, filterRequest).map(MinimizedStudentModel::new);
    }

    @Override
    public Optional<DetailedStudentModel> loadDetailed(Integer id) {
        return repository.findById(id).map(DetailedStudentModel::new);
    }

    @Override
    public List<DetailedStudentModel> loadDetailedBulk(List<Integer> ids) {
        return repository.findAllById(ids).stream().map(DetailedStudentModel::new).collect(Collectors.toList());
    }

    @Override
    protected Specification<Student> getFilterSpecification(FilterRequest request) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getName() != null && !request.getName().isEmpty())
                predicates.add(cb.like(root.get("name"), "%" + request.getName() + "%"));
            if (request.getCourse() != null)
                predicates.add(cb.equal(root.<Integer>get("yearOfStudy"), request.getCourse()));

            Join<Student, Speciality> speciality = root.join("speciality");
            if (request.getSpecialityId() != null)
                predicates.add(cb.equal(speciality.get("id"), request.getSpecialityId()));
            if (request.getFacultyId() != null)
                predicates.add(cb.equal(speciality.get("faculty"), request.getFacultyId()));

            Predicate[] p = predicates.toArray(new Predicate[predicates.size()]);
            return p.length == 0 ? null : p.length == 1 ? p[0] : cb.and(p);
        };
    }

    @Override
    protected BaseRepository<Student> getRepository() {
        return repository;
    }

}