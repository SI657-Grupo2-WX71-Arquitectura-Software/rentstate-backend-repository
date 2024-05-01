package com.rentstate.property_service.api.dto;

import lombok.Data;

@Data
public class PropertyResponse {

    private Long id;
    private String title;
    private String description;
    private String characteristics;
    private String location;
    private String category;
    private Boolean available;
    private Long userId;


}
