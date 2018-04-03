package com.tsimura.statukma.service.impl;

import com.tsimura.statukma.entity.BaseEntity;
import com.tsimura.statukma.model.EntityModel;
import com.tsimura.statukma.model.helper.FilterRequest;
import com.tsimura.statukma.persistence.BaseRepository;
import com.tsimura.statukma.service.AbstractService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public abstract class AbstractServiceImpl <ENT extends BaseEntity, MODEL extends EntityModel, DET_MOD extends EntityModel> implements AbstractService <ENT, MODEL, DET_MOD> {

    public Page<ENT> loadFilteredRaw(Integer page, Integer size, FilterRequest filterRequest) {
        return getRepository().findAll(getFilterSpecification(filterRequest), PageRequest.of(page, size));
    }

    public Optional<ENT> loadFull(Integer id) {
        return getRepository().findById(id);
    }

    protected abstract Specification<ENT> getFilterSpecification(FilterRequest request);

    protected abstract BaseRepository<ENT> getRepository();

}
