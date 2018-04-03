package com.tsimura.statukma.persistence;

import com.tsimura.statukma.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BaseRepository<ENT extends BaseEntity> extends JpaRepository<ENT, Integer>,
        JpaSpecificationExecutor<ENT> {

}
