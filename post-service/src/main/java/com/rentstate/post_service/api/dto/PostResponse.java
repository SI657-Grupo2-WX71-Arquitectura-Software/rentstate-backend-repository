package com.rentstate.post_service.api.dto;

import lombok.Data;

@Data
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private float price;
    private Long propertyId;
    private Long userId;
}
