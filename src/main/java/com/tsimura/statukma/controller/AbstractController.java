package com.tsimura.statukma.controller;

import com.tsimura.statukma.entity.BaseEntity;
import com.tsimura.statukma.model.EntityModel;
import com.tsimura.statukma.model.helper.FilterRequest;
import com.tsimura.statukma.service.AbstractService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public abstract class AbstractController<ENT extends BaseEntity, MOD extends EntityModel, DET_MOD extends EntityModel> {

    @Transactional
    @PostMapping("/loadFiltered")
    public Page<MOD> loadFiltered(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                  @RequestParam(value = "size", defaultValue = "20") Integer size,
                                  @RequestBody FilterRequest filterRequest) {
        return getService().loadFiltered(page, size, filterRequest);
    }

    @Transactional
    @GetMapping("/loadFull/{id}")
    public ENT loadFull(@PathVariable Integer id) {
        return getService().loadFull(id).orElse(null);
    }

    @Transactional
    @PostMapping("/loadDetailed")
    public List<DET_MOD> loadDetailedBulk(@RequestBody List<Integer> ids) {
        return getService().loadDetailedBulk(ids);
    }

    @Transactional
    @GetMapping("/loadDetailed/{id}")
    public DET_MOD loadDetailed(@PathVariable Integer id) {
        return getService().loadDetailed(id).orElse(null);
    }

    protected abstract AbstractService<ENT, MOD, DET_MOD> getService();

}