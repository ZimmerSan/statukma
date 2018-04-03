package com.tsimura.statukma.persistence;

import com.tsimura.statukma.entity.Faculty;

public interface FacultyRepository extends BaseRepository<Faculty>, WithSearchCriteria<Faculty> {
}