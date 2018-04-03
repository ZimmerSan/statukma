package com.tsimura.statukma.service;

import com.tsimura.statukma.entity.BaseEntity;
import com.tsimura.statukma.model.EntityModel;
import com.tsimura.statukma.model.helper.FilterRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface AbstractService <ENT extends BaseEntity, MODEL extends EntityModel, DET_MOD extends EntityModel> {

    Page<MODEL> loadFiltered(Integer page, Integer size, FilterRequest filterRequest);

    Optional<ENT> loadFull(Integer id);

    Optional<DET_MOD> loadDetailed(Integer id);

    List<DET_MOD> loadDetailedBulk(List<Integer> ids);

}
