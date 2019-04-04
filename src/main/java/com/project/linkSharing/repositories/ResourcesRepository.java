package com.project.linkSharing.repositories;

import com.project.linkSharing.entities.Resources;
import org.springframework.data.repository.CrudRepository;

public interface ResourcesRepository extends CrudRepository<Resources, Integer> {
    Resources save(Resources resources);
}
