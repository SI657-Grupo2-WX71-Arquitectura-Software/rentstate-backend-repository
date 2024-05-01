package com.rentstate.user_service.repositories;



import com.rentstate.user_service.model.aggregates.User;
import com.rentstate.user_service.model.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findAllByRatedUser(User ratedUser);

    Optional<Rating> findByRatedUserAndRatedByUser(User ratedUser, User ratedByUser);
}