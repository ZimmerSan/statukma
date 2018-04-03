package com.tsimura.statukma.persistence;

import com.tsimura.statukma.model.helper.SearchCriteriaModel;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WithSearchCriteria<K> {

    @Query("select new com.tsimura.statukma.model.helper.SearchCriteriaModel(f.name, f.id) from #{#entityName} f")
    List<SearchCriteriaModel> buildSearchCriteria();

}
