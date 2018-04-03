package com.tsimura.statukma.persistence;

import com.tsimura.statukma.entity.Chair;

public interface ChairRepository extends BaseRepository<Chair>, WithSearchCriteria<Chair> {
}
