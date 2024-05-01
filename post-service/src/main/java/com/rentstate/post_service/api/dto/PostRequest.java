package com.rentstate.post_service.api.dto;

import lombok.Data;

@Data
public class PostRequest {
    private String title;
    private String content;
    private float price;
    private Long propertyId;
    private Long userId;
}
