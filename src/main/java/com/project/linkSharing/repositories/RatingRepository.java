package com.project.linkSharing.repositories;

import com.project.linkSharing.entities.Rating;
import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<Rating, Integer> {
    Rating save(Rating rating);
}
