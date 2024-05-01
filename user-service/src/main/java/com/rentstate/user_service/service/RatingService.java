package com.rentstate.user_service.service;





import com.rentstate.user_service.model.aggregates.User;
import com.rentstate.user_service.model.entities.Rating;

import java.util.Optional;

public interface RatingService {
    Optional<Rating> create(Rating ratings);
    Optional<Rating> update(Rating ratings);

    Integer getAverageRatingByRatedUser(User ratedUser);

}