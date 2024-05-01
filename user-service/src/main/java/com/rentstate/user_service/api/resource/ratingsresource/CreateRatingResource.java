package com.rentstate.user_service.api.resource.ratingsresource;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CreateRatingResource {

    private Long ratedUserId;

    private Long ratedByUserId;

    private int rating;

}
