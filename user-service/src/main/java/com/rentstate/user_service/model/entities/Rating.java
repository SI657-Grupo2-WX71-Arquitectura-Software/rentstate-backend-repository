package com.rentstate.user_service.model.entities;

import com.rentstate.user_service.model.aggregates.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name ="ratigns")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rated_user_id", nullable = false)
    private User ratedUser;

    @ManyToOne
    @JoinColumn(name = "rated_by_user_id", nullable = false)
    private User ratedByUser;

    @Min(1)
    @Max(5)
    @Column(nullable = false)
    private int rating;


    public Rating(){}
    public Rating(User ratedUser, User ratedByUser, int rating) {
        this.ratedUser = ratedUser;
        this.ratedByUser = ratedByUser;
        this.rating = rating;
    }
}