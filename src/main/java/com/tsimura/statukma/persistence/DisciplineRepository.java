package com.tsimura.statukma.persistence;

import com.tsimura.statukma.entity.Discipline;

public interface DisciplineRepository extends BaseRepository<Discipline>, WithSearchCriteria<Discipline> {
}
